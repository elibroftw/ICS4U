package shapes;

public class Parallelogram extends Shape {
	
	private double base;
	private double side;
	private double height;
	public Parallelogram(double length, double side, double height) {
		this.base = length;
		this.side = side;
		this.height = height;
	}
	
	@Override
	public double getPerimeter() {
		return (side + base) * 2;
	}

	@Override
	public double getArea() {
		return base * height;
	}

	@Override
	public String toString() {
		return "Rectange[length=" + base + ", side=" + side + ", height=" + height + "]";
	}

}
