package sinh;

import java.lang.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.function.DoubleFunction;


public class hyperbolicFunction {

	public static void main(String[] args) {
		   double x = 100;
			long start = System.currentTimeMillis(); 

		   // print the hyperbolic sine for these doubles
		   System.out.println("sinh(" + x + ")=" + Math.sinh(x));
		   
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);
			
			
			
			
			start = System.currentTimeMillis(); 
			
			System.out.println("Taylor series sinh(" + x + ")=" + taylorSeriesSinh(x, 5));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);

			
			
			
			start = System.currentTimeMillis(); 
			
			System.out.println("Taylor series sinh(" + x + ")=" + sinhTaylorExp(x));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);
			
			
			start = System.currentTimeMillis(); 
			
			System.out.println("Pade appriximation sinh(" + x + ")=" + sinhPadeExp(x));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);
			

	}


	/**method hyperbolic sinh talyor expansion 
	 * Compute the sinh of the angle theta using the taylor series expansion while the input term number
	 * @chao wang
	 * @version 1
	 * @param var as the input variable
	 * @param expansionOrer as the expansion terms
	 */
	public static double taylorSeriesSinh(double theta, int expansionOrder){
        double  returnResult = 0; // this  will be the return result
        
        for (int i=1; i<=expansionOrder*2;i=i+2){
        	returnResult=returnResult+power(theta,i)/factorial(i);
        }
        
        return returnResult;
	}
	
	/**method  exponentional talyor expansion 
	 * @version 1
	 * @param x as power term e^x
	 * @param y as the expansion terms
	 */
	public static double taylorSeriesExp(double x, int y){
        double  result = 1; // this  will be the result
        
        for (int i=1; i<=y;i=i+1){
        	result=result+power(x,i)/factorial(i);
        }
        
        return result;
	}
	
	/**method pade approximation exponential function 
	 * Compute the exp(theta) of the variable theta using the pade approximation expansion while order term is finite=5
	 * @version 1
	 * @param theta as the exp(theta)
	 * @return  results of exp(theta)
	 */
	public static double padeExp(double theta){
		double padeResult=0;  // this  will be the result
		padeResult=(1+(1.0/2)*theta+(1.0/9)*power(theta,2)+(1.0/72)*power(theta,3)+(1.0/1008)*power(theta,4)+(1.0/30240)*power(theta,5));
		padeResult=padeResult/(1-0.5*theta+(1.0/9)*power(theta,2)-(1.0/72)*power(theta,3)+(1.0/1008)*power(theta,4)-(1.0/30240)*power(theta,5));
		return padeResult;
	}
	
	/**method hyperbolic sinh based on taylor exponential function 
	 * @version 1
	 * @param x as the input 
	 */
	public static double sinhTaylorExp(double x){
		int expansionOrder=9;
		return (taylorSeriesExp(x,expansionOrder)*taylorSeriesExp(x,expansionOrder)-1)/(2*taylorSeriesExp(x,expansionOrder));  //set order is 9
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
	 * In 1997, David H. Bailey, Peter Borwein and Simon Plouffe published a paper (Bailey, 1997) on a new formula for ¦Ð as an infinite series:
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
