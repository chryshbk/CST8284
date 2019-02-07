package calculator;
import java.util.Scanner;

public class TestComplexCalculator {
	
   private static Scanner input = new Scanner(System.in);
   private static int choice = 0;
   private static ComplexCalculator calc;
   
   //Code execution starts here
   public static void main(String[] args) {
	   
      System.out.println("Welcome to the Complex Calculator\n");
      
      while(true){   //loop until 'Q' entered by user, below
    	 Complex c1 = TestComplexCalculator.getComplexNumber();  // read in the 1st complex number
    	 Complex c2 = TestComplexCalculator.getComplexNumber();  // read in the 2nd complex number
    	 calc = new ComplexCalculator(c1, c2);                   // perform the calculation
         System.out.println("The result is: " + calc.toString());  // output the result
         System.out.println ("Do you wish to continue? Enter Q to quit or anything else to continue");
         if (input.next().charAt(0)=='Q') break;              // press 'Q' to quit, otherwise loop
      }
   }
   
   // Static method requests and returns a complex number from user
   private static Complex getComplexNumber(){
	   
   	   choice = TestComplexCalculator.displayTypeInputMenu();
   	   input.nextLine();
       System.out.print("Enter a complex number ");
       
       switch(choice){
       
          case 1:								      //Single string input
         	 System.out.println("as a single string, e.g. 4-3i");
        	 String strComplex = input.next();
    	     return(new Complex(strComplex));         //Call 1-String constructor
    	     
          case 2:								      // Two string input
         	 System.out.println("as two separate strings, with a space in between, e.g. 4 -3i");
        	 String strReal = input.next(), strImag = input.next();
    	     return(new Complex(strReal, strImag));  //Call 2-String constructor
    	     
          case 3:									 // Two int input
         	 System.out.println("as two integers, where the 2nd int is the imaginary number, e.g. 4 3");
        	 int iReal = input.nextInt(), iImag = input.nextInt();
    	     return(new Complex(iReal, iImag));      //Call 2-integer constructor
    	     
          default:
        	 System.out.println("Bad input choice; exiting\n");
        	 System.exit(0);
        	 return(new Complex());    //not used, but required: JVM expects a Complex return type
       }
   }
   
   //Standard menu method; prompts user to enter input type for Complex constructor
   private static int displayTypeInputMenu(){
      System.out.printf("%s\n\t%s\n\t%s\n\t%s", 
    	"Enter the format of the complex number to be entered:", 
  		"1. A single string",
        "2. 2 strings",
        "3. 2 integer values\n");
      return(input.nextInt());
   }
   
}
