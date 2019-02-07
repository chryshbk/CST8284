package shape;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testRectangleSquare() {
		Rectangle r = new Rectangle();
		Square s = new Square ();
		
		
		assertEquals(s, r);
	}

}
