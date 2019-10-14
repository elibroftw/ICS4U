package assignmentsDataStructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MundaneDictionary {
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("MundaneDictionary.txt"); // Defining the File with the filename
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(f);
		final Map<String, String> mundaneDictionary = new HashMap<String, String>(); // Initialize the HashMap
		while (sc.hasNextLine()) { // Start putting in the words and their definition into the HashMap
			String line = sc.nextLine();
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == ' ') {
					// Splits up the line into word and definition and puts it into the HashMap
					mundaneDictionary.put(line.substring(0, i), line.substring(i + 3));
					break;
				}
			}
		}
		sc = new Scanner(System.in);
		while (true) {
			Iterator<Entry<String, String>> it = mundaneDictionary.entrySet().iterator(); // Make the HashMap iterable
			while (it.hasNext()) {
				// Display all the words by interating through the words and definitions
				// Iterator
				Map.Entry<String, String> word = it.next();
				System.out.println(word.getKey() + ": " + word.getValue());
			}

			// Redefining words part
			String word;
			do {
				System.out.println("Enter word you would like to change: ");
				word = sc.next(); // get word user wants to change
				if (mundaneDictionary.get(word) == null) { // check if the word actually exists
					System.out.println("Word does not exist");
				}
			} while (mundaneDictionary.get(word) == null);

			// get the new definition
			System.out.println("Enter new definition: ");
			String definition = "";
			sc.nextLine();
			definition = sc.nextLine();
			mundaneDictionary.put(word, definition); // put new definition into the HasTable
		} // Repeat

	}
}
