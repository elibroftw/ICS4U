package assignmentsDataStructures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class OtherFunctions {

	
	public static void main(String[] args) {
		System.out.println(infixToPostfix("1 + 4 * ( 2 + ( 1 + 1 + 1 ) )"));
	}
	/**
	 * Converts a decimal number into Binary
	 * @param n an integer representing the number to convert
	 * @return binaryValue representing n in binary
	 */
	public static String toBoolean(int n) {
		if (n == 0) { // base case of 0
			return "0";
		}
		Stack<Integer> binaries = new Stack<>();
		while (n != 1) {
			binaries.add(n % 2); // Adding the remainder
			n = n / 2;
		}
		String output = "";
		while (binaries.size() > 0) {
			output += binaries.pop(); // Integer gets turned into String
		}
		return "1" + output; // "1" to account for the last 1
	}

	/**
	 * Calculates values of the fibonacci sequence and returns them
	 * Assumes that 1, 1 are the first two terms
	 * @param n represents the first nth terms that the function will return
	 * @return a list of the first nth terms
	 */
	public static String fib(int n) {
		if (n <= 0) {
			return "";
		}
		Queue<Integer> fib = new LinkedList<>();
		int uno = 1;
		int dos = 1;
		fib.add(dos);
		for (int i = 2; i < n; i++) {
			int tres = uno + dos;
			fib.add(tres);
			uno = dos;
			dos = tres;
		}
		String output = "1";
		for (int i = 1; i < n; i++) {
			output += ", " + fib.remove();
		}
		return output;
	}

	/**
	 * Checks if an expression of a String has balanced brackets
	 * Balanced meaning a "bracket" that is opened should be also closed before any previous opended bracket
	 * @param input The string that needs to be checked
	 * @return boolean value representing if the input is balanced
	 */
	public static boolean isBalanced(String input) {
		Stack<Character> brackets = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				brackets.add(c);
			} else {

				char x = brackets.pop();
				switch (c) {
				case ')':
					if (x != '(')
						return false;
					break;
				case '}':
					if (x != '{')
						return false;
					break;
				case ']':
					if (x != '[')
						return false;
					break;
				}
			}
		}
		return true;
	}

	/**
	 * Takes an infix expression and converts it into postfix format
	 * @param expression The infix expression
	 * @return the converted postfix expression
	 */
	public static String infixToPostfix(String expression) {
		Stack<Character> operators = new Stack<>();
		String output = "";
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			switch (c) {
			case '*':
				if (operators.empty()) {
					operators.push(c);
					break;
				}
				while(operators.peek() == '*' || operators.peek() == '/') {
					output = output + operators.pop() + ' ';
					if (operators.empty()) break;
				}
				operators.push(c);
				break;
			case '/':
				if (operators.empty()) {
					operators.push(c);
					break;
				}
				while(operators.peek() == '*' || operators.peek() == '/') {
					output = output + operators.pop() + ' ';
					if (operators.empty()) break;
				}
				operators.push(c);
				break;
			case '(':
				operators.push(c);
				break;
			case '+':
				if (operators.empty()) {
					operators.push(c);
					break;
				}
				while(operators.peek() == '*' || operators.peek() == '/') {
					output = output + operators.pop() + ' ';
					if (operators.empty()) break;
				}
				operators.push(c);
				break;
			case '-':
				if (operators.empty()) {
					operators.push(c);
					break;
				}
				while(operators.peek() == '*' || operators.peek() == '/') {
					output = output + operators.pop() + ' ';
					if (operators.empty()) break;
				}
				operators.push(c);
				break;
			case ')':
				while (true) {
					char operator = operators.pop();
					if (operator == '(') break;
					output = output + operator + ' ';
				} break;
			default:
				output = output + c + ' ';
			}
		}
		while (operators.size() > 0) {
			output = output + operators.pop() + ' ';
		}
		return output.trim();
	}

	/**
	 * Takes in a postfix expression and solves it
	 * @param expression The postfix expression
	 * @return calculated value of the expression
	 */
	public static Double postfixCalculator(String expression) {
		// x y ^ 5 z * / 10 +
		// x ^ y / (5 * z) + 10
		// 10.5 2 ^ 5 35 * / 10.0 +
		// (10.5 ^ 2 - 5) / 35 + 10.0 = 10.63
		Stack<Double> numbers = new Stack<>();
		String[] opers = expression.split("\\s"); // splits the expression into parts
		for (String string : opers) {
			try{
				numbers.push(Double.parseDouble(string));
			} catch (Exception e) {
				double operand2 = numbers.pop(); // because the second operand was last to be added
				double operand1 = numbers.pop();
				switch (string) {
				case "/": operand1 /= operand2; break;
				case "*": operand1 *= operand2; break;
				case "+":operand1 += operand2;	break;
				case "-": operand1 -= operand2;	break;
				case "^": operand1 = Math.pow(operand1, operand2);
				}
				numbers.push(operand1);
			}			
		}
		return numbers.pop();
	}
}

