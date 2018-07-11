package part1;

import java.io.IOException;

public class Computer implements ComputerInterface {
  
	private Adder adder;
	
	private Complementer complementer;
	
	private Bus bus;
	
	private Printer printer;
	
	private Reader reader;

	private Register r0;
	
	private Register r1;
	
	private Register r2;
	
	private Register r3;
	
	
	
	
	public Computer() {
		build();
	}
	
	public void build() {
		
		adder = new Adder();
		complementer = new Complementer();
		bus = new Bus();
		printer = new Printer();
		reader = new Reader();
		r0 = new Register();
		r1 = new Register();
		r2 = new Register();
		r3 = new Register();
	}
	
	public void run() {
		
		//READ
		readInstruction();
		//MOVE R0, R1
		moveInstruction(r0, r1);
		//READ
		readInstruction();
		//MOVE R0, R2
		moveInstruction(r0, r2);
		//ADD R1, R2, R3
		addInstruction(r1, r2, r3);
		//MOVE R3, R0
		moveInstruction(r3, r0);
		//PRINT
		printInstruction();
		//READ
		readInstruction();
		//MOVE R0, R1
		moveInstruction(r0, r1);
		//SUB R1, R3, R0
		subInstruction(r1, r3, r0);
		//PRINT
		printInstruction();
		//READ
		readInstruction();
		//MOVE R0, R2
		moveInstruction(r0,r2);
		//ADD R3, R2, R1
		addInstruction(r3, r2, r1);
		//MOVE R1, R0
		moveInstruction(r1, r0);
		//PRINT
		printInstruction();
	}
	
	private void readInstruction(){
		try {
			reader.storeValue();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bus.setValue(reader.getValue());
		r0.setValue(bus.getValue());
	}
	
	private void moveInstruction(Register ra, Register rb) {
		System.err.println("\t\t\tMOVE " + ra + "," + rb); //for instruction trace
		rb.setValue(ra.getValue());

	}
	
	private void addInstruction(Register ra, Register rb, Register rc) {
		System.err.println("\t\t\tADD " + ra + "," + rb + "," + rc); //for instruction trace
		adder.add(ra.getValue(), rb.getValue());
		rc.setValue(adder.getSum());
	}
	
	private void printInstruction() {
		bus.setValue(r0.getValue());
		printer.printValue(bus.getValue());
	}
	
	private void subInstruction(Register ra, Register rb, Register rc) {
		int complementValue;
		
		complementValue = complementer.changeSign(ra.getValue());
		System.out.println("Complement: " + complementValue);
		System.err.println("\t\t\tSUBTRACT " + ra + "," + rb + "," + rc); //for instruction trace
		adder.add(complementValue, rb.getValue());
		rc.setValue(adder.getSum());
	}

}
