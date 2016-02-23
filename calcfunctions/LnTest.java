package calculatorfunction;

import static org.junit.Assert.*;

import org.junit.Test;

public class LnTest {
	@Test
	public void lnTest() {
			double errorTolerance = 1e-5; 
			double testNumber0 = Math.PI;
			double testNumber1 = 1;

			assertEquals(Math.log(testNumber0), Functions.ln(testNumber0),
					errorTolerance);
			
			assertEquals(Math.log(testNumber1), Functions.ln(testNumber1),
					errorTolerance);
	}
}
