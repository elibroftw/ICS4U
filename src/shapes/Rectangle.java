package shapes;

public class Rectangle extends Shape{
	private double length;
	private double width;
	
	public Rectangle() {
		this(1.0, 1.0);
	}

	public Rectangle(double dimensions, double dimensions2) {
		this.length = dimensions;
		this.width = dimensions2;
	}
	
	public double getLength() {
		return length;
	}
	
	public void setLength(float length){
		this.length = length;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	@Override
	public double getArea() {
		return length*width;
	}
	
	@Override
	public double getPerimeter() {
		return 2 * (length + width);
	}
	
	@Override
	public String toString(){
		return "Rectange[length=" + length + ", width=" + width + "]";
	}
}
