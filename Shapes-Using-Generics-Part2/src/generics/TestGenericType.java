package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Integer;

import cst8284.generics.BasicShape;
import cst8284.generics.Circle;
import cst8284.generics.Rectangle;
import cst8284.generics.SolidObject;
import cst8284.generics.Square;

public class TestGenericType {
	
	public static void main (String[] args){
		
		// instantiate ArrayLists to be used in displayComparisonTable()
		ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1,2,2,1));
		ArrayList<String> strings = new ArrayList<>();
		ArrayList<BasicShape> shapes = new ArrayList<>();
		ArrayList<SolidObject<BasicShape>> solids = new ArrayList<>();

		// instantiate strings and add to strings ArrayList		
		strings.add("myString");
		strings.add(new String ("my String"));
		strings.add("myString");
		
		// instantiate 2D objects and add to shapes ArrayList
		Circle circle1 = new Circle(3.0);
		Square square1 = new Square(4.0);
		Rectangle rectangle1 = new Rectangle(2.0, 8.0);
		Rectangle rectangle2 = new Rectangle(3.0, 5.0);
		shapes.add(circle1);
		shapes.add(square1);
		shapes.add(rectangle1);
		shapes.add(rectangle2);

		// instantiate and add 3D objects to solids ArrayList 
		solids.add(new SolidObject<BasicShape>(circle1, 5.0));
		solids.add(new SolidObject<BasicShape>(square1, 2.0));
		solids.add(new SolidObject<BasicShape>(rectangle1, 2.0));
		solids.add(new SolidObject<BasicShape>(rectangle2, 2.0));
		
		// Compare the contents of each ArrayList
		Table.displayComparisonTable(integers);
		Table.displayComparisonTable(strings);
		Table.displayComparisonTable(shapes);
		Table.displayComparisonTable(solids);
	}
}
