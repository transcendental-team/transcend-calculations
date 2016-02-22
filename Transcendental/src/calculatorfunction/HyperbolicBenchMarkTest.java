package calculatorfunction;

import java.lang.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.function.DoubleFunction;


public class HyperbolicBenchMarkTest {

	public static void main(String[] args) {
		   double x = 150;

		   long start = System.currentTimeMillis(); 

		   // print the hyperbolic sine for these doubles
		   System.out.println("sinh(" + x + ")=" + Math.sinh(x));
		   
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);
			
			
			
			
			start = System.currentTimeMillis(); 
			
			System.out.println("Taylor series sinh(" + x + ")=" + getSinhTaylor(x));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);

			
			
			
			start = System.currentTimeMillis(); 
			
			System.out.println("Taylor series exponential sinh(" + x + ")=" + sinhTaylorExp(x));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);
			
			
			start = System.currentTimeMillis(); 
			
			System.out.println("Pade appriximation exp(" + x + ")=" + padeExp(x));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);

	}



	/**method hyperbolic sinh using talyor expansion 
	 * Compute the sinh of the angle dRadian using the taylor series expansion with the infinite expansion term
	 * till the last expansion term is less than e-20
	 * formula: sinh(x) = x + x^3/3! +x^5/5! +....+ x^n/n! for all x
	 * Implementation from: wiki: Taylor series
	 * @see http://stackoverflow.com/questions/30166785/java-program-gives-incorrect-taylor-series-term-for-function-ex
	 * @author Chao
	 * @version 2
	 * @param userInputInRadians as the input variable in radians
	 */
	public static double getSinhTaylor(double dRadian){
	    double percision = 0.0000000000000000001;
	    double elementInSeries = dRadian;  //first expansion term is x/1!;
	    double summationOfElements = 0.0;
	    double dResult = 0.0;
	    boolean negativeInput = false;
	    int expansionOrder = 1;

	    // if user input is negative number, we use sinh(x)=-sinh(-x) property. calcualte sinh(-x)
	    if (dRadian < 0) {
	    	negativeInput = true;
	    	dRadian = -dRadian;
	    	elementInSeries = -elementInSeries;
	    }
	    
 
	    do {
	    	summationOfElements =summationOfElements + elementInSeries;	 
	    	// second expansion term is  x^3/3!, third is x^5/5!, hence need to increase order 2 times
	    	expansionOrder =expansionOrder+1;
	    	elementInSeries =elementInSeries * dRadian / expansionOrder ;
	    	expansionOrder =expansionOrder + 1;
	    	elementInSeries =elementInSeries * dRadian / expansionOrder ;
	        
	    	// validate if summation is exceed the limits.
	    	if (summationOfElements > Double.MAX_VALUE) {
	            System.out.println("Too Large");
	            break;
	        }
	    }
	    // Quit the summation loop if adding element is less than e-20
	    while (elementInSeries >= percision);
	    
	    dResult=summationOfElements;
	    //if input is positive, return sinh(x), if input is negative, return -sinh(-x)
	    return negativeInput ? -dResult : dResult;
        
	}
	
	/**method  exponentional talyor expansion 
	 * @version 1
	 * @param x as power term e^x
	 */	
	public static double taylorSeriesExp(double x) {
	    double eps = 0.0000000000000000001;
	    double elem = 1.0;
	    double sum = 0.0;
	    boolean negative = false;
	    int i = 1;
	    sum = 0.0;

	    if (x < 0) {
	        negative = true;
	        x = -x;
	    }

	    do {
	        sum += elem;
	        elem *= x / i;
	        i++;
	        if (sum > Double.MAX_VALUE) {
	            System.out.println("Too Large");
	            break;
	        }
	    }
	    while (elem >= eps);

	    return negative ? 1.0 / sum : sum;
	}
	
	/**method pade approximation exponential function 
	 * Compute the exp(theta) of the variable theta using the pade approximation expansion while order term is finite=5
	 * @version 1
	 * @param theta as the exp(theta)
	 * @return  results of exp(theta)
	 */
	public static double padeExp(double userInputInDegree){
		double padeResult=0;  // this  will be the result
		padeResult=(1+(1.0/2)*userInputInDegree+(1.0/9)*power(userInputInDegree,2)+(1.0/72)*power(userInputInDegree,3)+(1.0/1008)*power(userInputInDegree,4)+(1.0/30240)*power(userInputInDegree,5));
		padeResult=padeResult/(1-0.5*userInputInDegree+(1.0/9)*power(userInputInDegree,2)-(1.0/72)*power(userInputInDegree,3)+(1.0/1008)*power(userInputInDegree,4)-(1.0/30240)*power(userInputInDegree,5));
		return padeResult;
	}
	
	/**method hyperbolic sinh based on taylor exponential function 
	 * @version 1
	 * @param x as the input 
	 */
	public static double sinhTaylorExp(double x){
		return (taylorSeriesExp(x)+taylorSeriesExp(-x))/2;  //set order is 9
	}
	
	/**method hyperbolic sine derived from pade approximation series 
	 * @version 1
	 * @param x as the input 
	 */
	public static double sinhPadeExp(double x){
		return (padeExp(x)*padeExp(x)-1)/(2*padeExp(x));

	}
	

	/**method factorial 
	 * @chao wang
	 * @version 1
	 * @param n as the input of (n)!
	 * @return the n!
	 */
    public static int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    
	/**method power 
	 * @chao wang
	 * @version 1
	 * @param n as the power term ^n
	 * @param x as the base term (x)^n
	 * @return result of x^n
	 */
    public static double power(double x, int n) {
        double  result = 1; // this  will be the result
        if(n==0)
        	return result;
        else{
        for (int i = 1; i <= n; i++) {
            result =result*x;
        }}
        return result;
    }
    
	/**method pi approximation 
	 * In 1997, David H. Bailey, Peter Borwein and Simon Plouffe published a paper (Bailey, 1997) on a new formula for �� as an infinite series:
	 * @chao wang
	 * @version 1
	 * @param n as the order number
	 * @return result of pi
	 */
    public static double pi( int order) {
        double  pi = 0; // pi is the out put result
        for (int i=0; i<=order; i++)
            pi=pi+((1.0/power(16,i))*(4.0/(8*i+1)-2.0/(8*i+4)-1.0/(8*i+5)-1.0/(8*i+6)));
        
        return pi;
    }
 
	/**method euler's number approximation 
	 * implement by definition of e: 1+1/1!+1/2!+1/3!..
	 * @chao wang
	 * @version 1
	 * @param n as the order number
	 * @return result of pi
	 */
    public static double e( int order) {
        double  euler = 0; // euler is the out put result
        for (int i=0; i<=order; i++)
            euler=euler+1.0/factorial(i);
        
        return euler;
    }
    

}