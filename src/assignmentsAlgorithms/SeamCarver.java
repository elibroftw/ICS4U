package assignmentsAlgorithms;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;

public class SeamCarver {

	private BufferedImage img;
	private static int[][][] pixels;

	/**
	 * Constructor
	 * 
	 * @param picture
	 */
	public SeamCarver(Image picture) {
		img = (BufferedImage) picture;
		pixels = getImageAsPixels(picture, 0, 0, getWidth(), getHeight());
	}

	/**
	 * BufferedImage that the SeamCarver uses
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		return img;
	}

	/**
	 * Image that the SeamCarver uses
	 * 
	 * @return
	 */
	public Image picture() {
		return img;
	}

	/**
	 * width of image
	 * 
	 * @param img
	 * @return
	 */
	public int getWidth() {
		return img.getWidth();
	}

	/**
	 * height of image
	 * 
	 * @param img
	 * @return
	 */
	public int getHeight() {
		return img.getHeight();
	}

	/**
	 * Takes the integer representation of a pixel and return it in its component
	 * form. Below is a representation of the integer, with explanation of which
	 * bits represent which colour:
	 * 
	 * Colour Value: Alpha Red Green Blue Binary Digit: 01234567 01234567 01234567
	 * 01234567
	 * 
	 * Each colour value takes 8 binary digits of the integer (total 32 bits). The
	 * most significant bits represent the alpha (transparency) value from 0 (fully
	 * transparent) to 255 (fully opaque). This is followed by red, green and blue
	 * values, each from 0 to 255.
	 * 
	 * The returned value is an integer array of size 4, containing the individual
	 * colour values of the red, green, blue and alpha colours, in that order.
	 * 
	 * Ex: input value pixel = 11111111 01011011 11010011 01110111 produces an
	 * array: [91, 211, 119, 255]
	 * 
	 * @param pixel
	 *            - The integer representation of the pixel in ARGB form.
	 * @return array of integers containing the colour componets of the input pixel
	 */
	public int[] convertSinglePixel(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		return new int[] { red, green, blue, alpha };
	}

