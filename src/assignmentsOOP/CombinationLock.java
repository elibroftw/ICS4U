package assignmentsOOP;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CombinationLock implements ICombinationLock {

	private int[] combination;
	private int min = 0;
	private int max = 39;
	private int health = 5;
	private String password = "Elijah";
	private boolean isAdmin = false;
	private boolean isLocked = true;
	private Random rand = new Random();
	private int pickLockAttempts = 0;
	private int combinationAttempts = 0;
	
	/**
	 * This constructor set the lock combination as the parameters
	 * @param a the first number of the combination
	 * @param b the second number of the combination
	 * @param c the third number of the combination
	 */
	public CombinationLock(int a, int b, int c) {
		combination = new int[] { a, b, c };
		max = Math.max(Math.max(a, b), c);
		min = Math.min(Math.min(a, b), c);
	}
	
	/**
	 * This constructor sets the lock to a random combination 
	 */
	public CombinationLock() {
		int  a = rand.nextInt(max) + min;
		int b = rand.nextInt(max) + min;
		int c = rand.nextInt(max) + min;
		combination = new int[] { a, b, c };		
	}

	/**
	 * Returns the health of the lock at a maximum of 5 and a minimum of 0
	 */
	@Override
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns a boolean representing if the lock is broken
	 * A lock is broken if its health is 0
	 */
	@Override
	public boolean isBroken() {
		return health == 0;
	}
	
	/**
	 * Attempt to break the lock
	 * Does a random amount of damage to the lock from 1 to 5
	 */
	@Override
	public int breakLock() {
		int damage = rand.nextInt(5) + 1;
		health -= damage;
		if(health <= 0) {
			health = 0;
		}
		return damage;
	}

	/**
	 * @return int maximum number the lock supports
	 */
	@Override
	public int getMax() {
		return max;
	}

	/**
	 * @return int minimum number the lock supports
	 */
	@Override
	public int getMin() {
		return min;
	}

	/**
	 * @return boolean representing if the lock is locked
	 */
	@Override
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * Locks the lock
	 */
	@Override
	public void lock() {
		isLocked = true;
	}
	
	/**
	 * Tries to unlock the lock with the given combination
	 * @param combination the combination that is to be tried
	 * @return boolean representing if the lock has been unlocked
	 */
	@Override
	public boolean unlock(int[] combination) {
		combinationAttempts += 1;
		if (Arrays.equals(this.combination, combination)) {
			isLocked = false;
		}
		return isLocked;
	}

	/**
	 * 10% chance of picking lock
	 * rand.nextInt(100) + 1 gives a number [1, 100]
	 * @return boolean representing if the lock was picked or not
	 */
	@Override
	public boolean pickLock() {
		pickLockAttempts += 1;
		if(!isLocked) {
			return false;
		}
		if (rand.nextInt(100)+1 <= 10) {
			isLocked = false;
			return true;
		}
		return false;
	}

	/**
	 * @return the amount of times the lock was attempted to be picked
	 */
	@Override
	public int numPickLockAttempts() {
		return pickLockAttempts;
	}

	/**
	 * @return the amount of times the lock was attempted to be picked
	 */
	@Override
	public int numCombinationAttempts() {
		return combinationAttempts;
	}

	/**
	 * @param password The admin password
	 * @return boolean indicating whether admin access has been granted or not
	 */
	@Override
	public boolean adminMode(String password) {
		if (password.equals(this.password)) {
			isAdmin = true;
			return true;
		}
		return false;
	}

	/**
	 * Leaves admin mode
	 */
	@Override
	public void leaveAdmin() {
		isAdmin = false;
	}

	/**
	 * Changes the admin password
	 */
	@Override
	public boolean setPassword(String password) {
		if(isAdmin) {
			this.password = password;
			return true;
		}
		return false;
	}

	/**
	 * @return the combination of the loc
	 */
	@Override
	public int[] getCombination() {
		if(isAdmin) {
			return combination;
		}
		return new int [] {};
	}

	/**
	 * Resets the locks health to 5
	 * @return boolean representing if the reset worked
	 */
	@Override
	public boolean resetHealth() {
		if(isAdmin) {
			health = 5;
			return true;
		}
		return false;
	}

	/**
	 * Sets a new lock combination
	 * For a combination to be valid, the numbers need to be >= min and <= max
	 * @param combination
	 * @return boolean indicating if the new combination is valid
	 */
	@Override
	public boolean setCombination(int[] combination) {
		if (isAdmin && Arrays.stream(combination).min().getAsInt() >= min
				&& Arrays.stream(combination).max().getAsInt() <= max) {
			this.combination = combination;
			return true;
		}
		return false;
	}

	/**
	 * sets a new minimum lock combination value
	 * @param min this is the new min value which has to be less than the lowest number in the combination
	 * @return boolean indicating whether it worked or not
	 */
	@Override
	public boolean setMin(int min) { 
		int a = combination[0], b = combination[1], c = combination[2];
		int combinationMin = Math.min(Math.min(a, b), c);
		if(isAdmin && min <= combinationMin) {
			this.min = min;
			return true;
		}
		return false;
	}

	/**
	 * sets a new maximum lock combination value
	 * @param max this is the new max value which has to be more than the highest number in the combination
	 * @return boolean indicating whether it worked or not
	 */
	@Override
	public boolean setMax(int max) {
		int a = combination[0], b = combination[1], c = combination[2];
		int combinationMax = Math.max(Math.max(a, b), c);
		if(isAdmin && max >= combinationMax) {
			this.max = max;
			return true;
		}
		return false;
	}
	
	/**
	 * Saves the combination to a new file
	 * @param filename saves combination to this file
	 * @return boolean whether the save worked or not
	 */
	@Override
	public boolean save(String filename) {
		if (isAdmin) {
			File f = new File(filename);
			try {
				PrintWriter pw = new PrintWriter(f);
				for (int i = 0; i < 3; i++) {
					pw.println(combination[i]);
				}
				pw.flush();
				pw.close();
				return true;
			} catch (FileNotFoundException e) {
				return false;
			}
		}
		return false;
	}

	
	/**
	 * Loads combination from a file and changes min/max values to comply
	 * @return boolean if the loading worked
	 */
	@Override
	public boolean load(String filename) {
		if (isAdmin) {
			File f = new File(filename);
			Scanner fileIn;
			try {
				fileIn = new Scanner(f);
				for (int i = 0; i < 3; i++) {
					combination[i] = Integer.parseInt(fileIn.next());
				}
				fileIn.close();
				max = Math.max(Math.max(combination[0], combination[1]), combination[2]);
				min = Math.min(Math.min(combination[0], combination[1]), combination[2]);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Prints out stuff about the lock only if there is admin access
	 */
	@Override
	public String toString() {
		if(isAdmin) return "Lock [isLocked: " + isLocked + ", isBroken: " + isBroken() + ", Max: " + max + ", Min: " + min + "]";
		return "Only admins can access this";
	}
}
