package assignmentsBlackBoxTesting;

public class PhoneNumberTestHarness {

	public static void main(String[] args) {

		// Simple (10)
		testPhoneNumber("spaces", "891 567 1234", true);
		testPhoneNumber("dashes", "891-765-1236", true);
		testPhoneNumber("none", "8917651236", false);
		testPhoneNumber("random characters", "asd asd abhj", false);
		testPhoneNumber("random characters v2", "456 asg abhj", false);
		testPhoneNumber("random characters v3", "456 347 abhj", false);
		testPhoneNumber("extra digits", "891376531236", false);
		testPhoneNumber("invalid seperators", "891=765~1236", false);
		testPhoneNumber("invalid seperator 1", "891+765 1236", false);
		testPhoneNumber("invalid seperator 2", "891 765-1236", false);
		testPhoneNumber("mix", "642-346 3475", false);
		testPhoneNumber("mix", "435 634-7644", false);
		
		// Area code optional (5)
		testPhoneNumber("No area code: split dash", "634-7644", true);
		testPhoneNumber("No area code: split space", "743 4555", true);
		testPhoneNumber("No area code: split none", "6518236", false);
		testPhoneNumber("No #", "242  429", false);
		testPhoneNumber("invalid separator", "651=8236", false);

		// Area code with () stuff (15)
		testPhoneNumber("AREA missing )", "(891 765-1236", false);
		testPhoneNumber("AREA missing (", "891) 765-1236", false);
		testPhoneNumber("AREA missing digits", "() 765-1236", false);
		testPhoneNumber("AREA no spaces", "(891)765-1236", false);
		
		testPhoneNumber("AREA with () mix", "(891) 765-1236", true);
		testPhoneNumber("AREA with () spaces", "(531) 789 1112", true);
		testPhoneNumber("AREA with () dashes", "(371)-651-4131", false);
		testPhoneNumber("No ( and )", "12432 644 2903", false);
		testPhoneNumber("AREA invalid seperators", "(531)=789~1112", false);
		testPhoneNumber("AREA invalid seperator 1", "(531)=789 1112", false);
		testPhoneNumber("AREA invalid seperator 2", "(531) 789~1112", false);
		
		testPhoneNumber("Area code with () High Border (998)", "(998) 765-1236", true);
		testPhoneNumber("Area code with () High Border (999)", "(999) 765 1236", true);
		testPhoneNumber("Area code with () High Border / too many digits (1000)", "(1000) 765-1236", false);
		testPhoneNumber("Area code with () too High / too many digits (1623)", "(1623) 765 1236", false);
		
		// Area code without () (4)
		testPhoneNumber("Area code High Border (998)", "998-765-1236", true);
		testPhoneNumber("Area code High Border (999)", "999 765 1236", true);
		testPhoneNumber("Area code High Border / too many digits (1000)", "1000-765-1236", false);
		testPhoneNumber("Area code too High / too many digits (1623)", "1623-765-1236", false);

		// Area code low border cases (7)
		testPhoneNumber("Area code Low Border (201)", "201-715-1636", true);
		testPhoneNumber("Area code Low Border (200)", "200-275-1336", true);
		testPhoneNumber("Area code Low Border (199)", "199 575 1536", false);
		testPhoneNumber("Area code Low Border (199)", "(199) 575 1536", false);
		testPhoneNumber("Area code too Low (120)", "120 575 1536", false);
		testPhoneNumber("Area code too Low (120)", "(120) 575 1536", false);
		testPhoneNumber("Area code not enough digits (19)", "19-575-1536", false);

		// Prefix Border cases (9)
		testPhoneNumber("Prefix low Border (201)", "732 201 5432", true);
		testPhoneNumber("Prefix low Border (200)", "732 200 5432", false);
		testPhoneNumber("Prefix low Border (199)", "732 199 5432", false);
		testPhoneNumber("Prefix too low (100)", "732 100 5432", false);
		testPhoneNumber("Prefix not enough digits (12)", "732 12 5432", false);

		testPhoneNumber("Prefix high Border (998)", "732 998 5432", true);
		testPhoneNumber("Prefix high Border (999)", "732 999 5432", true);
		testPhoneNumber("Prefix high Border / too many digits (1000)", "732 1000 5432", false);
		testPhoneNumber("Prefix too high (2145)", "732 2145 5432", false);

		// Suffix (4)
		testPhoneNumber("Suffix low border", "523 235 0000", true);
		testPhoneNumber("Suffix high border", "523 235 9999", true);
		testPhoneNumber("Suffix not enough digits", "523 235 324", false);
		testPhoneNumber("Suffix too many digits", "523 234 32224", false);

	}

	private static void testPhoneNumber(String name, String input, boolean expected) {
		boolean output = PhoneNumber.isValidPhone(input);
		if (output == expected) {
			System.out.println("PASSED! " + name);
		} else {
			System.out.println("FAILED " + name + " with output: " + output);
		}
	}

}
