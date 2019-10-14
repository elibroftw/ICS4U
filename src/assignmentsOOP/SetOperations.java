package assignmentsOOP;

import java.util.Set;
import java.util.TreeSet;

public class SetOperations {

	public static void main(String[] args) {
		String mySetOperation = "[]*[]";
		String symbol = "";
		if (mySetOperation.contains("+")) symbol = "\\+";
		else if (mySetOperation.contains("-")) symbol = "\\-";
		else if (mySetOperation.contains("*")) symbol = "\\*";
		if (symbol.equals("")) {
			System.out.println(mySetOperation);
		}
		else {
			System.out.println(parseSetOperation(mySetOperation, symbol));
		}		
	}

	private static String parseSetOperation(String setOperation, String symbol) {
		String [] sets = setOperation.split(symbol);
		Set<Integer> set1 = parseSet(sets[0].trim());
		Set<Integer> set2 = parseSet(sets[1].trim());
		if (symbol.equals("\\*")) set1.retainAll(set2);
		else if (symbol.equals("\\-")) set1.removeAll(set2);
		else set1.addAll(set2);
		return set1.toString();
	}

	private static Set<Integer> parseSet(String set) {
		String noBrackets = set.substring(1, set.length()-1);
		Set<Integer> mySet = new TreeSet<Integer>();
		try {
			String [] vals = noBrackets.split(",");
			for (int i = 1; i < vals.length; i++) {
				Integer parsedInteger = Integer.parseInt(vals[i-1].trim());
				mySet.add(parsedInteger);
			}
		}
		catch (Error NumberFormatException){
			
		}	
		return mySet;
	}

}
