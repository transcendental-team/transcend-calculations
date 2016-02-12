package calcfunctions;

public class Functions {
	/*TODO Implement function for E
	 * 
	 */
	private final double E = Math.E;
	
	/*Returns the natural logarithm of the input double
	 * 
	 */
	public static double ln(double x){
		return logb10(x) / logb10(E);
	}
	
	/*
	 * Returns the base-10 logarithm of the input double
	 * @param x
	 * @return log10(x)
	 */
	public static double logb10(double x){
		double log_x = 0;
		double exponent;
		
		if (x <= 0){
			return 0.0/0.0; //return NaN
		}
		if (x == 10){
			return 1;
		}
		for (int i = 0; i < 18; i++){
			exponent = 0.0;
			while (x > 10){
				x /= 10;
				exponent++;
			}
			log_x += (exponent * (powerOfInt(10, -i)));
			x = powerOfInt(x, 10);
		}
		return log_x;
	} //end logb10()
	
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
