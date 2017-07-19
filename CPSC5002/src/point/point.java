package point;

import java.awt.Point;

public class point {
	public double x;
	public double y;
	
	/**
	 * Get the deistance coordinate in the Polar system.
	 * @return distance from origin
	 */
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(doubl y) {
		this.y = y;
	}
	public double getR() {
		return Math.sqrt(x*x + y*y);
		}
	public void setR(double rCoord) {
		double theta = getTheta();
		x = rCoord * Math.cos(theta);
		y = rCoord * Math.sin(theta);
	}
	
	/**
	 * Get the angle coordinate in the Polar system.
	 * @return angle in radians counterclockwise from East
	 *       [0,2pi]
	 *       
	 */
	public double getTheta(){
		return Math.atan2(y, x);
	}
	
	public void SetTheta(double thetaCoord) {
		double r = getR();
		x = r * Math.cos(thetaCoord);
		y = r * Math.sin(thetaCoord);
		
	}
	
	public String toString() {
		return "point(x=" + getX() + " ,y=" + getY() + ")";
	}
	
	public double distance(point other) {
		double dx = other.getX() - this.getX();
		double dy = other.getY() - this.getY();
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public double distance(double otherx, double othery) {
		Point other = new Point(otherx, othery);
		return distance(other);
	}
	
	public static double distance(Point a, Point b) {
		return a.distance(b);
	}
	
	public static double distance(double ax, double ay, double bx, double by) {
		Point.distance(new Point(ax, ay), new Point(bx, by));
	}
}

