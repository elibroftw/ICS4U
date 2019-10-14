package assignmentsOOP;

import java.util.Scanner;

public class LockClient{
	private static CombinationLock myLock = new CombinationLock();
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {		
		int choice = 0;
		while(choice != 9) {
			System.out.println("What would you like to do (Enters an integer)");
			System.out.println("1. Enter Admin Section");
			System.out.println("2. Check lock status");
			System.out.println("3. Attempt to open the lock with a 3-digit code");
			System.out.println("4. Attempt to pick the lock");
			System.out.println("5. Check the lock's health");
			System.out.println("6. Attempt to break the lock physically");
			System.out.println("7. Brute force attack");
			System.out.println("8. Lock the lock");
			System.out.println("9. Exit");
			choice = sc.nextInt();
			switch(choice){
			case 1:
				System.out.println("Enter Admin Password");
				String pw = sc.next();
				if(myLock.adminMode(pw)) adminMode();
				else System.out.println("Incorrect Password");
				break;
			case 2:
				if(myLock.isBroken()) {
					System.out.println("LOCK IS BROKEN CANNOT DO THIS OPTION");
				}
				else System.out.println("Lock locked: "+myLock.isLocked()); break;
			case 3:
				if(myLock.isBroken()) {
					System.out.println("LOCK IS BROKEN CANNOT DO THIS OPTION");
				}
				else guessCode(); break;
			case 4:
				if(myLock.isBroken()) {
					System.out.println("LOCK IS BROKEN CANNOT DO THIS OPTION");
				}
				else attemptPick(); break;
			case 5:
				if(myLock.isBroken()) {
					System.out.println("LOCK IS BROKEN");
				}
				else System.out.println("Locks health is: " + myLock.getHealth()/5*100 + "%"); break;
			case 6:
				if(myLock.isBroken()) {
					System.out.println("LOCK IS ALREADY BROKEN CANNOT DO THIS OPTION");
				}
				else attemptBreak(); break;
			case 7:
				if(myLock.isBroken()) {
					System.out.println("LOCK IS BROKEN CANNOT DO THIS OPTION");
				}
				else bruteForce(); break;
			case 8: myLock.lock(); break;
			case 9: System.out.println("Bye"); break;
			default: System.out.println("That was an invalid choice, please try again");
			}
		}
		sc.close();
	}

	private static void bruteForce() {
		int attempts = 0;
		int A = 0, B = 0, C = 0;
		boolean access = false;
		for(int a = myLock.getMin(); a <= myLock.getMax(); a++) {
			for(int b = myLock.getMin(); b <= myLock.getMax(); b++) {
				for(int c = myLock.getMin(); c <= myLock.getMax(); c++) {
					attempts += 1;
					access = myLock.unlock(new int[] {a, b, c});
					A = a;
					B = b;
					C = c;
					if(access) break;
				}
				if(access) break;
			}
			if(access) break;
		}
		System.out.println("The combination is: " + A + ", " + B + ", " + C);
		System.out.println("Number of attempts: " + attempts);
	}

	private static void attemptBreak() {
		System.out.println("you did " + myLock.breakLock() + " damage");
		System.out.println("Locks health is: " + myLock.getHealth()/5.0 *100 + "%");
	}

	private static void attemptPick() {
		if(myLock.pickLock()) {
			System.out.println("Lock has been picked");
		}
		else {
			System.out.println("Lock pick did not succeed");
		}
	}

	private static void guessCode() {
		System.out.print("Enter first number: ");
		int a = sc.nextInt();
		System.out.print("Enter second number: ");
		int b = sc.nextInt();
		System.out.print("Enter third number: ");
		int c = sc.nextInt();
		boolean unlocked = myLock.unlock(new int[] {a, b, c});
		if(unlocked) System.out.println("Lock is unlocked");
		else System.out.println("Lock not unlocked");
	}

	private static void adminMode() {
		int choice = 0;
		while(choice != 10) {
			System.out.println("You are in admin mode\nWhat would you like to do (Enter an integer)");
			System.out.println("1. See the lock combination");
			System.out.println("2. Reset the lock's health");
			System.out.println("3. Change the min");
			System.out.println("4. Change the max");
			System.out.println("5. Change the combination");
			System.out.println("6. Change the password");
			System.out.println("7. Output of the lock (toString())");
			System.out.println("8. Save the combo to a file");
			System.out.println("9. Load a combo from a file");
			System.out.println("10. Exit admin mode");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				int[] combination = myLock.getCombination();
				System.out.println(
						"The lock combination is: " + combination[0] + ", " + combination[1] + ", " + combination[2]);
				break;
			case 2:
				myLock.resetHealth();
				System.out.println("Health has been reset");
				break;
			case 3:
				System.out.print("Enter new min value: ");
				int newMin = sc.nextInt();
				boolean minWorked = myLock.setMin(newMin);
				if (minWorked) System.out.println("Min changed");
				else System.out.println("Failed to change min");
				break;
			case 4:
				System.out.print("Enter new max value: ");
				int newMax = sc.nextInt();
				boolean maxWorked = myLock.setMax(newMax);
				if (maxWorked) System.out.println("max changed");
				else System.out.println("Failed to change max");
				break;
			case 5:
				System.out.println("Remember to stay within the min (" + myLock.getMin() + ") and max (" + myLock.getMax() + ")");
				System.out.print("Enter first number: ");
				int a = sc.nextInt();
				System.out.print("Enter second number: ");
				int b = sc.nextInt();
				System.out.print("Enter third number: ");
				int c = sc.nextInt();
				boolean changeCombinationWorked = myLock.setCombination(new int[] {a, b, c});
				if (changeCombinationWorked) System.out.println("Combination changed!");
				else System.out.println("Combination could not be changed");
				break;
			case 6:
				System.out.println("Enter new admin password: ");
				String password = sc.next();
				myLock.setPassword(password);
				break;
			case 7:
				System.out.println(myLock);
				break;
			case 8:
				System.out.print("Enter filename to save combination to: ");
				String saveFilename = sc.next();
				boolean saveWorked = myLock.save(saveFilename);
				if (saveWorked) System.out.println("Save successful");
				else System.out.println("Save unsuccessful");
				break;
			case 9:
				System.out.print("Enter filename to load combination from: ");
				String loadFilename = sc.next();
				boolean loadWorked = myLock.load(loadFilename);
				if (loadWorked) System.out.println("Load successful");
				else System.out.println("Load unsuccessful");
				break;
			case 10:
				System.out.println("Exiting admin mode");
				myLock.leaveAdmin();
				break;
			default: System.out.println("That option is invalid");
			}
		}		
	}
}
