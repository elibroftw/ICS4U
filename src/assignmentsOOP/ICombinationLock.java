package assignmentsOOP;

public interface ICombinationLock {
	int getHealth();
	boolean isBroken();
	int breakLock();
	
	int getMax();
	int getMin();
	
	boolean isLocked();
	
	void lock();
	boolean unlock(int[] combination);
	
	boolean pickLock();
	int numPickLockAttempts();
	int numCombinationAttempts();
	
	// ADMIN STUFF
	boolean adminMode(String password);
	void leaveAdmin();
	boolean setPassword(String password);
	int[] getCombination();
	boolean resetHealth();
	boolean setCombination(int[] combination);
	boolean setMin(int min);
	boolean setMax(int max);
	String toString();
	boolean save(String filename);
	boolean load(String filename);
	
	
}
