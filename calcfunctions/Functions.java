package calcfunctions;

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

}
