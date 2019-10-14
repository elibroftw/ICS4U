package assignmentsDataStructures;

public class SortedList<E> {
	private int[] backedData = new int[10];
	private int size = 0;

	/**
	 * Adds an integer to the list in a sorted order
	 * 
	 * @param k
	 *            integer to be added
	 */
	public void add(int k) {
		size++;
		if (size == 1) {
			backedData = new int[] { k };
			return;
		}
		int index = 0;
		for (int i : backedData) {
			if (i >= k) {
				break;
			}
			index++;
		}
		int[] temp = backedData;
		backedData = new int[size];
		for (int i = 0; i < index; i++) {
			backedData[i] = temp[i];
		}
		backedData[index] = k;
		for (int i = index + 1; i < size; i++) {
			backedData[i] = temp[i - 1];
		}
	}

	/**
	 * Calls add(int k)
	 * @param k
	 */
	public void insert(int k) {
		add(k);
	}

	/**
	 * Returns the size of the list
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
     * Returns the element at index i
     * @param i - the index of the element
     * @return the item at the specified index.
     */
	public int get(int index) {
		return backedData[index];
	}

	/**
	 * Returns the String friendly version of the list
	 * @return comma seperated list as a string
	 */
	public String toString() {
		String output = "[";
		for (int i = 0; i < this.size(); i++) {
			output += ", " + this.get(i);
		}
		return output.replaceFirst(", ", "") + "]";
	}
}
