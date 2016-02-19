package calcfunctions;

public class Functions {
	//Branch and put your functions here.

	/**method hyperbolic sinh using talyor expansion 
	 * Compute the sinh of the angle theta using the taylor series expansion with the increase of expansion term
	 * till the adding element is less than e-20
	 * Implementation from: wiki: Taylor series
	 * @see http://stackoverflow.com/questions/30166785/java-program-gives-incorrect-taylor-series-term-for-function-ex
	 * @author Chao
	 * @version 2
	 * @param userInputInRadians as the input variable in radians
	 */
	public static double getSinhTaylor(double aRadian){
	    double percision = 0.0000000000000000001;
	    double elementInSeries = aRadian;  //first expansion term is user_Input_in_Radians;
	    double summationOfElements = 0.0;
	    boolean negativeInput = false;
	    int expansionOrder = 1;

	    // if user input is negative number, we use sinh(x)=-sinh(-x) property. calcualte sinh(-x)
	    if (aRadian < 0) {
	    	negativeInput = true;
	    	aRadian=-aRadian;
	    	elementInSeries = -elementInSeries;
	    }
	    
 
	    do {
	    	summationOfElements =summationOfElements+ elementInSeries;	 
	    	// second expansion term is x^3/3!, third is x^5/5!, hence need to increase order 2 times
	    	expansionOrder ++;
	    	elementInSeries *= aRadian / expansionOrder ;
	    	expansionOrder ++;
	    	elementInSeries *= aRadian / expansionOrder ;
	        
	    	// validate if summation is exceed the limits.
	    	if (summationOfElements > Double.MAX_VALUE) {
	            System.out.println("Too Large");
	            break;
	        }
	    }
	    // Quit the summation loop if adding element is less than e-20
	    while (elementInSeries >= percision);

	    //if input is positive, return sinh(x), if input is negative, return -sinh(-x)
	    return negativeInput ? -summationOfElements : summationOfElements;
        
	}
}
