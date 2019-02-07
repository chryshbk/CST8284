package calculator;


public class ComplexCalculator {
	
	private java.util.Scanner op = new java.util.Scanner(System.in);
	private Complex c;
	
	public ComplexCalculator(){}
	
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
			  
		   case '*':
			   c = multiply(c1, c2);
			   break;
			  
		   case '/':
			   c = divide(c1, c2);
			   break;
		      
		   default:
			  System.out.println("Unknown operation requested");
		}
		
	}
	
	public Complex plus(Complex c1, Complex c2){
		double real = c1.getReal() + c2.getReal();
		double imag = c1.getImag() + c2.getImag();
		return(new Complex(real, imag));
	}
	
	public Complex subtract(Complex c1, Complex c2){
		double real = c1.getReal() - c2.getReal();
		double imag = c1.getImag() - c2.getImag();
		return (new Complex(real, imag));
	}
	
	public Complex multiply(Complex c1, Complex c2){
		double real = c1.getReal() * c2.getReal() - c1.getImag() * c2.getImag();
		double imag = c1.getReal() * c2.getImag() + c2.getReal() * c1.getImag();
		return(new Complex(real, imag));
	}

	public Complex divide(Complex c1, Complex c2){	
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
	}

	public Complex getComplexResult(){
		return c;
	}

	public String toString(){
		return c.toString();
	}

}
