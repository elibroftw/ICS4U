package shapes;

public class Donut extends Shape {
	private double internalRadius;
	private double externalRadius;
	
	public Donut() {
		this(1, 1);
	}
	
	public Donut(double internalRadius, double externalRadius) {
		this.internalRadius = internalRadius;
		this.externalRadius = externalRadius;
	}
	
	public void setInternalRadius(double internalRadius) {
		this.internalRadius = internalRadius;
	}
	
	public void setExternalRadius(double externalRadius) {
		this.externalRadius = externalRadius;
	}
	
	@Override
	public double getArea(){
		return Math.PI * Math.pow(externalRadius, 2) - Math.PI * Math.pow(internalRadius, 2);
	}
	
	public double getPerimeter(){
		return 2*Math.PI*internalRadius + 2*Math.PI*externalRadius;
	}
	
	public boolean isValidDonut() {
		if (externalRadius < internalRadius) {
			return false;
		}
		if (externalRadius < 0 || internalRadius < 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Donut[internalRadius=" + internalRadius + ", externalRadius=" + externalRadius + "]";
	}
	
}
