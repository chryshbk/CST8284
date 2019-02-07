package generics;

public class SolidObject <T extends BasicShape> {
	
   private double depth;
   private T shape;
   
   protected SolidObject(T shape, double depth){
	   this.setShape(shape);
	   this.setDepth(depth);
   }
   protected SolidObject(T shape){
	   this(shape, 1.0);
   }
   
   public double getDepth(){return depth;}
   public void setDepth(double depth){this.depth = depth;}
   
   public T getShape(){return shape;}
   public void setShape(T shape){this.shape = shape;}
   
   public String getName(){
	   if (getShape() instanceof Circle){
		   return "Cylinder";
	   } else if (getShape() instanceof Rectangle){
		   return "Block";
	   } else{
		   return "Cube";
	   }
   }
   public double getVolume(){
	   return getShape().getArea() * getDepth();
   }
   public double getSurfaceArea(){
	   return (2*getShape().getArea()) + (getShape() .getPerimeter() * getDepth());
   }
   
   @Override
	public boolean equals(Object o){
	 return (this.getVolume() == ((SolidObject<BasicShape>) o).getVolume());
	}

}
