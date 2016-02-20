package calculatorfunction;

public class Transfunction {

	/**
	 * @author Eric 
	 * Square root method using Newton's iterations
	 * 
	 * @param dInput User input
	 * @return Square root value 
	 * @version 1
	 */
	public static double sqrt(double dInput) {
		
		double epsilon = 1e-15; // error tolerance
		double dResult = dInput; // estimate of the square root
		
		if (dInput < 0) {
			// positive numbers only
			return 0.0 / 0.0;
		}

		// apply Newton iterations until desired accuracy 
		while ((dResult - dInput / dResult) > epsilon * dResult) {
			dResult = (dInput / dResult + dResult) / 2.0;
		}
		return dResult;
	}
}
