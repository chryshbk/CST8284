package shape;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {

	@Test
	public void testSquareCircle() {
		Square s = new Square(1.0);
		Circle c = new Circle (1.0);
		
		assertFalse(s.equals(c));
	}

}
