package generics;

import java.util.ArrayList;
public class Table {

	 public static <E> void displayComparisonTable(ArrayList<E> arList){
			
	      int i = 0;
	      System.out.print("\n\n");
	      for (E ColumnHeader: arList)
	         System.out.print("\t	" + ColumnHeader.getClass().getSimpleName()+ i++ + "  ");
			i = 0;
		 // Print out each row,starting with the name of the object
		 for (E genObjRow: arList){
		    System.out.println();	// Next line
		    System.out.print(genObjRow.getClass().getSimpleName()+ i++ + "    ");
	            for (E genObjColumn: arList)
	               System.out.print("\t    " + genObjColumn.equals(genObjRow));
	         }    
	      }
}
