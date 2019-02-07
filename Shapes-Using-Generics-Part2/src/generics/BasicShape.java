package generics;

public abstract class BasicShape {
	
	private double width;
	
	public double getWidth(){return width;}
	public void setWidth(double width){this.width = width;}
			
	@Override
	public String toString(){
		return ("BasicShape Overrides " + super.toString());
	}
	
	public abstract double getArea();
	public abstract double getPerimeter();
	
	@Override
	public boolean equals(Object o){
	 return (this.getArea() == ((BasicShape) o).getArea());
	}
	
}
