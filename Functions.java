package comchaowangcanada.httpsgithub.calculator;

/**
 * Created by Calculator Team on 22/02/2016.
 */
public class Functions {

    public static final double PI = calculatePi(); //Pi constant
    public static final double E_NUMBER = calculateE(); //e constant

    /** Calculate the value e using the Newton series:
     * e = ∑(1/(n!))
     * @return the constant e.
     */
    public static double calculateE(){
        double dResult = 1;
        double factorial = 1;
        //Choose max iterations based on precision
        for (int i = 1; i < 18; i++){
            factorial *= i;
            dResult += 1/factorial;
        }
        return dResult;
    } //End calculateE()

    /** Calculates base to the power of an integer exponent
     * Helper function for logb10()
     * @param double base is the base of the power
     * @param int exp is the integer exponent
     * @return double result, the result of base^(exp)
     */
    private static double powerOfInt(double dBase, int iExp){
        double dResult = 1;
        if (iExp == 0)
            return 1;
        if (iExp > 0){
            for (int i = 0; i < iExp; i++){
                dResult *= dBase;
            }
            return dResult;
        }

        else{
            for (int i = 0; i < (-1 * iExp); i++){
                dResult *= dBase;
            }
            return 1 / dResult;
        }
    } //end powerOfInt()

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
     * @see https://goparallel.sourceforge.net/calculate-pi-with-custom-c-class/
     * @return the constant pi
     */
    private final static double calculatePi() {
        double pi = 0;
        double sum = 1;
        int i = 0;
        while (abs(sum) > (1e-6)) {//best approximation with 1e-8
            sum = ((i % 2 == 0) ? 1 : -1) / (2 * i + 1.0); //sum of fractional elements
            pi += sum;
            i++;
        }
        return pi * 4;
    }


    /**method hyperbolic sinh using talyor expansion
     * Compute the sinh of the angle dRadian using the taylor series expansion
     * with the infinite expansion term  till the last expansion term is less
     * than e-10
     * formula: sinh(x) = x + x^3/3! +x^5/5! +....+ x^n/n! for all x
     * Implementation from: wiki: Taylor series
     * @see http://stackoverflow.com/questions/30166785/java-program-gives-incorrect-taylor-series-term-for-function-ex
     * @param dRadian as the double input variable in radian
     * @return hyperbolic sine function value
     */
    public static double sinh(double dRadian){
        double percision = 0.00000000001;	// precision up to 10th digit after decimal point.
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


    /** Computes the natural logarithm using the Taylor Series:
     * ln(1 + x) = x - (x^2)/2 + (x^3)/3 - (x^4)/4 +- ...
     * -1 < x <= 1
     * @see  www.convict.lu/Jeunes/ultimate_stuff/exp_ln_2.htm
     * @param dInput is the user input
     * @return the natural logarithm of the input
     */
    public static double ln(double dInput){
        //Test for valid input
        if (dInput <= 0){
            return 0.0/0.0; //return NaN
        }

        double dResult = 0;
        int floor = 0;

		/* Reduction to valid range of Taylor series: 0 < input < 2.
		 * Floor will be added to the Taylor series result later.
		 */
        while (dInput > 2){
            dInput /= E_NUMBER;
            floor++;
        }

		/* The Taylor series computes ln of x + 1,
		 * so we use (input - 1) for valid ln(input)
		 */
        double taylorin = dInput - 1;

        //ln(1 + x) = x - (x^2)/2 + (x^3)/3 - (x^4)/4 +- ...
        for (int i = 1; i < 1000; i++){
            if (i % 2 == 1) { //if i is odd
                dResult += powerOfInt(taylorin, i)/i;
            }
            else { //if i is even
                dResult -= powerOfInt(taylorin, i)/i;
            }
        }
		/* return the calculated result + the floor to return
		 * the natural logarithm of the original input
		 */
        return floor + dResult;
    }


    /**
     * Power function of 10 using Taylor expansion
     * @param dInput input value
     * @return Power function of 10 value
     */
    public static double pow10(double dInput){

        double dResult = 1.0;
        double term = 1.0;
        double xln10 = dInput * Functions.ln(10.0);

        if (dInput<0){
            return 1 / pow10(-dInput);
        }

        // apply Taylor series until getting the desired accuracy
        int iterator = 1;
        while (term > 1e-12){
            term *= xln10 / iterator;
            dResult += term;
            iterator++;
        }

        // handle floating-point numbers by default
        double tempSum = dResult % 1.0;
        if (tempSum > 0.99999){
            dResult = dResult - tempSum + 1;
        }
        else if (tempSum < 1e-6) {
            dResult -= tempSum;
        }
        return dResult;
    }


    /**
     * Method to Compute the sine of the angle theta using the taylor series expansion.
     * sin(x) = x - (x^3/3!) + (x^5/5!) - (x^7/7!) + ...
     * @param dRadians value given in Radians by user.
     * @return dResult the sine of the angle theta which was given in Radians by user.
     */
    static final double sine(double dRadians) {

        dRadians %= 2*PI; //convert dRadians to an angle between -2 PI and 2 PI
        double dResult = dRadians; //numerator initially
        double factorial = 1; //denominator factorial initially.
        double radianMultiple = dRadians * dRadians;
        //iterate to the approximation of positive infinity of type double.
        for (int i = 0; factorial < Double.POSITIVE_INFINITY; i++) {
            factorial *= (2 * i + 2) * (2 * i + 3); //best denominator factorial approximation with each iteration.
            dRadians *= radianMultiple; //numerator power increase by 2
            //Sum of fractional components of the Taylor series expansion.
            dResult += (i % 2 == 0 ? -1 : 1) * dRadians / factorial;
        }

        return dResult;

    }


    /**
     * Square root method using Newton's iterations
     * @param dInput User input
     * @return Square root value
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
//END Transfunction
