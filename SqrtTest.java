package calculatorfunction;

import static org.junit.Assert.*;

import org.junit.Test;

public class SqrtTest {
	@Test
	public void sqrtTest() {
			double errorTolerance = 1e-5; 
			double testNumber1 = 0;

			assertEquals(Math.sqrt(testNumber0), Functions.sqrt(testNumber0),
					errorTolerance);
			
			assertEquals(Math.sqrt(testNumber1), Functions.sqrt(testNumber1),
					errorTolerance);
	}
}
