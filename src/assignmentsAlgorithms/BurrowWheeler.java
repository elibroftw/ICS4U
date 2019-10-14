package assignmentsAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BurrowWheeler {
	public static void main(String[] args) {
		testTransform("^BANANA|", "BNN^AA|A", "Wikipedia example");
		testTransform("AAAAA", "AAAAA", "If every character is the same");
		testTransform("AFEDCB", "BCDEFA", "Rows will already be in sorted order");
		testTransform("0987654321", "1234567890", "testing if numbers get sorted properly");
		testTransform("\n\n\nA", "A\n\n\n", "testing escape character");
		testTransform("123ABC","C123AB","includes both letters and numbers");
		testTransform("hello","hoell","see if lowercase works");		
		testTransform("DropBox","pxrBoDo","upper and lowercase");
		testTransform("Password123","d123Prwoass","Uppercase, lowercase, numbers");			
		// special character > numbers > uppercase > lowercase
		testTransform("Aa!1", "a!1A", "special character included");
	}

	public static String transform(String s) {
		int length = s.length();
		List<String> table = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			table.add(s);
			s = s.charAt(length - 1) + s.substring(0, length - 1);
		}
		table.sort(Comparator.naturalOrder());
		String output = "";
		for (String word : table) {
			output += word.charAt(length - 1);
		}
		return output;
	}

	public static void testTransform(String input, String expected, String description) {
		String output = transform(input);
		if (output.equals(expected)) {
			System.out.println("PASSED! " + description);
		} else {
			System.out.println("FAILED " + description + " with output: " + output);
		}
	}
}
