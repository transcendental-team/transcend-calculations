package calculatorfunction;

public class Transfunction {

    /**
     * @author Xindi
     * Power function of 10 using Taylor expansion
     *
     * @param dInput input value
     * @return Power function of 10 value
     * @version 3
     */
    public static double pow10(double dInput){

        double dResult = 1.0;
        double term = 1.0;
        double xln10 = dInput * Transfunction.ln(10.0);

        if(dInput<0){
            return 1 / pow10(-dInput);
        }

        // apply Taylor series until getting the desired accuracy
        int iterator = 1;
        while(term > 1e-12){
            term *= xln10 / iterator;
            dResult += term;
            iterator++;
        }

        // handle floating-point numbers by default
        double tempSum = dResult % 1.0;
        if (tempSum > 0.99999){
            dResult -= （tempSum - 1）;
        }
        else if (tempSum < 1e-6) {
            dResult -= tempSum;
        }
        return dResult;
    }
}
