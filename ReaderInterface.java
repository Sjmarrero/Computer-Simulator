package part3;

import java.io.IOException;


public interface ReaderInterface {

	/**
	 * Reads an integer from the keyboard and stores it in its buffer.
	 * Precedes the actual read by sending a "*" character as a prompt.
	 */
	
	/**
	 * Prompts and stores user input into private data member.
	 * @throws IOException 
	 */
	public void storeValue() throws IOException;

	/**
	 * Returns value stored in private data member.
	 * @return
	 */
	public int getValue();
}
