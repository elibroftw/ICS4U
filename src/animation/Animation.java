package animation;

import java.util.LinkedList;

public class Animation {
	private String name;
	private LinkedList<int[]> frames = new LinkedList<>();
	private int loop = -1;
	private int index = -1;

	/**
	 * New animation with name name
	 * @param name
	 */
	Animation(String name) {
		this.name = name;
	}

	/**
	 * add a frame to the animation
	 * @param spriteNumber
	 * @param frameTimer
	 */
	public void addFrame(int spriteNumber, int frameTimer) {
		frames.add(new int[] { spriteNumber, frameTimer });
	}

	/**
	 * sets a loop for the animation, default is -1 indicating no loop reference
	 * @param reference
	 */
	public void setLoop(int reference) {
		loop = reference;
	}
	
	/**
	 * sets the animation to the beginning
	 * @return
	 */
	public int[] start() {
		index = -1;
		return getNext();
	}
	
	/**
	 * gets the next frame in the animation, the next frame is the same frame if the animation is not looped and is maxed
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public int[] getNext() throws ArrayIndexOutOfBoundsException{
		index += 1;
		if(index >= frames.size()) {
			if (isLooped()) {
				index = loop;
			}
			else {
				index = Math.max(0, index-1);
			}
		}
		return frames.get(index); // returns Spirte number, Frame timer; if frame timer is 0, that means infinity
	}
	
	/**
	 * gets the nextFrame even if the animation isn't looped (gets first frame in this case)
	 * @return
	 */
	public int[] overrideGetNext() {
		index += 1;
		if(index >= frames.size()) {
			index = Math.max(0, loop);
		}
		return frames.get(index);
	}

	/**
	 * gets the previous frame, does not care if it's looped
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public int[] getPrevious() throws ArrayIndexOutOfBoundsException{
		index -= 1;
		if(index < 0) {
			index = frames.size() - 1;
		}
		return frames.get(index);
	}
	
	/**
	 * get the names of the animation
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * returns if the animation is looped or not by checking if loop == -1
	 * @return
	 */
	public boolean isLooped(){
		return (loop != -1);
	}
	
	/**
	 * returns how many frames are in the animation
	 * @return
	 */
	public int size() {
		return frames.size();
	}
	
	/**
	 * returns the frames of the animation
	 * @return
	 */
	public LinkedList<int[]> getFrames(){
		return frames;
	}
	
	@Override
	public String toString(){
		return "[Animation, name=" + name + ", frames="+frames.size()+"]";
	}
}
