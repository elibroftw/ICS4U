package algorithms;

public class Sorts {

    public static void main(String[] args) {
    	int[] temp = new int[] {1, 2, 3, 4, 5};
        insertionSort(temp); // 10 comparisons
        temp = new int[] {1, 2, 3, 4, 5}; 
        selectionSort(temp); // finsihes with 0 comparisons
        temp = new int[] {3, 4, 5, 1, 2};
        insertionSort(temp);
        temp = new int[] {3, 4, 5, 1, 2};
        selectionSort(temp);
        temp = new int[] {5, 4, 3, 2, 1}; 
        insertionSort(temp); // always takes the same number of times
        temp = new int[] {5, 4, 3, 2, 1};
        selectionSort(temp); //slowest when array is reversed
        // selection sort is faster
    }
    
    /**
     * Takes the smallest value to the front each time
     * @param array
     */
    public static void insertionSort(int[] array){
    	int r = 0;
        for (int j = 0; j < array.length - 1; j++) {
			int v = j;
			for (int i = j + 1; i < array.length; i++) {
				r++;
				if(array[i] < array[v]) v = i;
			}
			if(v != j) {
				int temp = array[v];
				array[v] = array[j];
				array[j] = temp;
			}
		}
        System.out.println(r);
    }
    
    /**
     * swaps smallest item in unsorted section and moves it to the front of the unsorted parts of a list
     * @param array
     * @return
     */
    public static void selectionSort(int[] array){
    	int r = 0;
        for(int i = 1; i <array.length; i ++){
            int v = array[i];
            int j = i;
            while(j > 0 && v < array[j - 1]){
            	r++;
                array[j] = array[j - 1];
                j -= 1;
            }
            array[j] = v;
        }
        System.out.println(r);
    }
 
    public static int search(int[] sortedArray, int key) {
    	int max = sortedArray.length;
    	int min = 0;
    	while(min < max) {
    		int mid = (int) Math.floor((max + min)/2);
    		if(sortedArray[mid] < key) {
    			 min = mid + 1;
    		}
    		else {
    			max = mid;
    		}
    	}
    	if(sortedArray[min] == key) {
    		return min;
    	}
    	return -1;
    }
}