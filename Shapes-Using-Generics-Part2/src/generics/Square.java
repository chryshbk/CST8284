package generics;
public class Square extends BasicShape{
	
	protected Square(){
		this(1.0);
	}
	
	protected Square(double width){
		this.setWidth(width);
	}
	
	protected Square(Square square){
		 this(square.getWidth());
	}
	
	@Override
	public double getArea(){
		return this.getWidth() * this.getWidth();
	}
	
	@Override
	public double getPerimeter(){
		return this.getWidth() * 4;
	}
	
	@Override
	public String toString(){
		return "Square Overrides "+super.toString();
	}
	
	/*@Override
	public boolean equals(Object obj){
		if (obj == this){
			return true;
	}
		if (!(obj instanceof Square)){
			return false;
	}
		
	return (this.getWidth() == ((Square)obj).getWidth());
	}*/
}