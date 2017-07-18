package point;

public class point {
	public double x;
	public double y;
	
	/**
	 * Get the deistance coordinate in the Polar system.
	 * @return distance from origin
	 */
	public double getR() {
		return Math.sqrt(x*x + y*y);
		}
	
	/**
	 * Get the angle coordinate in the Polar system.
	 * @return angle in radians counterclockwise from East
	 *       [0,2pi]
	 */
	public double getTheta(){
		return Math.atan2(y, x);
	}
}

