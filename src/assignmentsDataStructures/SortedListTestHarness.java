package assignmentsDataStructures;

public class SortedListTestHarness {
	public static void main(String[] args) {
			SortedList<Integer> sortedList = new SortedList<>();
			testSortedListInts("Size of 0", sortedList.size(), 0);
			sortedList.add(11);
			testSortedListInts("getting the only item of the list", sortedList.get(0), 11);
			testSortedListInts("Size of 1", sortedList.size(), 1);
			testSortedList("Testing if list can add a number", sortedList, "[11]");
			for(int i = 0; i < 7; i++) {
				sortedList.add(i);
			}
			testSortedList("Testing if the list is sorted (size = default size - 2", sortedList, "[0, 1, 2, 3, 4, 5, 6, 11]");
			// default size is 10
			sortedList.add(7);
			testSortedList("Testing if the list is sorted (size = default size - 1", sortedList, "[0, 1, 2, 3, 4, 5, 6, 7, 11]");
			sortedList.add(8);
			testSortedList("Testing if list is sorted (size = default size)", sortedList, "[0, 1, 2, 3, 4, 5, 6, 7, 8, 11]");
			sortedList.insert(99);
			testSortedList("Testing if insert works, and also (size = 1 + default size)", sortedList, "[0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 99]");
			testSortedListInts("99 should be returned if get is working properly", sortedList.get(10), 99);
			sortedList.add(35);
			testSortedList("Adding between 2 numbers, and also (size = 2 + default size)", sortedList, "[0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 35, 99]");
			testSortedListInts("99 should still be returned if get is working properly", sortedList.get(11), 99);
			testSortedListInts("size of the list in a normal setting", sortedList.size(), 12);
	}
	
	/**
	 * Checks if the given sorted list is equal to the expected
	 * @param name name of the test
	 * @param sortedList the sortedList that was tested on
	 * @param expected the expected sortedList as a String
	 */
	private static void testSortedList(String name, SortedList<Integer> sortedList, String expected) {
		if (sortedList.toString().equals(expected)) { // Yes I added a toString method to make it easier
            System.out.println("PASSED! " + name);
        } else {
            System.out.println("FAILED "+name+" with output: " + sortedList.toString());
        }
    }
	
	private static void testSortedListInts(String name, int var, int expected) {
		if (var == expected) {
            System.out.println("PASSED! " + name);
        } else {
            System.out.println("FAILED "+name+" with output: " + var);
        }
    }
	
	
}
