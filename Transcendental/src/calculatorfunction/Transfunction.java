package calculatorfunction;

public class Transfunction {
	
	
	public static final double E_NUMBER = calculateE();
	
	/** Calculate the value e using the Newton series:
	 * e = ∑(1/(n!))
	 * @return the constant e.
	 */
	public static double calculateE(){
		double dResult = 1;
		double factorial = 1;
		//Choose max iterations based on precision
		for (int i = 1; i < 18; i++){
			factorial *= i;
			dResult += 1/factorial;
		}
		return dResult;
	} //End calculateE()
	
	/** Computes the natural logarithm using the Taylor Series:
	 * ln(1 + x) = x - (x^2)/2 + (x^3)/3 - (x^4)/4 +- ...
	 * -1 < x <= 1
	 * http://www.convict.lu/Jeunes/ultimate_stuff/exp_ln_2.htm
	 * @param input is the user input
	 * @return the natural logarithm of the input
	 */
	public static double ln(double dInput){
		//Test for valid input
		if (dInput <= 0){
			return 0.0/0.0; //return NaN
		}
		
		double dResult = 0;
		int floor = 0;
		
		/* Reduction to valid range of Taylor series: 0 < input < 2.
		* Floor will be added to the Taylor series result later.
		*/ 
		while (dInput > 2){
			dInput /= E_NUMBER;
			floor++;
		}
		
		/* The Taylor series computes ln of x + 1, 
		 * so we use (input - 1) for valid ln(input)
		 */ 
		double taylorin = dInput - 1;
		
		//ln(1 + x) = x - (x^2)/2 + (x^3)/3 - (x^4)/4 +- ...
		for (int i = 1; i < 1000; i++){
			if (i % 2 == 1) { //if i is odd
				dResult += powerOfInt(taylorin, i)/i;
			}
			else { //if i is even
				dResult -= powerOfInt(taylorin, i)/i;
			}
		}
		/* return the calculated result + the floor to return
		 * the natural logarithm of the original input
		 */
		return floor + dResult;
	}
	
	/** Calculates base to the power of an integer exponent
	 * Helper function for logb10()
	 * @param double base is the base of the power
	 * @param int exp is the integer exponent
	 * @return double result, the result of base^(exp)
	 */
	private static double powerOfInt(double dBase, int iExp){
		double dResult = 1;
		if (iExp == 0)
			return 1;
		if (iExp > 0){
			for (int i = 0; i < iExp; i++){
				dResult *= dBase;
			}
			return dResult;
		}
		//Repeated Code: could be improved
		else{
			for (int i = 0; i < (-1 * iExp); i++){
				dResult *= dBase;
			}
			return 1 / dResult;
		}
	} //end powerOfInt()

}
