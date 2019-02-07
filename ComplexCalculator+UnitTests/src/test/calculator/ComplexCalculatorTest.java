package test.cst8284.calculator;
import cst8284.Calculator.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComplexCalculatorTest {

	@Test
	public void testComplexCalculator() {
	}

	@Test
	public void testPlus() {
		Complex c1 = new Complex(2.0, 2.0);
		Complex c2 = new Complex(2.0, 2.0);
		
		Complex expectedResult = new Complex(4.0, 4.0);
		ComplexCalculator calc = new ComplexCalculator();
		
		Complex plus = calc.plus(c1, c2);
		
		System.out.println("The sum is: "+expectedResult.toString()+
				" = "+plus.toString());
		assertTrue(expectedResult.equals(plus));	
		
	}

	@Test
	public void testSubtract() {
		Complex c1 = new Complex(2.0, 2.0);
		Complex c2 = new Complex(2.0, 2.0);
		
		Complex expectedResult = new Complex(0.0, 0.0);
		ComplexCalculator calc = new ComplexCalculator();
		
		Complex subtract = calc.subtract(c1, c2);
		
		System.out.println("The subtraction result is: "+subtract.toString()+ " = "+expectedResult.toString());
		assertNotSame(expectedResult, subtract);
		
	}

	@Test
	public void testMultiply() {
		Complex c1 = new Complex(4.0, 1.0);
		Complex c2 = new Complex(2.0, 1.0);
		
		Complex expectedResult = new Complex(6.0, 6.0);
		ComplexCalculator calc = new ComplexCalculator();
		
		Complex multiply = calc.multiply(c1, c2);
		
		System.out.println("The multiplication result is: "+multiply.toString()+ " != "+expectedResult.toString());
		assertFalse(multiply.equals(expectedResult));
	}

}
