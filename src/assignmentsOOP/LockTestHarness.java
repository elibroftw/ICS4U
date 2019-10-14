package assignmentsOOP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class LockTestHarness {
	public static void main(String args[]) {
		// Blackbox Tests
		CombinationLock testLock1 = new CombinationLock();
		CombinationLock testLock2 = new CombinationLock(4, 23, 14);
		
		testBreakLock("Does lock take damage", testLock1, 1, 5);
		testAdmin("Incorrecct admin password", testLock1, "asd", false);
		testAdmin("Correcct admin password", testLock1, "Elijah", true);
		testLock1.adminMode("Elijah");
		testUnlock("Checking if getCombination works", testLock1, testLock1.getCombination(), true);
		testPickLock("Testing pickLock when lock is unlocked", testLock1, false); // will take some time as there is a timeout
		testLock1.lock();
		testPickLock("Testing pickLock", testLock1, true); // true is expected unless lock is unlocked; Chances of failing are very low
		testUnlock("Correct combo", testLock2, new int[] { 4, 23, 14 }, true);
		testUnlock("incorrect combo", testLock2, new int[] { 3, 22, 13 }, false);
		testLock2.adminMode("Elijah");
		testSetCombination("valid", testLock2, new int[] {15, 4, 23}, true);
		testSetCombination("border a lot below max but above min", testLock2, new int[] {13, 13, 13}, true);
		testSetCombination("border 2 below max", testLock2, new int[] {21, 21, 21}, true);
		testSetCombination("border 1 below max", testLock2, new int[] {22, 22, 22}, true);
		testSetCombination("border max", testLock2, new int[] {23, 23, 23}, true);
		testSetCombination("border 1 above max", testLock2, new int[] {24, 24, 24}, false);
		testSetCombination("border 2 above max", testLock2, new int[] {25, 25, 25}, false);
		testSetCombination("border a lot above max", testLock2, new int[] {35, 35, 35}, false);
		
		testSetCombination("border a lot above min but below max", testLock2, new int[] {12, 12, 12}, true);
		testSetCombination("border 2 above min", testLock2, new int[] {6, 6, 6}, true);
		testSetCombination("border 1 above min", testLock2, new int[] {5, 5, 5}, true);
		testSetCombination("border is min", testLock2, new int[] {4, 4, 4}, true);
		testSetCombination("border 1 below min", testLock2, new int[] {3, 3, 3}, false);
		testSetCombination("border 2 below min", testLock2, new int[] {2, 2, 2}, false);
		testSetCombination("border a lot below min", testLock2, new int[] {-21, -21, -21}, false);
		
		testLock2.setCombination(new int[] {4, 23, 14});
		testSetMax("above maximum number in combo", testLock2, 55, true);
		testSetMax("1 below maximum number in combo", testLock2, 22, false);
		testSetMax("below maximum number in combo somewhere in the middle", testLock2, 14, false);
		testSetMax("is maximum number in combo", testLock2, 23, true);
		
		testSetMin("below minimum number in combo", testLock2, 2, true);
		testSetMin("1 above minimum number in combo", testLock2, 5, false);
		testSetMin("above minimum number in combo somehwhere in the middle", testLock2, 13, false);
		testSetMin("is minimum number in combo", testLock2, 4, true);
		
		testSave("save to a file", testLock2, "save1.txt", true);
		testLoad("file does not exist", testLock2, "save2.txt", false);
		createSaveFile("save3.txt", 0, 21, 88);
		testLoad("file does exist, numbers will override max and min", testLock2, "save3.txt", true);
		testLock2.load("save3.txt");
		testLoad("load file from first save", testLock2, "save1.txt", true);
		testCommonAdminMethods(testLock2); // Also tests if leaveAdmin works and if methods return false if not admin 
		testCommonMethods(testLock2);
	}

	private static void testCommonMethods(CombinationLock lock) {
		if(lock.getHealth() <= 5 && lock.getHealth() >= 0) {
			System.out.println("PASSED! getHealth()");
		}
		else {
			System.out.println("FAILED! getHealth() should be <= 5 and >= 0");
		}
		if(lock.getMax() >= 23) {
			System.out.println("PASSED! Max value is valid");
		}
		else {
			System.out.println("FAILED! Max should be >= than the max value in the combination");
		}
		if(lock.getMin() <= 4) {
			System.out.println("PASSED! Min value is valid");
		}
		else {
			System.out.println("FAILED! Min should be <= than the min value in the combination");
		}
		lock.breakLock();
		lock.breakLock();
		lock.breakLock();
		lock.breakLock();
		lock.breakLock();
		if(lock.isBroken() && lock.getHealth() == 0) {
			System.out.println("PASSED! isBroken works");
		}
		else {
			System.out.println("FAILED! isBroken should be true when health == 0");
		}
		lock.adminMode("Elijah");
		lock.resetHealth();
		if(!lock.isBroken() && lock.getHealth() != 0) {
			System.out.println("PASSED! isBroken and health works");
		}
		else {
			System.out.println("FAILED! health should be 5 and isBroken should be false");
		}
		lock.unlock(new int[] { 4, 23, 14 });
		if(!lock.isLocked()) System.out.println("PASSED! isLocked works");
		else System.out.println("FAILED! isLocked should be false");
		lock.lock();
		if(lock.isLocked()) System.out.println("PASSED! locking works");
		else System.out.println("FAILED! lock should be locked and isLocked should be true");
		
	}

	private static void createSaveFile(String filename, int a, int b, int c) {
		File f = new File(filename);
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.println(a);
			pw.println(b);
			pw.println(c);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			
		}
	}

	private static void testLoad(String name, CombinationLock lock, String input, boolean expected) {
		if(lock.load(input) == expected) {
			System.out.println("PASSED! " + name);
		}
		else {
			System.out.println("FAILED " + name + " with output: " + !expected);
		}
		
	}

	private static void testSave(String name, CombinationLock lock, String input, boolean expected) {
		if(lock.save(input) == expected) {
				System.out.println("PASSED! " + name);
		}
		else {
				System.out.println("FAILED " + name + " with output: " + !expected);
			}
	}

	private static void testSetMin(String name, CombinationLock lock, int input, boolean expected) {
		boolean worked = lock.setMin(input);
		if (worked == expected) {
			System.out.println("PASSED! " + name);
		} else {
			System.out.println("FAILED " + name + " with output: " + worked);
		}
	}

	private static void testSetMax(String name, CombinationLock lock, int input, boolean expected) {
		boolean worked = lock.setMax(input);
		if (worked == expected) {
			System.out.println("PASSED! " + name);
		} else {
			System.out.println("FAILED " + name + " with output: " + worked);
		}
		
	}

	private static void testSetCombination(String name, CombinationLock lock, int[] input, boolean expected) {
		boolean worked = lock.setCombination(input);
		if (worked == expected) {
			System.out.println("PASSED! " + name);
		} else {
			System.out.println("FAILED " + name + " with output: " + worked);
		}
	}

	private static void testCommonAdminMethods(CombinationLock lock) {
		// I didn't make a test method for each method becuase most are just booleans and are just dependant on one factor (if admin)
		// I made seperate methods for the admin methods that have multiple variables (save, load, setMax, setMin, setCombination)
		lock.adminMode("Elijah");
		
		if (lock.resetHealth()) System.out.println("PASSED: RESET HEALTH WORKING");
		else System.out.println("FAILED: RESET HEALTH SHOULD WORK WHEN ADMIN");
		
		if (lock.setPassword("Eli")) System.out.println("PASSED: SET PASSWORD WORKING");
		else System.out.println("FAILED: SET PASSWORD SHOULD BE WORKING WHEN ADMIN");
		
		if (!Arrays.equals(lock.getCombination(), new int[] {})) { // if no admin access method should return empty array
			System.out.println("PASSED: GET COMBINATION WORKING");
		}
		else {
			System.out.println("FAILED: GET COMBINATION SHOULD NOT BE RETURNING EMPTY ARRAY");
		}
		
		if (!lock.toString().equals("Only admins can access this")) System.out.println("PASSED: TO STRING WORKING");
		else System.out.println("FAILED: TO STRING SHOULD BE PRINTING INFORMATION WHEN ADMIN");
	
		lock.setPassword("Elijah");
		lock.leaveAdmin();
		// Now check each admin method to see if they work because they shouldn't
		
		if (lock.resetHealth()) {
			System.out.println("FAILED: RESET HEALTH SHOULD NOT WORK WHEN ADMIN MODE IS LEFT");
		}
		if (lock.setPassword("Elijah")) {
			System.out.println("FAILED: SET PASSWORD SHOULD NOT WORK WHEN ADMIN MODE IS LEFT");
		}
		if (!Arrays.equals(lock.getCombination(), new int[] {})) {
			System.out.println("FAILED: GET COMBINATION SHOULD NOT WORK WHEN ADMIN MODE IS LEFT");
		}
		if (lock.save("save5.txt")) {
			System.out.println("FAILED: SAVE SHOULD NOT WORK WHEN ADMIN MODE IS LEFT");
		}
		if (lock.load("save1.txt")) { // because file exists
			System.out.println("FAILED: LOAD SHOULD NOT WORK WHEN ADMIN MODE IS LEFT");
		}
		if (!lock.toString().equals("Only admins can access this")) {
			System.out.println("FAILED: TOSTRING SHOULD RETURN \"Only admins can access this\" WHEN ADMIN MODE IS LEFT");
		}
		if (lock.setCombination(new int[] {15, 15, 15})) {
			System.out.println("FAILED: SETCOMBINATION SHOULD NOT WORK WHEN ADMIN MODE IS LEFT");
		}
		if (lock.setMax(999)) {
			System.out.println("FAILED: SETMAX SHOULD NOT WORK WHEN ADMIN MODE IS LEFT");
		}
		if (lock.setMin(999)) {
			System.out.println("FAILED: SETMIN SHOULD NOT WORK WHEN ADMIN MODE IS LEFT");
		}
	}

	private static void testUnlock(String name, CombinationLock lock, int[] input, boolean expected) {
		boolean worked = lock.unlock(input);
		if (worked == expected) {
			System.out.println("PASSED! " + name);
		} else {
			System.out.println("FAILED " + name + " with output: " + worked);
		}
	}

	private static void testPickLock(String name, CombinationLock lock, boolean expected) {
		boolean result = false;
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		// one minute timeout
		while (!result && (endTime - startTime < 6000L)) {
			result = lock.pickLock();
			endTime = System.currentTimeMillis();
		}
		if (result == expected) {
			System.out.println("PASSED! " + name);
		} else {
			System.out.println("FAILED " + name + " with output: " + result);
		}
	}

	private static void testAdmin(String name, CombinationLock lock, String input, boolean expected) {
		boolean worked = lock.adminMode(input);
		if (worked == expected) {
			System.out.println("PASSED! " + name);
		} else {
			System.out.println("FAILED " + name + " with output: " + worked);
		}
	}

	private static void testBreakLock(String name, CombinationLock lock, int minimumDamage, int maximumDamage) {
		int damage = lock.breakLock();
		if (minimumDamage <= damage && damage <= maximumDamage) {
			System.out.println("PASSED! " + name);
		} else {
			System.out.println("FAILED " + name + " with output: " + damage);
		}
	}
}
