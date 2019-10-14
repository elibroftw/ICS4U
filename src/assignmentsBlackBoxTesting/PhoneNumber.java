package assignmentsBlackBoxTesting;

/**
 * @author Elijah Lopez
 */
public class PhoneNumber {
	public static void main(String[] args) {

	}
	
	public static boolean isValidPhone(String phoneNumber){
		if (phoneNumber.length() == 14) return length14(phoneNumber);
		if (phoneNumber.length() == 12) return length12(phoneNumber);
		if (phoneNumber.length() == 8) return length8(phoneNumber);
		return false;
	}
	
	
	/**
	 * For numbers with Area code in parentheses
	 * ex. (213) 424 1245
	 * @param phoneNumber
	 * @return
	 */
	private static boolean length14(String phoneNumber) {
		if (!phoneNumber.contains("(") || !phoneNumber.contains(")")) return false;
		if (!phoneNumber.substring(5, 6).equals(" ")) return false;
		if (!phoneNumber.substring(9, 10).equals(" ") && !phoneNumber.substring(9, 10).equals("-") ) {
			return false;
		}
		String areaCode = phoneNumber.substring(1, 4);
		if(!testAreaCode(areaCode)) return false;
		
		String prefixCode = phoneNumber.substring(6, 9);
		if(!testPrefixCode(prefixCode)) return false;
		
		String suffixCode = phoneNumber.substring(10);
		if(!testSuffixCode(suffixCode)) return false;
		
		return true;
	}
	
	/**
	 * For numbers that have an area code without parentheses
	 * ex. 213 456 7890
	 * @param phoneNumber
	 * @return
	 */
	private static boolean length12(String phoneNumber) {
		if (phoneNumber.contains("-") && phoneNumber.contains(" ")) return false;
		if(!phoneNumber.contains(" ") && !phoneNumber.contains("-")) return false;;
		
		String seperatorOne = phoneNumber.substring(3, 4);
		if(!seperatorOne.equals(" ") && !seperatorOne.equals("-")) return false;
		
		String seperatorTwo = phoneNumber.substring(7, 8);
		if(!seperatorTwo.equals(" ") && !seperatorTwo.equals("-")) return false;
		
		String areaCode = phoneNumber.substring(0, 3);
		if(!testAreaCode(areaCode)) return false;
		
		String prefixCode = phoneNumber.substring(4, 7);
		if(!testPrefixCode(prefixCode)) return false;
		
		String suffixCode = phoneNumber.substring(8);
		if(!testSuffixCode(suffixCode)) return false;
		
		return true;
	}
	
	/**
	 * For numbers without Area codes
	 * ex. 991 5486
	 * @param phoneNumber
	 * @return
	 */
	private static boolean length8(String phoneNumber) {
		if (phoneNumber.contains("-") && phoneNumber.contains(" ")) return false;
		if(!phoneNumber.contains(" ") && !phoneNumber.contains("-")) return false;
		String prefixCode = phoneNumber.substring(0, 3);
		if(!testPrefixCode(prefixCode)) return false;
		
		String suffixCode = phoneNumber.substring(4);
		if(!testSuffixCode(suffixCode)) return false;
		
		return true;
	}
	
	/**
	 * Checks if an area code is valid
	 * @param areaCode
	 * @return
	 */
	private static boolean testAreaCode(String areaCode) {
		try{
			int code = Integer.parseInt(areaCode);
			if(code < 200) {
				return false;
			}
			return true;
		}
		catch (NumberFormatException e){
			return false;
		}
	}
	
	/**
	 * Checks if a prefix code is valid
	 * @param prefixCode
	 * @return
	 */
	private static boolean testPrefixCode(String prefixCode) {
		try {
			int code = Integer.parseInt(prefixCode);
			if(code <= 200) {
				return false;
			}
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}		
	}
	
	/**
	 * Check if a suffix code is valid
	 * @param suffixCode
	 * @return
	 */
	private static boolean testSuffixCode(String suffixCode) {
		try {
			Integer.parseInt(suffixCode);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}
