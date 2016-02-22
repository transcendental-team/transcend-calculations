import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.function.DoubleFunction;

/**
 * 
 * @author Srikanth
 * @version V2
 */
public class Transcendental {

    private static double cachedPi = 0;

    /**
     * Compute the sine of the angle theta using the taylor series expansion while the factorial term is finite
     *
     * @return the sine of the angle theta
     * @version 2
     */
    public static final double sine1(double theta) {
        if (cachedPi == 0) cachedPi = pi(1e-8);
        // compute the sine using the taylor series while the factorial term is finite
        if (theta < 0) theta *= -1;
        theta %= cachedPi * 2;
        double out = theta;
        double factorial = 1;
        double save = theta * theta;
        for (int iteration = 0; factorial < Double.POSITIVE_INFINITY; iteration++) {
            factorial *= (2 * iteration + 2) * (2 * iteration + 3);
            theta *= save;
            out += (iteration % 2 == 0 ? -1 : 1) * theta / factorial;
        }
        return out;
    }

    /**
     * Compute the sine of an angle using Bhaskara I's sine approximation formula
     * The runtime of this method will be significantly less than the sine1 function.
     * However, the calculations will be slightly less accurate.
     *
     * @param theta
     * @return
     */
    public static final double sine2(double theta) {
        if (cachedPi == 0) cachedPi = pi(1e-8);
        return 16 * theta * (cachedPi - theta) /
                (5 * cachedPi * cachedPi - 4 * theta * (cachedPi - theta));
    }

    /**
     * Calculate pi using Leibniz formula.
     * source: https://goparallel.sourceforge.net/calculate-pi-with-custom-c-class/
     *
     * @param epsilon the smallest value of iteration which should be calculated
     * @return the constant pi
     */
    public static final double pi(double epsilon) {
        double pi = 0;
        double n = Double.MAX_VALUE;
        int i = 0;
        while (abs(n) > epsilon) {
            n = ((i % 2 == 0) ? 1 : -1) / (2. * i + 1);
            pi += n;
            i++;
        }
        return pi * 4;
    }

    /**
     * @return the absolute value of the number
     */
    private static double abs(double number) {
        if (number < 0)
            return -number;
        return number;
    }

/**
 * test cases 
 * Average peak Memory and average time elapsed for each method implementation.
 * @param args
 */
    public static void main(String[] args) {
    	
    	System.out.println(sine1(3));
    	System.out.println(sine2(3));
        /**
         * Benchmarks are calculated by 5 average of 5,000,000 values of each function assigned to members of an array.

         Sine Benchmark - Taylor Series
         Avg time elapsed: 1924 ms
         Avg peak memory:  1513587 bytes

         Sine Benchmark - Bhaskara I's sine approximation formula
         Avg time elapsed: 142.4 ms
         Avg peak memory: 1534566.4 bytes

         It seems that the original function of computing the cosine was indeed a lot slower than computing sine1.
         This is incorrect, as both should be fairly similar to compute. The computational complexity of computing
         sine1 is O(1) because we simply compute the factorial until it is infinite which, no matter what starting number
         is chosen will always end up being the same number of iterations.
         */
     args = new String[]{"sine1"};
     
     
     System.out.println("-------------------------------------");
    	
        if (args[0].equals("sine1")) {
            long sineTime = 0, sineMemory = 0;
            for (int i = 0; i < 5; i++) {
                long[] output = test(Transcendental::sine1);
                sineTime += output[0];
                sineMemory += output[1];
            }
            System.out.println("Sine Benchmark - Taylor Series");
            System.out.println("Avg time elapsed: " + (sineTime /= 5.));
            System.out.println("Avg peak memory:  " + (sineMemory /= 5.));
            
        } 
        
        args = new String[]{"sine2"};
        
        if (args[0].equals("sine2")) {
            long sineTime = 0, sineMemory = 0;
            for (int i = 0; i < 5; i++) {
                long[] output = test(Transcendental::sine2);
                sineTime += output[0];
                sineMemory += output[1];
            }
            System.out.println("Sine Benchmark - Approximation formula");
            System.out.println("Avg time elapsed: " + (sineTime / 5));
            System.out.println("Avg peak memory:  " + (sineMemory / 5));
        } else {
            throw new IllegalArgumentException("args[0] should be 'sine1' or 'sine2'.");
        }

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
}
