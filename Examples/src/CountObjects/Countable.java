package CountObjects;
/**
 * This class demonstrates a static field.
 * @author Phillip J Viens
 *
 */

public class Countable {
	
	private static int instanceCount = 0;
	
	/**
	 * The constructor increments the static field instanceCount.
	 * This keesp track of the number of instances of this class 
	 * that are created.
	 */
	
	public Countable()
	{
		instanceCount++;
	}
	
	/**
	 * The getInstanceCount method returns the number of the instances
	 * of this class that have been created.
	 * @return The value in the instanceCount field.
	 */
	
	public int getInstanceCount()
	{
		return instanceCount;
	}
}
