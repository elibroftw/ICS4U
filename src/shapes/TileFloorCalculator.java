package shapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TileFloorCalculator {
	static Scanner sc = new Scanner(System.in);
	static List<Shape> shapes = new ArrayList<>();
	
	public static void main(String[] args) {

		double pricePerSquareUnit = 0;
		while (true) {
			int choice = -1;
			while(choice > 7 || choice < 1) {
				System.out.println("What would like to do?");
				System.out.println("1. Create a new shap?");
				System.out.println("2. Delete an existing shape");
				System.out.println("3. Display the list of existing shapes");
				System.out.println("4. Enter the price per square unit");
				System.out.println("5. Calculate the total cost");
				System.out.println("6. Calculate the total area");
				System.out.println("7. Clear the array of shapes");
				choice = sc.nextInt();
			}
			switch(choice) {
			case 1:
				newShape();
				break;
			case 2: 
				deleteShape();
				break;
			case 3: 
				for (Shape shape : shapes) {
					System.out.println(shape);
				}
				break;
			case 4:
				while(pricePerSquareUnit <= 0) {
					pricePerSquareUnit = sc.nextDouble();
					if (pricePerSquareUnit <= 0) {
						System.out.println("That's an invalid input");
					}
				}
				break;
			case 5: 
				System.out.println("$" + pricePerSquareUnit * calculateArea());
				break;
			case 6:
				System.out.println(calculateArea());
				break;
			case 7:
				shapes.clear();
				break;
			}
			
		}
		
		
		
	}

	private static void deleteShape() {
		System.out.println("Enter a number to specify which shape to delete");
		for (Shape shape : shapes) {
			System.out.println("1. "+shape.toString());
		}
		int choice = 0;
		int size = shapes.size();
		while (choice < 0 || choice > size) {
			choice = sc.nextInt();
			if (choice < 0 || choice > size) {
				System.out.println("That was an ivalid choice, please try again");
			}
		}
		shapes.remove(choice-1);
	}

	public static double[] getDimensions(int inputs) {
		double[] dimensions = new double[inputs];
		for (int i = 0; i < inputs; i++) {
			System.out.println("Enter side/radius length (height/side/base if parallelogram) " + (i + 1));
			dimensions[i] = sc.nextDouble();
		}
		return dimensions;
	}
	
	public static void newShape() {
		double[] dimensions;
		List<String> shapeNames = Arrays.asList("square", "circle", "donut", "rectangle", "triangle", "parallelogram");
		String shapeName = "";
		Shape newShape = null;
		while (shapeNames.contains(shapeName)) {
			shapeName = sc.next().toLowerCase();
			if (shapeNames.contains(shapeName)) {
				System.out.println("That shape name is invalid");
			}
		}
		switch (shapeName) {
		case "square":
			dimensions = getDimensions(1);
			newShape = new Square(dimensions[0]);
		case "circle":
			dimensions = getDimensions(1);
			newShape = new Circle(dimensions[0]);
		case "donut":
			dimensions = getDimensions(2);
			newShape = new Donut(dimensions[0], dimensions[1]); 
		case "rectangle":
			dimensions = getDimensions(2);
			newShape = new Rectangle(dimensions[0], dimensions[1]);
		case "triangle":
			dimensions = getDimensions(3);
			newShape = new Triangle(dimensions[0], dimensions[1], dimensions[2]);
		case "parallelogram":
			dimensions = getDimensions(3);
			newShape = new Parallelogram(dimensions[0], dimensions[1], dimensions[2]);
		}
		shapes.add(newShape);
		
	}

	public static double calculateArea() {
		double area = 0;
		for (Shape shape : shapes) {
			area += shape.getArea();
		}
		return area;
	}

}
