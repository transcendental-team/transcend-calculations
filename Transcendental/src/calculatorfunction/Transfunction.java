package calculatorfunction;

public class Transfunction {
	
	
	public static final double E_NUMBER = calculateE();
	
	/** Calculate the value e using the Newton series:
	 * e = ∑(1/(n!))
	 * @return the constant e.
	 */
	public static double calculateE(){
		double result = 1;
		double factorial = 1;
		//Choose max iterations based on precision
		for (int i = 1; i < 18; i++){
			factorial *= i;
			result += 1/factorial;
		}
		return result;
	} //End calculateE()
	
	/** Computes the natural logarithm using the Taylor Series:
	 * ln(1 + x) = x - (x^2)/2 + (x^3)/3 - (x^4)/4 +- ...
	 * -1 < x <= 1
	 * http://www.convict.lu/Jeunes/ultimate_stuff/exp_ln_2.htm
	 * @param input is the user input
	 * @return the natural logarithm of the input
	 */
	public static double ln(double input){
		//Test for valid input
		if (input <= 0){
			return 0.0/0.0; //return NaN
		}
		
		double result = 0;
		int floor = 0;
		
		/* Reduction to valid range of Taylor series: 0 < input < 2.
		* Floor will be added to the Taylor series result later.
		*/ 
		while (input > 2){
			input /= E_NUMBER;
			floor++;
		}
		
		/* The Taylor series computes ln of x + 1, 
		 * so we use (input - 1) for valid ln(input)
		 */ 
		double taylorin = input - 1;
		
		//ln(1 + x) = x - (x^2)/2 + (x^3)/3 - (x^4)/4 +- ...
		for (int i = 1; i < 1000; i++){
			if (i % 2 == 1) { //if i is odd
				result += powerOfInt(taylorin, i)/i;
			}
			else { //if i is even
				result -= powerOfInt(taylorin, i)/i;
			}
		}
		/* return the calculated result + the floor to return
		 * the natural logarithm of the original input
		 */
		return floor + result;
	}
	
	/** Calculates base to the power of an integer exponent
	 * Helper function for logb10()
	 * @param double base is the base of the power
	 * @param int exp is the integer exponent
	 * @return double result, the result of base^(exp)
	 */
	private static double powerOfInt(double base, int exp){
		double result = 1;
		if (exp == 0)
			return 1;
		if (exp > 0){
			for (int i = 0; i < exp; i++){
				result *= base;
			}
			return result;
		}
		//Repeated Code: could be improved
		else{
			for (int i = 0; i < (-1 * exp); i++){
				result *= base;
			}
			return 1 / result;
		}
	} //end powerOfInt()

}