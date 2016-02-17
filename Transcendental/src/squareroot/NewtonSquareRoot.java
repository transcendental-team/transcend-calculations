package squareroot;

import static org.junit.Assert.*;

import org.junit.Test;

public class NewtonSquareRoot {

	@Test
	public void sqrt2Test() {
		double errorTolerance = 1e-100; // still wouldn't fail
		double testNumber = Math.PI;
		
		assertEquals(Math.sqrt(testNumber), Transcendental.sqrt2(testNumber),
				errorTolerance);
	}

}
