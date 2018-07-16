package part1;

public interface BusInterface {

	/**
	 * Holds an integer. We will assume that this bus is the only
	 * way to convey data to or from the Printer and Reader objects.
	 */
	
	/**
	 * Sets the private data member.
	 * @param a Value to be stored in private data member
	 */
	public void setValue(int a );
		
	/**
	 * Returns the value of the private data member.
	 * @return private data member value
	 */
	public int getValue();
}