	/**
	 * Takes a java.awt.Image and returns a subsection of its pixel values in a 3
	 * dimensional array. The first two dimensions represent the width and height of
	 * the image. The 3rddimension is the pixel data with 4 integers: [Red, Green,
	 * Blue, Alpha].
	 * 
	 * Each pixel value is an integer between 0 and 255 inclusive.
	 * 
	 * If for any reason the image cannot be loaded, the method returns null and
	 * prints an error to System.err.
	 * 
	 * @param img
	 *            - The image to be converted to a 3D array.
	 * @param x
	 *            - The x coordinate to start the image subsection.
	 * @param y
	 *            - The y coordinate to start the image subsection.
	 * @param w
	 *            - The width of the window within the image.
	 * @param h
	 *            - The height of the window within the image.
	 * @return int [][][] a 3D array of pixel information
	 */
	public int[][][] getImageAsPixels(Image img, int x, int y, int w, int h) {
		int[] pixels = new int[w * h];
		PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixels, 0, w);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			System.err.println("interrupted waiting for pixels!");
			return null;
		}
		if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
			System.err.println("image fetch aborted or errored");
			return null;
		}
		int[][][] result = new int[w][h][];
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				result[i][j] = convertSinglePixel(pixels[j * w + i]);
			}
		}
		return result;
	}

	/**
	 * @param image
	 * @return
	 */
	public Image createImage() {
		// parameter used to be int image[][][] but that's just the global pixel
		// variable
		int width = pixels.length;
		int height = pixels[0].length;
		// Convert the 2D data into a 3D array:
		int oneD[] = new int[width * height];
		// For column
		for (int i = 0; i < width; i++) {
			// for each row
			for (int j = 0; j < height; j++) {
				if (pixels[i][j].length != 4) {
					System.out.println("Image does not have 3 colors and an alpha channel");
					return null;
				}
				// Check for invalid values.
				for (int k = 0; k < 4; k++)
					if (pixels[i][j][k] > 255)
						pixels[i][j][k] = 255;
					else if (pixels[i][j][k] < 0)
						pixels[i][j][k] = 0;
				// Combine each pixel into one int.
				// First 8 bits are alpha value (255=opaque)
				// Then red, green, and blue (8-bits each)
				oneD[j * width + i] = (0x000000FF & pixels[i][j][3]) << 24 | // Alpha
						(0x000000FF & pixels[i][j][0]) << 16 | // Red
						(0x000000FF & pixels[i][j][1]) << 8 | // Green
						(0x000000FF & pixels[i][j][2]); // Blue
			}
		}

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, width, height, oneD, 0, width);
		return img;
	}

	/**
	 * Calculates the energy of a certain pixel in the image
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double energy(int x, int y) {
		int leftPixelR, leftPixelG, leftPixelB;
		int rightPixelR, rightPixelG, rightPixelB;
		int topPixelR, topPixelG, topPixelB;
		int botPixelR, botPixelG, botPixelB;
		if (x < 0 || y < 0 || x > img.getWidth() || y > img.getHeight())
			throw new java.lang.IndexOutOfBoundsException();
		try {
			leftPixelR = pixels[x - 1][y][0];
			leftPixelG = pixels[x - 1][y][1];
			leftPixelB = pixels[x - 1][y][2];
		} catch (ArrayIndexOutOfBoundsException e) {
			leftPixelR = 255;
			leftPixelG = 255;
			leftPixelB = 255;
		}
		try {
			rightPixelR = pixels[x + 1][y][0];
			rightPixelG = pixels[x + 1][y][1];
			rightPixelB = pixels[x + 1][y][2];
		} catch (ArrayIndexOutOfBoundsException e) {
			rightPixelR = 255;
			rightPixelG = 255;
			rightPixelB = 255;
		}
		try {
			topPixelR = pixels[x][y - 1][0];
			topPixelG = pixels[x][y - 1][1];
			topPixelB = pixels[x][y - 1][2];
		} catch (ArrayIndexOutOfBoundsException e) {
			topPixelR = 255;
			topPixelG = 255;
			topPixelB = 255;
		}
		try {
			botPixelR = pixels[x][y + 1][0];
			botPixelG = pixels[x][y + 1][1];
			botPixelB = pixels[x][y + 1][2];
		} catch (ArrayIndexOutOfBoundsException e) {
			botPixelR = 255;
			botPixelG = 255;
			botPixelB = 255;
		}
		double gradientX = Math.pow(rightPixelR - leftPixelR, 2) + Math.pow(rightPixelG - leftPixelG, 2)
				+ Math.pow(rightPixelB - leftPixelB, 2);
		double gradientY = Math.pow(topPixelR - botPixelR, 2) + Math.pow(topPixelG - botPixelG, 2)
				+ Math.pow(topPixelB - botPixelB, 2);

		return gradientX + gradientY;

	}

	/**
	 * Calculates the energy for every pixel
	 * 
	 * @return an array containing energy information
	 */
	private double[][] getEnergies() {
		double[][] energies = new double[getWidth()][getHeight()];
		for (int w = 0; w < img.getWidth(); w++) {
			for (int h = 0; h < img.getHeight(); h++) {
				energies[w][h] = energy(w, h);
			}
		}
		return energies;
	}

	/**
	 * finds the horizontal seam
	 * 
	 * @return
	 */
	public int[] findHorizontalSeam() {
		int width = getWidth(); // to prevent abuse of getWidth() and getHeight()
		int height = getHeight();
		double[][] energies = getEnergies(); // recalculates energy to account for any previous carve
		int[] path = new int[width]; // the path has to be the size of the img's width
		double leastEnergy = -1; // default value for total energy of path
		double tempEnergy = 0; // total energy for every path at a time
		int modifier = 0; // prevents two entries from differing by more than 1
		for (int h = 0; h < height; h++) { // Goes through differnet starting heights
			int[] temp = new int[width];
			temp[0] = h; // The first entry will alwyas be the starting height at a width of 0
			tempEnergy = energies[h][0];
			for (int w = 1; w < width; w++) { // goes through every column
				double energyMiddle;
				double energyBot;
				double energyTop;
				// try and cathes for corner cases/boundary cases
				try {
					energyTop = energies[w][h - 1 + modifier];
				} catch (ArrayIndexOutOfBoundsException e) {
					energyTop = -1;
				}
				try {
					energyMiddle = energies[w][h + modifier];
				} catch (ArrayIndexOutOfBoundsException e) {
					energyMiddle = -1;
				}
				try {
					energyBot = energies[w][h + 1 + modifier];
				} catch (ArrayIndexOutOfBoundsException e) {
					energyBot = -1;
				}
				// Figuring out which pixel is valid and has the least energy
				if (energyMiddle >= 0 && energyMiddle < energyBot && energyMiddle < energyTop) {
					tempEnergy += energyMiddle;
					temp[h] = w + modifier;
				} else if (energyBot >= 0 && energyBot < energyMiddle && energyBot < energyTop) {
					tempEnergy += energyMiddle;
					modifier++;
					temp[h] = w + modifier;
				} else if (energyTop >= 0 && energyTop < energyMiddle && energyTop < energyBot) {
					tempEnergy += energyMiddle;
					modifier--;
					temp[h] = w + modifier;
				}
			}
			// if the total energy of the path is less than the record holder, replace the
			// recordholder and output path
			if (tempEnergy < leastEnergy || leastEnergy < 0) {
				leastEnergy = tempEnergy;
				path = temp;
			}
		}
		return path;
	}

	/**
	 * Finds the vertical seam
	 * 
	 * @return
	 */
	public int[] findVerticalSeam() {
		int width = getWidth(); // Most Comments are the same as HorizontalSeam
		int height = getHeight();
		double[][] energies = getEnergies();
		int[] path = new int[height];
		double leastEnergy = -1;
		double tempEnergy = 0;
		int modifier = 0;
		for (int w = 0; w < width; w++) { // Since it's vertical now, we go through each starting width
			int[] temp = new int[height]; // The first entry will alwyas be the starting width at a height of 0
			temp[0] = w;
			// Height is 0
			tempEnergy = energies[w][0];
			for (int h = 1; h < height; h++) { // goes through every row
				double energyMiddle;
				double energyRight;
				double energyLeft;
				try {
					energyLeft = energies[w - 1 + modifier][h];
				} catch (ArrayIndexOutOfBoundsException e) {
					energyLeft = -1;
				}
				try {
					energyMiddle = energies[w + modifier][h];
				} catch (ArrayIndexOutOfBoundsException e) {
					energyMiddle = -1;
				}
				try {
					energyRight = energies[w + 1 + modifier][h];
				} catch (ArrayIndexOutOfBoundsException e) {
					energyRight = -1;
				}
				if (energyMiddle >= 0 && energyMiddle < energyRight && energyMiddle < energyLeft) {
					tempEnergy += energyMiddle;
					temp[h] = w + modifier;
				} else if (energyRight >= 0 && energyRight < energyMiddle && energyRight < energyLeft) {
					tempEnergy += energyMiddle;
					modifier++;
					temp[h] = w + modifier;

				} else if (energyLeft >= 0 && energyLeft < energyMiddle && energyLeft < energyRight) {
					tempEnergy += energyMiddle;
					modifier--;
					temp[h] = w + modifier;
				}
			}
			if (tempEnergy < leastEnergy || leastEnergy < 0) {
				leastEnergy = tempEnergy;
				path = temp;
			}
		}
		return path;
	}

	/**
	 * removes the given horizontal path from the pixel array
	 * 
	 * @param path
	 * @throws java.lang.IllegalArgumentException
	 * @throws java.lang.NullPointerException
	 */
	public void removeHorizontalSeam(int[] path)
			throws java.lang.IllegalArgumentException, java.lang.NullPointerException {
		// Eclipse says testing for null is dead code so I just put that ^ instead
		int width = getWidth();
		int height = getHeight();
		if (path.length != width || width <= 1) { // cases
			throw new java.lang.IllegalArgumentException();
		}
		int temp = -1;
		int[][][] newPixels = new int[width][height - 1][4];
		// easier to make new array and then replace rather than ... exactly too much
		// thinking for the other way if there even is one
		for (int w = 0; w < width; w++) {
			int modifier = 0; // index modifier I go over this later
			int toRemove = path[w];
			if (Math.abs(toRemove - temp) > 1 && temp >= 0 || toRemove > width || toRemove < 0) { // finding cases
				throw new java.lang.IllegalArgumentException();
			}
			for (int h = 0; h < height; h++) {
				if (h == toRemove) {
					modifier = 1; // now we have to shift start putting pixels one left because we skipped the
									// path one
				} else {
					newPixels[w][h - modifier] = pixels[w][h]; // simple copy paste
				}
			}
			temp = toRemove;
		}
		pixels = newPixels;
		img = (BufferedImage) createImage();
	}

	/**
	 * removes the given vertical path from the pixel array
	 * 
	 * @param path
	 * @throws java.lang.IllegalArgumentException
	 * @throws java.lang.NullPointerException
	 */
	public void removeVerticalSeam(int[] path)
			throws java.lang.IllegalArgumentException, java.lang.NullPointerException {
		// Same logic as Horizontal but now width and height are switched
		int width = getWidth();
		int height = getHeight();
		if (path.length != height || height <= 1) {
			throw new java.lang.IllegalArgumentException();
		}
		int temp = -1;
		int[][][] newPixels = new int[width - 1][height][4];
		for (int h = 0; h < height; h++) {
			int modifier = 0;
			int toRemove = path[h];
			if (Math.abs(toRemove - temp) > 1 && temp >= 0 || toRemove > height || height < 0) {
				throw new java.lang.IllegalArgumentException();
			}
			for (int w = 0; w < width; w++) {
				if (w == toRemove) {
					modifier = 1;
				} else {
					newPixels[w - modifier][h] = pixels[w][h];
				}
			}
			temp = toRemove;
		}
		pixels = newPixels;
		img = (BufferedImage) createImage();
	}
}
