package animation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpriteData {

	private String spriteLocation; // location of sprite gif
	public int numberOfSprites, spriteWidth, spriteHeight; // cleaner code if this is public
	private List<Animation> animations = new ArrayList<>(); // List of animations
	private List<String> animationNames = new ArrayList<>(); // helpful for setting comboBox choices

	/**
	 * Parses the data of a given file
	 * 
	 * @param filename
	 * @throws IOException
	 */
	SpriteData(String filename) throws IOException {
		File file = new File(filename); // create File Object
		BufferedReader br = new BufferedReader(new FileReader(file)); // Create the reader
		String st;
		spriteLocation = br.readLine(); // First line is the reference to the gif
		String[] metadata = br.readLine().split(" "); // metadata of the sprite
		numberOfSprites = Integer.parseInt(metadata[0]);
		spriteWidth = Integer.parseInt(metadata[1]);
		spriteHeight = Integer.parseInt(metadata[2]);
		while ((st = br.readLine()) != null) { // reading rest of file
			String[] tempData = st.split(" ");
			Animation animation = new Animation(tempData[0]); // animation name is first
			animationNames.add(tempData[0]); // add the name to the animationNames list
			for (int i = 1; i < tempData.length; i++) { // go through each frame of the line
				String[] frameData = tempData[i].split(":"); // info of each frame
				animation.addFrame(Integer.parseInt(frameData[0]), Integer.parseInt(frameData[1]));
				if (frameData.length == 3) { // if the animation loops
					animation.setLoop(Integer.parseInt(frameData[2]));
				}
			}
			animations.add(animation);
		}
		br.close();

	}
	
	/**
	 * returns a list of the animations
	 * @return animations
	 */
	public List<Animation> getAnimations() {
		return animations;
	}

	/**
	 * returns a list of the animation names
	 * @return animationNames
	 */
	public List<String> getNames() {
		return animationNames;
	}
	
	/**
	 * returns the location of the sprite gif
	 * @return spriteLocation
	 */
	public String getSpriteLocation() {
		return spriteLocation;
	}
}
