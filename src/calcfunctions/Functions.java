package calcfunctions;

public class Functions {
	public static final double E = calculateE();
	
	/* Calculate the value e using the Newton series:
	 * e = ∑(1/(n!))
	 */
	public static double calculateE(){
		double result = 1;
		double factorial = 1;
		for (int i = 1; i < 18; i++){
			factorial *= i;
			result += 1/factorial;
		}
		return result;
	} //End calculateE()
	
	/*Returns the natural logarithm of the input double
	 * 
	 */
	public static double ln(double x){
		return logb10(x) / logb10(E);
	}
	
	/**
	 * Returns the base-10 logarithm of the input double
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
	 * @param base
	 * @param input is an input validated by the main method 
	 * @return the l
	 */
	public static double logHelper(int base, double input){
		double result = 0;
		double floor;
		for (int iter = 0; iter < 18; iter++){
			floor = 0.0;
			while (input > base){
				input /= base;
				floor++;
			}
			result += (floor * (powerOfInt(base, -iter)));
			input = powerOfInt(input, base);
		}
		return result;
	}
	
	/* Calculates base to the power of an integer exponent
	 * Helper function for logb10()
	 * @params double base, int exp
	 * @return double result, the result of base^(exp)
	 */
	private static double powerOfInt(double base, int exp){
		double result = 1;
		if (exp == 0)
			return 1;
		if (exp > 0){
			for (int iter = 0; iter < exp; iter++){
				result *= base;
			}
			return result;
		}
		//Repeated Code: could be improved
		else{
			for (int iter = 0; iter < (-1 * exp); iter++){
				result *= base;
			}
			return 1 / result;
		}
	} //end powerOfInt()
}
