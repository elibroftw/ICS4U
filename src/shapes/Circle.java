package shapes;

public class Circle extends Shape {

	private double radius; // -radius:double
	private String colour; // -colour:String

	public Circle() { // +Circle()
		this(1.0, "red"); // Defaults for radius = 1.0, and colour = "red"
	}

	public Circle(double r) { // +Circle(r:double)
		this(r, "red");
	}

	public Circle(double r, String c) { // Not Listed! But it should be...
		this.radius = r;
		this.colour = c;
	}

	public double getRadius() { // +getRadius():double
		return radius;
	}
	
	@Override
	public double getArea() { // +getArea():double
		return Math.PI * Math.pow(radius, 2);
	}

	public String getColour() {
		return colour;
	}

	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setRadius(double radius) {
		if (radius >= 0) {
			this.radius = radius;
		}
	}

	@Override
	public String toString() {
		return "Circle[radius=" + radius + ", colour=" + colour + "]";
	}

}