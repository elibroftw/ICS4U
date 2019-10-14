package shapes;

public class Triangle extends Shape  {
	private double height;
	private double base;
	private double sideA;
	private double sideB;
	
	public Triangle() {
		this(5.0, 4.0, 3.0);
		height = 4.0f;
	}
	
	public Triangle(double base, double height) {
		this.height = height;
		this.base = base;
	}
	
	public Triangle(double base, double sideA, double sideB) {
		this.base = base;
		this.sideA = sideA;
		this.sideB = sideB;
	}
	
	public double getBase() {
		return base;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getSideA() {
		return sideA;
	}
	
	public double getSideB() {
		return sideB;
	}
	
	public void setBase(float base) {
		this.base = base; 
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setSides(float sideA, float sideB) {
		this.sideA = sideA;
		this.sideB = sideB;
	}
	
	@Override
	public double getPerimeter() {
		return base+sideA+sideB;
	}
	
	@Override
	public double getArea() {
		double s = (sideA + sideB + base)/2;
	    double x = ((s) * (s-sideA) * (s-sideB) * (s-base));
	    double Area = Math.sqrt(x);
	    return Area;
	}
	
	public boolean isValidTriangle() {
		if (sideA + sideB < base) {
			return false;
		}
		if (sideA + base < sideB) {
			return false;
		}
		if (base + sideB < sideA) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Triangle[base=" + base + "sideA=" + sideA + ", sideB=" + sideB + "]";
	}
}
