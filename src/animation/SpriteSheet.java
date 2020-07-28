package animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class SpriteSheet {
	List<BufferedImage> subimages = new ArrayList<>(); // Each gif is split into sub images
	SpriteSheet(String resourceFile, int spriteWidth, int spriteHeight, int numberOfSprites){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(resourceFile));
		} catch (IOException e) {
		}
		for (int i = 0; i < numberOfSprites*spriteWidth; i += spriteWidth) {
			subimages.add(img.getSubimage(i, 0, spriteWidth, spriteHeight));
		}
	}

	/**
	 * returns the frame image at the given index
	 * @param index
	 * @return the frame at index
	 */
	public BufferedImage getSprite(int index){
		return subimages.get(index);
		// modifies resource file image so that it only shows the frame that's needed
	}
}
