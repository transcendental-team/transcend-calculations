package calculatorfunction;

import static org.junit.Assert.*;

import org.junit.Test;

public class Pow10Test {
	@Test
	public void pow10Test() {
			double errorTolerance = 1e-5;
			double testNumber0 = Math.PI;
			double testNumber1 = 0;

			assertEquals(Math.pow(10, testNumber0), Functions.pow10(testNumber0),
					errorTolerance);
			
			assertEquals(Math.pow(10, testNumber1), Functions.pow10(testNumber1),
					errorTolerance);
	}
}
