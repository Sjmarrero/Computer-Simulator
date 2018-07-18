package part2;

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
	 * READ  					 - requests user input as an integer.
	 * 
	 * PRINT 					 - prints the value stored in register R0
	 * 
	 * MOVE R#, R#  			 - moves the value in the left register operand into the right
	 * 				 	 		  register operand.
	 * 
	 * ADD	R#, R#, R#			 - Adds the values in the left two register operands and stores 
	 * 					 		  the result in the right most register operand.
	 * 
	 * SUB	R#, R#, R#	 		- Subtracts the value in the left most operand from the right most
	 * 					 		  and stores the result in the right most register operand.
	 * 
	 * LOAD R#, <source> 		- Loads the value from memory at <source> into the register operand 
	 * 								The source may be either:
	 *									a memory location				e.g., 425
	 *									a register indirect address			e.g., (R1)
	 *									an auto-increment register indirect address	e.g., (R1)+
	 *									an immediate operand				e.g., #42
	 *
	 * STORE R#, <destination>	- Stores the value in the register operand into <destination> in memory.
	 * 								The destination may be either:
	 * 									a memory location				e.g., 425
	 * 									a register indirect address			e.g., (R1)
	 * 									an auto-increment register indirect address	e.g., (R1)+
	 * 
	 * DEC R#					- Decrements the value in the register operand by 1.
	 * 
	 * Label:					- The label instruction adds a looping capability. This capability has a 
	 * 							  basic implementation. The loop executes all instructions between Label: 
	 * 							  and BRNZ instruction. 
	 * 								* Once broken, the Label cannot be jumped back to. 
	 *								* There can be no other nested labels.
	 *
	 * BRNZ						- Used to mark the end of the loop. This instruction checks with 
	 * 							  class Status. If the most recent arithmetic operation resulted 
	 * 							  in zero, Status would be set to true. BRNZ (break if not zero) 
	 * 							  causes the loop to iterate again. When status is set to true 
	 * 							  (the last arithmetic operation resulted in zero), the loop will 
	 * 							  break and continue executing the program.
	 * 							 
	 * 
	 * @throws FileNotFoundException thrown when "program" file cannot be found/opened
	 */
	public void run() throws FileNotFoundException;
}
