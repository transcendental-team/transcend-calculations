package squareroot;

import static org.junit.Assert.*;

import org.junit.Test;

public class BakhshaliSquareRoot {

	@Test
	public void sqrt1Test() {
		double errorTolerance = 1e-1; // max tolerance before failure
		double testNumber = Math.PI;
		
		assertEquals(Math.sqrt(testNumber), Transcendental.sqrt1(testNumber),
				errorTolerance);
	}
}
