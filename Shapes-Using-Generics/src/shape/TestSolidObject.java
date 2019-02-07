package shape;

import java.util.ArrayList;

public class TestSolidObject {
	
	static ArrayList<SolidObject<BasicShape>> solidObjects = new ArrayList<>();
	
   public static void main(String[] args){

      Circle circle1 = new Circle(3.0);  // depth = 5.0
      Square square1 = new Square(4.0);  // depth = 2.0
      Rectangle rectangle1 = new Rectangle(2.0, 8.0); // depth = 2.0
      Rectangle rectangle2 = new Rectangle(3.0, 5.0); // depth = 2.0

      SolidObject<BasicShape> cylinder = new SolidObject<BasicShape>(circle1, 5.0);
      SolidObject<BasicShape> cube = new SolidObject<BasicShape>(square1, 2.0);
      SolidObject<BasicShape> block1 = new SolidObject<BasicShape>(rectangle1, 2.0);
      SolidObject<BasicShape> block2 = new SolidObject<BasicShape>(rectangle2, 2.0);
      
      solidObjects.add(cylinder);
      solidObjects.add(cube);
      solidObjects.add(block1);
      solidObjects.add(block2);
		
      displayVolumeComparison(solidObjects); 
      displaySurfaceAreaComparison(solidObjects);

   }
	
   public static boolean isVolumeEqual(SolidObject<BasicShape> so1, SolidObject<BasicShape> so2){
	   return (so1.getVolume() == so2.getVolume());
   }

   
   public static boolean isSurfaceAreaEqual(SolidObject <BasicShape> sol1, SolidObject <BasicShape> sol2){
	   return (sol1.getSurfaceArea() == sol2.getSurfaceArea());
   }

	
   public static void displayVolumeComparison(ArrayList<SolidObject<BasicShape>> arList){
		
      // Print out column header
      System.out.println("\nCheck the array: are the volumes of the solid objects equal?\n");
      System.out.print("\t\t");
      for (SolidObject<BasicShape> ColumnHeader: arList)
         System.out.print("\t" + ColumnHeader.getName());
		
	 // Print out each row,starting with the name of the object
	 for (SolidObject<BasicShape> solidObjRow: arList){
	    System.out.println();	// Next line
	    System.out.print(solidObjRow.getName());
            for (SolidObject<BasicShape> solidObjColumn: arList)
               System.out.print("\t\t" + isVolumeEqual(solidObjColumn, solidObjRow));
         }    
      }
	
   public static void displaySurfaceAreaComparison(ArrayList<SolidObject<BasicShape>> arList){
		
      // Print out column header
      System.out.println("\nCheck the array: are the surface areas of the solid objects equal?\n");
      System.out.print("\t\t");
       for (SolidObject<BasicShape> ColumnHeader: arList)
         System.out.print("\t" + ColumnHeader.getName());
		
      // Print out each row,starting with the name of the object
      for (SolidObject<BasicShape> solidObjRow: arList){
         System.out.println();	// Next line
         System.out.print(solidObjRow.getName());
         for (SolidObject<BasicShape> solidObjColumn: arList)
            System.out.print("\t\t" + isSurfaceAreaEqual(solidObjColumn, solidObjRow));  
      }
   }
}
