package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
	
	public void run() throws FileNotFoundException {
		
		String instruction;
		String[] registers;
		int regNumber;
		Register[] tempRegister = new Register[3];

		File programFile= new File("program.txt");
		Scanner program = new Scanner(programFile);
		while(program.hasNext()) {
			instruction = program.next();
			instruction = instruction.toLowerCase();
			if(instruction.equals("read")) {
				readInstruction();
			}
			else if(instruction.equals("move") || instruction.equals("add")
					|| instruction.equals("sub")) {
				String registerString = program.nextLine();
				registerString = registerString.replaceAll("\\s","");
				registers = registerString.split(",");
				for(int i = 0; i < registers.length; i++) {
					regNumber = Character.getNumericValue(registers[i].charAt(1));
					switch(regNumber) {
					case 0:
						tempRegister[i] = r0;
						break;
					case 1:
						tempRegister[i] = r1;
						break;
					case 2:
						tempRegister[i] = r2;
						break;
					case 3:
						tempRegister[i] = r3;
						break;
						default:
							System.out.println(registers[i] + " does not exist");
					}
				}
				if(instruction.equals("move"))
					moveInstruction(tempRegister[0], tempRegister[1]);
				else if(instruction.equals("add"))
					addInstruction(tempRegister[0], tempRegister[1], tempRegister[2]);
				else
					subInstruction(tempRegister[0], tempRegister[1], tempRegister[2]);
			}
			else if(instruction.equals("print")) {
				printInstruction();
			}
			else
				System.out.println(instruction.toUpperCase() + "- incompatible instruction");
		}
		program.close();
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
		System.err.println("\t\t\tMOVE " + ra + "," + rb + "\n"); //for instruction trace
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
		System.err.println("\t\t\tSUBTRACT " + ra + "," + rb + "," + rc); //for instruction trace
		adder.add(complementValue, rb.getValue());
		rc.setValue(adder.getSum());
	}

}
