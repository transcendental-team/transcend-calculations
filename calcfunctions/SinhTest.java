package calculatorfunction;

import static org.junit.Assert.*;

import org.junit.Test;

public class SinhTest {
	@Test
	public void sinhTest() {
			double errorTolerance = 1e-5;
			double testNumber0 = Math.PI;
			double testNumber1 = 0;

			assertEquals(Math.sinh(testNumber0), Functions.sinh(testNumber0),
					errorTolerance);
			
			assertEquals(Math.sinh(testNumber1), Functions.sinh(testNumber1),
					errorTolerance);
	}
}
