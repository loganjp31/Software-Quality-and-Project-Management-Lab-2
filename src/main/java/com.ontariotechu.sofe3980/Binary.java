package com.ontariotechu.sofe3980;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// uncomment the following code
		
		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}
  		
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	/**
	 * Performs a bitwise logical OR operation on two binary variables.
	 * Each bit of the result is 1 if at least one corresponding input bit is 1.
	 * For more information, visit
	 * <a href="https://en.wikipedia.org/wiki/Bitwise_operation#OR">Bitwise OR</a>.
	 *
	 * @param num1 The first binary operand
	 * @param num2 The second binary operand
	 */
	public static Binary or(Binary num1, Binary num2) {

		String a = num1.getValue();
		String b = num2.getValue();

		int maxLen = Math.max(a.length(), b.length());

		a = String.format("%" + maxLen + "s", a).replace(' ', '0');
		b = String.format("%" + maxLen + "s", b).replace(' ', '0');

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < maxLen; i++) {
			if (a.charAt(i) == '1' || b.charAt(i) == '1')
				result.append('1');
			else
				result.append('0');
		}

		return new Binary(result.toString());
	}

	/**
	 * Performs a bitwise logical AND operation on two binary variables.
	 * Each bit of the result is 1 only if both corresponding input bits are 1.
	 * For more information, visit
	 * <a href="https://en.wikipedia.org/wiki/Bitwise_operation#AND">Bitwise AND</a>.
	 *
	 * @param num1 The first binary operand
	 * @param num2 The second binary operand
	 */
	public static Binary and(Binary num1, Binary num2) {

		String a = num1.getValue();
		String b = num2.getValue();

		int maxLen = Math.max(a.length(), b.length());

		a = String.format("%" + maxLen + "s", a).replace(' ', '0');
		b = String.format("%" + maxLen + "s", b).replace(' ', '0');

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < maxLen; i++) {
			if (a.charAt(i) == '1' && b.charAt(i) == '1')
				result.append('1');
			else
				result.append('0');
		}

		return new Binary(result.toString());
	}

	/**
	 * Multiplies two binary variables using binary multiplication.
	 * This operation is performed by shifting and repeatedly adding binary values.
	 * For more information, visit
	 * <a href="https://www.wikihow.com/Multiply-Binary-Numbers">Multiply-Binary-Numbers</a>.
	 *
	 * @param num1 The first binary factor
	 * @param num2 The second binary factor
	 */
	public static Binary multiply(Binary num1, Binary num2) {

		String a = num1.getValue();
		String b = num2.getValue();

		Binary result = new Binary("0");

		int shift = 0;

		for (int i = b.length() - 1; i >= 0; i--) {
			if (b.charAt(i) == '1') {
				Binary shifted = new Binary(a + "0".repeat(shift));
				result = Binary.add(result, shifted);
			}
			shift++;
		}

		return result;
	}
}	
