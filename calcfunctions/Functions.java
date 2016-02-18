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
	public static double getSinh_TaylorSeries(double user_Input_in_Radians){
	    double percision = 0.0000000000000000001;
	    double element_in_Series = user_Input_in_Radians;  //first expansion term is user_Input_in_Radians;
	    double summation_of_Elements = 0.0;
	    boolean negative_Input = false;
	    int expansion_Order = 1;

	    // if user input is negative number, we use sinh(x)=-sinh(-x) property. calcualte sinh(-x)
	    if (user_Input_in_Radians < 0) {
	    	negative_Input = true;
	    	user_Input_in_Radians=-user_Input_in_Radians;
	    	element_in_Series = -element_in_Series;
	    }
	    
 
	    do {
	    	summation_of_Elements =summation_of_Elements+ element_in_Series;	 
	    	// second expansion term is x^3/3!, third is x^5/5!, hence need to increase order 2 times
	    	expansion_Order ++;
	    	element_in_Series *= user_Input_in_Radians / expansion_Order ;
	    	expansion_Order ++;
	    	element_in_Series *= user_Input_in_Radians / expansion_Order ;
	        
	    	// validate if summation is exceed the limits.
	    	if (summation_of_Elements > Double.MAX_VALUE) {
	            System.out.println("Too Large");
	            break;
	        }
	    }
	    // Quit the summation loop if adding element is less than e-20
	    while (element_in_Series >= percision);

	    //if input is positive, return sinh(x), if input is negative, return -sinh(-x)
	    return negative_Input ? -summation_of_Elements : summation_of_Elements;
        
	}

}
