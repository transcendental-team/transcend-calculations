package calculatorfunction;



public class Transfunction {

	/**method hyperbolic sinh using talyor expansion 
	 * Compute the sinh of the angle dRadian using the taylor series expansion 
	 * with the infinite expansion term  till the last expansion term is less 
	 * than e-20
	 * formula: sinh(x) = x + x^3/3! +x^5/5! +....+ x^n/n! for all x
	 * Implementation from: wiki: Taylor series
	 * @see http://stackoverflow.com/questions/30166785/java-program-gives-incorrect-taylor-series-term-for-function-ex
	 * @author Chao
	 * @version 5
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
<<<<<<< HEAD
	    	expansionOrder += 1;
	    	elementInSeries *= dRadian / expansionOrder ;
	    	expansionOrder += 1;
	    	elementInSeries *= dRadian / expansionOrder ;
=======
	    	expansionOrder = expansionOrder+1;
	    	elementInSeries = elementInSeries * dRadian / expansionOrder ;
	    	expansionOrder = expansionOrder + 1;
	    	elementInSeries = elementInSeries * dRadian / expansionOrder ;
>>>>>>> origin/hyperbolicSine
	        
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
}
