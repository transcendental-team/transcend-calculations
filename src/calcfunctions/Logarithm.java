package calcfunctions;

public class Logarithm {
	
	public static final double E_NUMBER = calculateE();
	
	/** Calculate the value e using the Newton series:
	 * e = ∑(1/(n!))
	 */
	public static double calculateE(){
		double result = 1;
		double factorial = 1;
		//Choose max iterations based on precision
		for (int iter = 1; iter < 18; iter++){
			factorial *= iter;
			result += 1/factorial;
		}
		return result;
	} //End calculateE()
	
	/** Returns the natural logarithm of the input double.
	 * 	Based on the identity log(x)/log(y) = log_base_y(x)
	 * @param input
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
	 * @param base
	 * @param input is an input validated by the main logb10 method 
	 * @return the l
	 */
	private static double logHelper(int base, double input){
		double result = 0;
		double floor;
		/* In each iteration, divide input by base until input < base. 
		 * floor keeps track of necessary number of divisions.
		 * Number of iterations can be changed to control accuracy.
		 */
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
