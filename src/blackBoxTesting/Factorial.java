package blackBoxTesting;

public class Factorial {
	/**
	 * Calculates the the value of n! by evaluating:
	 *    n x (n-1) x (n-2) x ... x 3 x 2 x 1
	 * Any invalid input value for n will result in a return value of -1.
	 * Invalid inputs are values of n less than 0.
	 * @param n This is the value of we are finding the factorial
	 * @return int The value of the factorial calculated. Invalid inputs are
	 */
	public static int calculate(int n) {
	    if (n < 0) {
	        return -1;
	    }
	    int value = 1;
	    for (int i = n; i > 0; i --) {
	        value *= i;
	    }
	    return value;
	}
}
