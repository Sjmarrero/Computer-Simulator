package part2;

public interface AdderInterface {

	/**
	 * Adds the two integer values it holds. Then makes the result available
	 * to be obtained by another object.
	 */
	
	/**
	 * Stores two values in private data members.
	 * @param a value to be stored
	 * @param b value to be stored
	 */
	public void setValues(int a, int b);
	
	/**
	 * Adds the two values stored and returns their sum.
	 * @return sum of the two private data members.
	 */
	public int add();
}
