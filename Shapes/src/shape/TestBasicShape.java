package shape;

public class TestBasicShape {

	public static void main(String[] args){
		
	    Circle circle = new Circle(2.0);
	    System.out.println(circle.toString());
	    System.out.println("The perimeter of the circle is " + circle.getPerimeter());
	    System.out.println("The area of the circle is: " + circle.getArea() + "\n");
	
	    Square square = new Square(2.0);
	    System.out.println(square.toString());
	    System.out.println("The perimeter of the square is " + square.getPerimeter());
	    System.out.println("The area of the square is: " + square.getArea() + "\n");
	    
	    Rectangle rect1 = new Rectangle(2.0,3);
	    System.out.println(rect1.toString());
	    System.out.println("The perimeter of the rectangle is " + rect1.getPerimeter());
	    System.out.println("The area of the rectangle is: " + rect1.getArea() +"\n");
	    
	    Rectangle rect2 = new Rectangle(rect1);
	    Rectangle rect3 = new Rectangle(2.0, 1.0);
	    
	    System.out.println("The square and the circle are " + (square.equals(circle)?"":"not ") + "equal");
	    System.out.println("The rectangle and the square are " + (rect1.equals(square)?"":"not ") + "equal");
	    System.out.println("Rectangles 1 and 2 are " + (rect2.equals(rect1)?"":"not ") + "equal");
	    System.out.println("Rectangles 2 and 3 are " + (rect2.equals(rect3)?"":"not ") + "equal");
	
	}
}
