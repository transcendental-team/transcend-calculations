package calcfunctions;

public class Functions {
	public static final double E = calculateE();
	
	/** Calculate the value e using the Newton series:
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
	
	/* Computes the natural logarithm using the Taylor Series:
	 * ln(1 + x) = x - (x^2)/2 + (x^3)/3 - (x^4)/4 +- ...
	 * -1 < x <= 1
	 * http://www.convict.lu/Jeunes/ultimate_stuff/exp_ln_2.htm
	 */
	public static double ln2(double input){
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
			input /= E;
			floor++;
		}
		
		/* The Taylor series computes ln of x + 1, 
		 * so we use (input - 1) for valid ln(input)
		 */ 
		double taylorin = input - 1;
		
		//ln(1 + x) = x - (x^2)/2 + (x^3)/3 - (x^4)/4 +- ...
		for (int iter = 1; iter < 1000; iter++){
			if (iter % 2 == 1) { //if i is odd
				result += powerOfInt(taylorin, iter)/iter;
			}
			else { //if i is even
				result -= powerOfInt(taylorin, iter)/iter;
			}
		}
		/* return the calculated result + the floor to return
		 * the natural logarithm of the original input
		 */
		return floor + result;
	}//end ln2()
	
	/* Calculates ln(x) based on adding 
	 * integer fractions from 1/10^n to 1/x*10^(n+1)
	 * based on:
	 * [integral](1/x)dx = ln x
	 * Very inefficient and slow!
	 * https://www.physicsforums.com/threads/ln-x-algorithm.4540/
	 */
	public static double ln3(double input){
		double result = 0;
		int power = 9;
		double divisor = powerOfInt(10, power);
		double limit = input*divisor;
		while (divisor <= limit){
			result += 1/divisor;
			divisor++;
		}
		return result;
	} //end ln3()

}
