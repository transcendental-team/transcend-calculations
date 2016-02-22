package calculatorfuntion;

<<<<<<< HEAD
public class Functions {
	// Square Root Methods
	/** Bakhshali approximation
	 * 
	 * Implementation from: Code Project
	 * http://www.codeproject.com/Articles/69941/
	 * Best-Square-Root-Method-Algorithm-Function-Precisi
	 * 
	 * @param m input value
	 * @return square root value
	 */
	public static double sqrt1(double m) {
		
		if (m < 0) {
			System.out.println("Error");
			return -1;
		}
		int i = 0;
		
		while ((i * i) <= m)
			i++;
		i--;
		double d = m - i * i;
		double p = d / (2 * i);
		double a = i + p;
		
		return a - (p * p) / (2 * a);
	}
	
	/** Newton's Iteration 
	 * 	
	 * Implementation from: Princeton University
	 * Introduction to Programming in Java
	 * http://introcs.cs.princeton.edu/java/13flow/Sqrt.java.html
	 * 
	 * @param c input value
	 * @return square root value
	 */
	public static double sqrt2(double c) {
		
		if (c < 0) {
			System.out.println("Error");
			return -1;
		}
        double epsilon = 1e-15;    // relative error tolerance
        double t = c;              // estimate of the square root of c

        // repeatedly apply Newton update step until desired precision is achieved
        while ((t - c/t) > epsilon*t) {
            t = (c/t + t) / 2.0;
        }
        return t;
    }
=======
public class Transfunction {
>>>>>>> origin/powerfunction

    /**
     * @author Xindi
     * Power function of 10 using Taylor expansion
     *
     * @param dInput input value
     * @return Power function of 10 value
     * @version 3
     */
    public static double powerFunctionOf10(double dInput){

        double dResult = 1.0;
        double term = 1.0;
        double xln10 = dInput * Logarithm.ln(10.0);
        int i = 1;

        if(dInput<0){
            return 1/ powerFunctionOf10(-dInput);
        }

        // apply Taylor series until getting the desired accuracy
        while(term>1e-12){

            term *= xln10/i;
            dResult += term;
            i++;

        }

        // handle floating-point numbers by default
        double tempSum = dResult%1;

        if (tempSum>0.99999){
            dResult = dResult-tempSum+1;
        }else if(tempSum<0.000001){
            dResult -= tempSum;
        }
        return dResult;
    }
}
