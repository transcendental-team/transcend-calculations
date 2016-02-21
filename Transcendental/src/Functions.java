package calculatorfunction;

public class Functions {

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
