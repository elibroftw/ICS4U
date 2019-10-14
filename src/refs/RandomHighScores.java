package refs;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class RandomHighScores {

	static Scanner sc = new Scanner(System.in);
	static File f;
	static PrintWriter pw;

	/**
	 * This program will generate a bunch of random numbres and keep the top ten
	 * in a highscores file
	 * 
	 * @param args
	 */

	public static void main(String[] args) throws FileNotFoundException {
		f = new File("highScores.txt");
		System.out.println("HELLO USER! Would you like to:");
		System.out.println("1: See the current top ten scores");
		System.out.println("2: Generate a hundred more random scores");
		System.out.println("3: Reset the high scores");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			viewFile();
			break;
		case 2:
			newScores();
			break;
		case 3:
			resetFile();
			break;

		default:
			System.out.println("Wrong option bub.");
			break;
		}
	}

	public static void newScores() throws FileNotFoundException {
		Scanner fileIn = new Scanner(f);
		int[] temp = new int[11];
		for (int j = 1; j < 10; j++) {
			temp[j] = fileIn.nextInt();
		}
		temp[10] = 0;
		fileIn.close();
		for (int i = 0; i < 100; i++) {
			Arrays.sort(temp);
			temp[0] = (int) (Math.random() * 100 + 1);
		}
		pw = new PrintWriter(f);
		for (int j = 0; j < 10; j++) {
			pw.println(temp[10-j]);
		}
		pw.flush();
		pw.close();
		System.out.println("DONE");
	}

	/**
	 * This method will re-populate the high scores file with ten zeroes
	 * 
	 * @throws FileNotFoundException
	 */
	private static void resetFile() throws FileNotFoundException {
		pw = new PrintWriter(f);
		for (int i = 0; i < 10; i++) {
			pw.println(0);
		}
		pw.flush();
		pw.close();
		System.out.println("FILE HAS BEEN RESET TO ZEROES");
	}

	/**
	 * This Method will display the current top ten scores (saved in a file) to
	 * the user
	 * 
	 * @throws FileNotFoundException
	 */
	private static void viewFile() throws FileNotFoundException {
		Scanner fileIn = new Scanner(f);
		while (fileIn.hasNext()) {
			System.out.println(fileIn.next());
		}
		fileIn.close();

	}

}
