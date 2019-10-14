package blackBoxTesting;

public class FactorialTestHarness {
	public static void main(String args[]) {
        // Blackbox Tests
        testFactorial("Negative Border Case (-1)", -1, -1);
        testFactorial("0", 0, 1);
        testFactorial("1", 1, 1);
        testFactorial("Non-1 Positive Integer", 5, 120);
        testFactorial("Invalid Number (-5)", -5, -1);
    }
    
    private static void testFactorial(String name, int input, int expected) {
        int output = Factorial.calculate(input);
        if (output == expected) {
            System.out.println("PASSED! " + name);
        } else {
            System.out.println("FAILED "+name+" with output: " + output);
        }
    }
}
