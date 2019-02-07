package shape;

public class Circle extends BasicShape{
		
		protected Circle(){
			this(1.0);
		}
		
		protected Circle(double width){
			this.setWidth(width);
		}
		
		protected Circle(Circle circle){
			this.setWidth(circle.getWidth());
		}
		
		@Override
		public double getArea(){
			return Math.PI;
		}
		
		@Override
		public double getPerimeter(){
			return this.getWidth() * Math.PI;
		}
		
		@Override
		public String toString(){
			return "Circle Overrides "+super.toString();
		}
		
		@Override
		public boolean equals(Object obj){
			if (obj == this){
				return true;
				}
			if (!(obj instanceof Circle)){
				return false;
			}
			
			return (this.getWidth() == ((Circle)obj).getWidth());
		}
}
