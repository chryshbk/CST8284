package calculator;

public class Complex {
	private double real = 0;
	private double imag = 0;
	
	// Complex constructor that takes in a single string, e.g. 2-4i
	public Complex(String cStr){
		this(cStr.split("(?=\\+)|(?=\\-)"));  // splits cStr at + or - into an array of two strings
	}
	
	// Complex constructor that takes in an array of two strings, e.g. cStr[0]="2", cStr[1]="-4i"
	public Complex(String[] cStr){
		this(cStr[0], cStr[1]);
		//TODO: chain the input from this constructor to the next constructor
	}

	// Complex constructor that takes two separate strings as parameters, e.g. "2" and "-4i"
	public Complex(String r, String i){
		//TODO: chain the input from this constructor to the next constructor
		// Note that this constructor needs to strip the 'i' from the string storing 
		// the imaginary number; it must pass only an integer to the next constructor, 
		// otherwise an error results
		
		this(Integer.valueOf(r), Integer.valueOf(i.substring(0, i.length()-1)));
		//Integer.valueOf changes the type of String r to Int r
		//i.substring is to eliminate the "i" from the end 
		//so it will be an integer in the next constructor
	}
	
	// Complex constructor that takes in two ints as parameters, e.g. 2 and -4
	public Complex(int r, int i){
		//TODO: chain the input from this constructor to the next constructor
		this((double) r, (double) i);
	}
	
	// Complex constructor that takes in two ints and stores them as floats, e.g. as 2.0 and -4.0
	public Complex(double r, double i){
		this.setReal(r);
		this.setImag(i);
	}
	
	//default Complex constructor; required, but not used.  DO NOT ALTER
	public Complex(){
	}
	
	//TODO: Write a getter called getReal() that returns the real value of the Complex number
	public Double getReal(){
		return real;
	}
	//TODO: Write a getter called getImag() that returns the imaginary value of the Complex number
	public Double getImag(){
		return imag;
	}
	//TODO: Write a getter called getComplex() that returns the Complex number itself
	public Complex getComplex(){
		return this;
	}
	//TODO: Write a method toString() that returns a string in the form "a + Bi" for this Complex number
	public String toString(){
		char op;
		if (imag < 0) {
			op = '-';
			imag *= -1;
		} else {
		op = '+';
		}
		return Double.toString(real)+" "+op+ " "+ Double.toString(imag)+"i";
	}
	//TODO: Write a setter called setReal() that sets this class's real value equal to the parameter passed to the method
	public void setReal(double r){
		real = r;
	}
	//TODO: Write a setter called setImag() that sets this class's imaginary value equal to the parameter passed to the method
	public void setImag(double i){
		imag = i;
	}

}
