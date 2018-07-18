package part1;

import java.io.FileNotFoundException;

public interface ComputerInterface {
	
	/**
	 * Method for instantiating all object data members of the class.
	 * This method is called by the constructor.
	 */
	public void build();
	
	/**
	 * Main method that reads, identifies, and executes instructions from a file.
	 * The file is a text file that represents the program simulated by this computer.
	 * The program is similar to MIPS assembly language. Specific format is explained 
	 * in the ReadMe instructions and as below. There are four registers marked 0-4.
	 * 
	 * The ISA recognized by this simulator are:
	 * 
	 * READ  			- requests user input as an integer.
	 * 
	 * PRINT 			- prints the value stored in register R0
	 * 
	 * MOVE R#, R#  	- moves the value in the left register operand into the right
	 * 				 	  register operand.
	 * 
	 * ADD	R#, R#, R#	- Adds the values in the left two register operands and stores 
	 * 					  the result in the right most register operand.
	 * 
	 * SUB	R#, R#, R#	- Subtracts the value in the left most operand from the right most
	 * 					  and stores the result in the right most register operand.
	 * 
	 * @throws FileNotFoundException thrown when "program" file cannot be found/opened
	 */
	public void run() throws FileNotFoundException;
	
}
