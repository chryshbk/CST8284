package calculator;


public class ComplexCalculator {
	
	private java.util.Scanner op = new java.util.Scanner(System.in);
	private Complex c;
	
	public ComplexCalculator(Complex c1, Complex c2){
		
		System.out.println("Which math operation do you wish to perform?  Enter +, -, *, /");
		char mathOp = op.nextLine().charAt(0);
		
		switch (mathOp){
		   case '+':
		      c = plus(c1, c2);
		      break;
		   case '-':
			  c = subtract(c1, c2);
			  break;
			  //TODO: Call the method for the subtraction of two complex numbers here
		   case '*':
			   c = multiply(c1, c2);
			   break;
			  //TODO: Call the method for the multiplication of two complex numbers here
		   case '/':
			   c = divide(c1, c2);
			   break;
		      //TODO: Call the method for the division of two complex numbers here
		   default:
			  System.out.println("Unknown operation requested");
		}
		
	}
	
	private Complex plus(Complex c1, Complex c2){
		double real = c1.getReal() + c2.getReal();
		double imag = c1.getImag() + c2.getImag();
		return(new Complex(real, imag));
	}

   //TODO Uncomment the following block of code and write code to perform the appropriate
   // math operations, as outlined in the appendix.  See the plus() method above for help.
	
	private Complex subtract(Complex c1, Complex c2){
		double real = c1.getReal() - c2.getReal();
		double imag = c1.getImag() - c2.getImag();
		return (new Complex(real, imag));
	}
	
	private Complex multiply(Complex c1, Complex c2){
		double real = c1.getReal() * c2.getReal() - c1.getImag() * c2.getImag();
		double imag = c1.getReal() * c2.getImag() + c2.getReal() * c1.getImag();
		return(new Complex(real, imag));
	}

	private Complex divide(Complex c1, Complex c2){	
		double real;
		double imag;
		double denominator = c2.getReal() * c2.getReal() + (c2.getImag() * c2.getImag());
		
		if (denominator == 0){
			System.out.println("Divide-by-zero error detected");
			real = 0;
			imag = 0;
		} else {
		
		real = c1.getReal() * c2.getReal() + (c1.getImag() * c2.getImag());
		real /= denominator;
		
		imag = c2.getReal() * c1.getImag() - (c1.getReal() * c2.getImag());
		
		imag /= denominator;
		}
		return(new Complex(real, imag));
	 //TODO: check for possible division by 0 and output an error message to the screen
	 //return a constructor with value 0 + 0i);
	}

    //TODO: write a getter called getComplexResult() that returns the Complex number stored in this class
	public Complex getComplexResult(){
		return c;
	}

//TODO: write a method called toString() that returns the string value of the Complex number generated
//above and stored in this class, i.e. it must return a String in the form "a + Bi" or "a - Bi" 
//depending on whether B is positive or negative.  For example, it wouldn't make much sense to write
//the output of a calculation as 3.0 +-2.0i; you should write it as 3.0 - 2.0i.  So be certain you
//code addresses the problem of writing the output correctly if B is a negative number

	public String toString(){
		return c.toString();
	}

}
