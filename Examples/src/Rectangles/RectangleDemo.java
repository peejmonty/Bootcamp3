package Rectangles;

/**
 * This program demonstrates the Rectangle class's
 * setLength, setWidth, getLength, get width,
 * and getArea methods.
 * @author Phillip J Viens
 */
public class RectangleDemo {
	public static void main(String[] args) {
		//Create a Rectangle object.
		Rectangle box = new Rectangle();
		
		//Call the object's setLength method, passing 10.0
		// as an argument
		box.setLength(10.0);
		
		//Call the object's setWidth method, passing 20.0
		//as an argument
		box.setWidth(20.0);
		
		//Display the object's length and width and area
		System.out.println("The box's length is "
				+ box.getLength());
		System.out.println("The box's width is "
				+ box.getWidth());
		System.out.println("The box's area is "
				+ box.getArea());
		
	}
}