package shape;

public class Rectangle extends Square{
	private double height;
	
	protected Rectangle(){	
		this(1.0, 1.0);
	}
	
	protected Rectangle(double width, double height){
		super(width);
		this.setHeight(height);
	}
	
	protected Rectangle(Rectangle rectangle){
		this(rectangle.getWidth(), rectangle.getHeight());
	}
	
	public double getHeight(){
		return height;
	}
	
	public void setHeight(double height){
		this.height = height;
	}
	
	@Override
	public double getArea(){
		return this.getWidth()*height;
	}
	
	@Override
	public double getPerimeter(){
		return (2*(this.getWidth()+height));
	}
	
	@Override
	public String toString(){
		return ("Rectangle Overrides "+super.toString());
	}
	
	@Override
	public boolean equals(Object obj){

		if (this==obj){
			return true;
		}
		if (!(obj instanceof Rectangle)){
			return false;
		}
		
		return (super.equals((Rectangle)obj) && this.getHeight() == ((Rectangle)obj).getHeight());
	}
}
