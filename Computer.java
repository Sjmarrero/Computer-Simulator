package part1;

public class Computer implements ComputerInterface {
  
  private Adder adder;
	
	private Complementer complementer;
	
	private Bus bus;
	
	private Printer printer;
	
	private Reader reader;
	
	public Computer() {
		
	
	}
	
	public void build() {
		
		Register r0;
		
		Register r1;
		
		Register r2;
		
		Register r3;
		
	}
	
	public void run() {
		
		//READ
		readInsturction();
		//MOVE R0, R1
		
		//READ
		
		//MOVE R0, R2
		
		//ADD R1, R2, R3
		
		//MOVE R3, R0
		
		//PRINT
		
		//READ
		
		//MOVE R0, R1
		
		//SUB R1, R3, R0
		
		//PRINT
		
		//READ
		
		//MOVE R0, R2
		
		//ADD R3, R2, R1
		
		//MOVE R1, R0
		
		//PRINT
	}
	
	private void readInstruction(){
		reader.storeInteger();
		r0.setInteger(reader.getInteger());
	}

}
