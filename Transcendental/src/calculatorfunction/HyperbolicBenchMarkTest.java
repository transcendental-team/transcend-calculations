package calculatorfunction;

import java.lang.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.function.DoubleFunction;


public class HyperbolicBenchMarkTest {

	public static void main(String[] args) {
		   double x = 1;

		   long start = System.currentTimeMillis(); 

		   // print the hyperbolic sine for these doubles
		   System.out.println("sinh(" + x + ")=" + Math.sinh(x));

	   
			long elapsedTimeMillis = System.currentTimeMillis()-start; 
			
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);
			
			
			
			
			start = System.currentTimeMillis(); 
			
			System.out.println("Taylor series sinh(" + x + ")=" + sinh(x));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);

			
			
			
			start = System.currentTimeMillis(); 

			System.out.println("Taylor series exponential sinh(" + x + ")=" + sinhTaylorExp(x));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);
			
			
			start = System.currentTimeMillis(); 
			
			System.out.println("Pade appriximation exp(" + x + ")=" + sinhPadeExp(x));
			
			elapsedTimeMillis = System.currentTimeMillis()-start; 
			System.out.println("Elapsed time in milliseconds "+elapsedTimeMillis);
			
			System.out.println("sinh(x) - Math.sinh(x) difference is "+ Math.abs(sinh(x) - Math.sinh(x))  );
			System.out.println("sinhTaylorExp(x) - Math.sinh(x) difference is "+ Math.abs(sinhTaylorExp(x) - Math.sinh(x))  );
		
//	        /**
//	         * Benchmarks are calculated by 5 average of 5,000,000 values of each function assigned to members of an array.
//			 * Resource code is copied from Srikanth*/
//
//
//	            long taylorSinhTime = 0, taylorSinhMemory = 0;
//	            for (int iteration = 0; iteration < 5; iteration++) {
//	                long[] output = test(HyperbolicBenchMarkTest::sinh);
//	                taylorSinhTime += output[0];
//	                taylorSinhMemory += output[1];
//	            
//	            System.out.println("Taylor sinh Benchmark - taylor sinh series");
//	            System.out.println("Avg time elapsed: " + (taylorSinhTime /= 5.));
//	            System.out.println("Avg peak memory:  " + (taylorSinhMemory /= 5.));
//	            
//
//	            long taylorExpSinhTime = 0, taylorExpSinhTimeMemory = 0;
//	            for (int i = 0; i < 5; i++) {
//	                output = test(HyperbolicBenchMarkTest::sinhTaylorExp);
//	                taylorExpSinhTime += output[0];
//	                taylorExpSinhTimeMemory += output[1];
//	            }
//	            System.out.println("Taylor exp sinh Benchmark - Taylor exp Series");
//	            System.out.println("Avg time elapsed: " + (taylorExpSinhTime /= 5.));
//	            System.out.println("Avg peak memory:  " + (taylorExpSinhTimeMemory /= 5.));
//	        
//	            
//	            long padeSinhTime = 0, padeSinhTimeMemory = 0;
//	            for (int i = 0; i < 5; i++) {
//	                output = test(HyperbolicBenchMarkTest::sinhPadeExp);
//	                padeSinhTime += output[0];
//	                padeSinhTimeMemory += output[1];
//	            }
//	            System.out.println("pade exp sinh Benchmark - pade exp sinh Series");
//	            System.out.println("Avg time elapsed: " + (padeSinhTime /= 5.));
//	            System.out.println("Avg peak memory:  " + (padeSinhTimeMemory /= 5.));
//	        } 

	    }
	
	    public static final long[] test(DoubleFunction<Double> function) {
	        long start = System.currentTimeMillis();

	        double[] values = new double[5_000_000];
	        for (int iteration = 0; iteration < 5_000_000; iteration++) {
	            values[iteration] = function.apply(iteration / 100.);
	        }

	        long end = System.currentTimeMillis();

	        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
	        MemoryUsage peak = pools.remove(0).getPeakUsage();

	        return new long[]{end - start, peak.getUsed()};
	    }




		/**method hyperbolic sinh using talyor expansion 
		 * Compute the sinh of the angle dRadian using the taylor series expansion 
		 * with the infinite expansion term  till the last expansion term is less 
		 * than e-20
		 * formula: sinh(x) = x + x^3/3! +x^5/5! +....+ x^n/n! for all x
		 * Implementation from: wiki: Taylor series
		 * @see http://stackoverflow.com/questions/30166785/java-program-gives-incorrect-taylor-series-term-for-function-ex
		 * @author Chao
		 * @version 4
		 * @param dRadian as the double input variable in radian
		 * @return hyperbolic sine function value
		 */
		public static double sinh(double dRadian){
		    double percision = 0.00000000001;	// percision up to 10th digit after decimal point.
		    double elementInSeries = dRadian;  //first expansion term is x/1!;
		    double summationOfElements = 0.0;
		    double dResult = 0.0;
		    boolean negativeInput = false;
		    int expansionOrder = 1;

		    // if user input is negative number, we use sinh(x)=-sinh(-x) property.
		    // calcualte sinh(-x)
		    if (dRadian < 0) {
		    	negativeInput = true;
		    	dRadian = -dRadian;
		    	elementInSeries = -elementInSeries;
		    }
		    
		    do {
		    	summationOfElements =summationOfElements + elementInSeries;	 
		    	// second expansion term is  x^3/3!, third is x^5/5!, hence need to
		    	//increase order 2 times
		    	expansionOrder += 1;
		    	elementInSeries *= dRadian / expansionOrder ;
		    	expansionOrder += 1;
		    	elementInSeries *= dRadian / expansionOrder ;
		        
		    	// validate if summation is exceed the limits.
		    	if (summationOfElements > Double.MAX_VALUE) {
		            System.out.println("Too Large");
		            break;
		        }
		    }
		    // Quit the summation loop if adding element is less than 1e-11
		    while (elementInSeries >= percision);
		    
		    dResult=summationOfElements;
		    //if input is positive, return sinh(x),
		    //if input is negative, return -sinh(-x)
		    return negativeInput ? -dResult : dResult;   
		}


	
	/**method  exponentional talyor expansion 
	 * @version 1
	 * @param x as power term e^x
	 */	
	public static double taylorSeriesExp(double x) {
	    double eps = 0.00000000001;
	    double elem = 1.0;
	    double sum = 0.0;
	    boolean negative = false;
	    int i = 1;
	    sum = 0.0;
	    int itrTimes=0;

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
	        itrTimes++;
	    }
	    while (elem >= eps); //elem >= eps  itrTimes <=9
	    System.out.println("iteration time is"+ itrTimes);


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
		double result=taylorSeriesExp(x);
		return (result*result-1)/(2*result);
	}
	
	/**method hyperbolic sine derived from pade approximation series 
	 * @version 1
	 * @param x as the input 
	 */
	public static double sinhPadeExp(double x){
		double result=padeExp(x);
		return (result*result-1)/(2*result);

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
