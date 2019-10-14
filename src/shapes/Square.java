package shapes;

public class Square extends Shape {
	private double sideLength;

	public Square(double sideLength) {
		this.sideLength = sideLength;
	}

	@Override
	public double getArea() {
		return Math.pow(sideLength, 2);
	}

	@Override
	public double getPerimeter() {
		return sideLength * 4;
	}

	@Override
	public String toString() {
		return "Square[sideLength=" + sideLength + "]";
	}
	
	
}
