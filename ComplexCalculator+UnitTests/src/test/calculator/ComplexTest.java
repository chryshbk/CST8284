package test.cst8284.calculator;
import cst8284.Calculator.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ComplexTest {

	private Complex c;
	
	@Test
	public void testComplex() {
		c = new Complex(5, 7);
		assertNotNull(c);
	}
	
	@Test
	public void testComplexDoubleDouble() {
	
		
	}

	@Test
	public void testGetReal() {
		
	}

	@Test
	public void testGetImag() {
	}

	@Test
	public void testToString() {
	}

	@Test
	public void testSetReal() {
		c = new Complex(5,7);
		c.setReal(5);
		
		Complex e1 = new Complex(6,7);
		e1.setReal(6);
		
		System.out.println("Real is: "+c+ " != "+e1);
		assertFalse(e1.equals(c));
	}

	@Test
	public void testSetImag() {
		c = new Complex(1, 8);
		c.setImag(8);
		
		Complex e1 = new Complex(1, 8);
		e1.setImag(8);
		System.out.println("Imag is: "+c+ " = "+e1);
		assertTrue(e1.equals(c));

	}

	@Test
	public void testEqualsComplex() {
		Complex c1 = new Complex(1,1);
		Complex c2 = new Complex(1,1);
		
		System.out.println("Equals test: "+c1+ " = "+c2);
		assertEquals(c1.equals(c2), true);
	}

}
