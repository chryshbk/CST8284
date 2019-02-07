package shape;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleTest {

	@Test
	public void testCircleEquals() {
		Circle c1 = new Circle(1.0);
		Circle c2 = new Circle(c1);
		
		assertTrue(c1.equals(c2));
	}

}
