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
		//Choose max iations based on precision
		for (int i = 1; i < 18; i++){
			factorial *= i;
			result += 1/factorial;
		}
		return result;
	} //End calculateE()
	
	/** Returns the natural logarithm of the input double.
	 * 	Based on the identity log(x)/log(y) = log_base_y(x)
	 * @param the user input, a double
	 * @return the natural logarithm of the input
	 */
	public static double ln(double input){
		return logb10(input) / logb10(E_NUMBER);
	}
	
	/**
	 * Returns the base-10 logarithm of the input double
	 * 
	 * http://www.everything2.com/title/Logarithm+algorithm
	 * @param input
	 * @return log base 10 of input
	 */
	public static double logb10(double input){	
		if (input <= 0){
			return 0.0/0.0; //return NaN
		}
		if (input == 10){
			return 1;
		}
		if (input == 1){
			return 0;
		}
		if (input < 1){  // take the log of the reciprocal and invert the sign
			return (-1) * logHelper(10, 1/input);
		}
		return logHelper(10, input);
	}
	
	/**
	 * Helper method for log function with an integer base
	 * @param base the base of the logarithm being calculated
	 * @param input is an input validated by the main logb10 method 
	 * @return the l
	 */
	private static double logHelper(int base, double input){
		double result = 0;
		double floor;
		/* In each iation, divide input by base until input < base. 
		 * floor keeps track of necessary number of divisions.
		 * Number of iations can be changed to control accuracy.
		 */
		for (int i = 0; i < 18; i++){
			floor = 0.0;
			while (input > base){
				input /= base;
				floor++;
			}
			result += (floor * (powerOfInt(base, -i)));
			input = powerOfInt(input, base);
		}
		return result;
	}
	
	/** Calculates base to the power of an integer exponent
	 * Helper function for logb10()
	 * @param double base is the base of the exponent, 
	 * @param int exp is the integer expoonent
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
		else{
			for (int i = 0; i < (-1 * exp); i++){
				result *= base;
			}
			return 1 / result;
		}
	} //end powerOfInt()
}