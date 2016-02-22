package calculatorfunction;

public class Transfunction {

	public static final double PI = pi(); //Pi constant

	/**
	 * @param num negative or positive number.
	 * @return the absolute value of the number
	 */
	private static double abs(double num) {
		if (num < 0){
			return -num;
		}
		return num;
	}

	/**
	 * Calculate pi using Leibniz formula.
	 * source: https://goparallel.sourceforge.net/calculate-pi-with-custom-c-class/
	 * @return the constant pi
	 */
	public static final double pi() {
		double pi = 0;
		double sum = 1;
		int i = 0;
		while (abs(sum) > (1e-8)) {//best approximation with 1e-8
			sum = ((i % 2 == 0) ? 1 : -1) / (2 * i + 1.0); //sum of fractional elements
			pi += sum;
			i++;
		}
		return pi * 4;
	}

	/**
	 * Method to Compute the sine of the angle theta using the taylor series expansion.
	 * sin(x) = x - (x^3/3!) + (x^5/5!) - (x^7/7!) + ...
	 * @param dRadians value given in Radians by user.
	 * @return dResult the sine of the angle theta which was given in Radians by user.
	 * @version 4
	 */
	public static final double sine(double dRadians) {

		dRadians %= 2*PI; //convert dRadians to an angle between -2 PI and 2 PI
		double dResult = dRadians; //numerator initially
		double factorial = 1; //denominator factorial initially.
		double radianMultiple = dRadians * dRadians;
		//iterate to the approximation of positive infinity of type double. 
		for (int i = 0; factorial < Double.POSITIVE_INFINITY; i++) {
			factorial *= (2 * i + 2) * (2 * i + 3); //best factorial approximation denominator with each iteration. 
			dRadians *= radianMultiple; //numerator power increase by 2
			//Sum of fractional components of the Taylor series expansion.
			dResult += (i % 2 == 0 ? -1 : 1) * dRadians / factorial;
		}

		return dResult;

	}

}
