package part2;

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
	
	private MemoryAddressRegister MAR;
	
	private MemoryDataRegister MDR;
	
	private MemoryControl memoryControl;

	private Register r0;
	
	private Register r1;
	
	private Register r2;
	
	private Register r3;
	
	private Status status;
	
	
	
	
	public Computer() {
		build();
	}
	
	public void build() {
		
		adder = new Adder();
		complementer = new Complementer();
		bus = new Bus();
		printer = new Printer();
		reader = new Reader();
		MAR = new MemoryAddressRegister();
		MDR = new MemoryDataRegister();
		memoryControl = new MemoryControl();
		r0 = new Register();
		r1 = new Register();
		r2 = new Register();
		r3 = new Register();
		status = new Status();
	}
	
	public void run() throws FileNotFoundException {
		
		String instruction;

		File programFile= new File("program2.txt");
		Scanner program = new Scanner(programFile);
		while(program.hasNext()) {
			instruction = program.nextLine();
			if(instruction.trim().isEmpty())
				continue;
			else if(instruction.contains(":")) {
				instruction = instruction.substring(instruction.indexOf(':')+1).trim();
				loopInstruction(instruction, program);
			}
			else
				callInstruction(instruction);
		}
		program.close();
		memoryControl.dumpMemory();
	}

	private boolean callInstruction(String instructionLine) {
		Register[] tempRegister = new Register[3];
		instructionLine = instructionLine.toLowerCase();
		int firstSpace = instructionLine.indexOf(' ');
		String instruction;
		
		if(firstSpace != -1) {
			instruction = instructionLine.substring(0, firstSpace);
		}
		else
			instruction = instructionLine;
		if(instruction.equals("brnz")) {
			return status.getZero();
		}
		else if(instruction.equals("read")) {
			readInstruction();
		}
		else if(instruction.equals("move") || instruction.equals("add")
				|| instruction.equals("sub")) {
			String registerString = instructionLine.substring(firstSpace, instructionLine.length());
			tempRegister = getRegisters(registerString);
			if(instruction.equals("move"))
				moveInstruction(tempRegister[0], tempRegister[1]);
			else if(instruction.equals("add"))
				addInstruction(tempRegister[0], tempRegister[1], tempRegister[2]);
			else
				subInstruction(tempRegister[0], tempRegister[1], tempRegister[2]);
		}
		else if(instruction.equals("load") || instruction.equals("store")) {
			String registerString = instructionLine.substring(firstSpace+1, instructionLine.length());
			tempRegister = getRegisters(registerString);
			if(instruction.equals("load")) {
				loadInstruction(tempRegister[0], tempRegister[1]);
			}
			else {
				storeInstruction(tempRegister[0], tempRegister[1]);
			}
		}
		else if(instruction.equals("dec")) {
			String registerString = instructionLine.substring(firstSpace, instructionLine.length());
			tempRegister = getRegisters(registerString);
			decInstruction(tempRegister[0]);
		}
		else if(instruction.equals("print")) {
			printInstruction();
		}
		else
			System.out.println(instruction.toUpperCase() + "- incompatible instruction");
		return status.getZero();
	}

	private Register[] getRegisters(String regString) {
		String[] registers; 
		Register[] temp = new Register[3];
		int regNumber, value;
		
		regString = regString.toLowerCase();
		regString = regString.replaceAll("\\s","");
		registers = regString.split(",");
		
		for(int i = 0; i < registers.length; i++) {
			char firstChar = registers[i].charAt(0);
			if(firstChar == 'r' || firstChar == '(') {
				if(firstChar == '(') {
					regNumber = Character.getNumericValue(registers[i].charAt(2));
				}
				else
					regNumber = Character.getNumericValue(registers[i].charAt(1));
				switch(regNumber) {
				case 0:
					temp[i] = r0;
					break;
				case 1:
					temp[i] = r1;
					break;
				case 2:
					temp[i] = r2;
					break;
				case 3:
					temp[i] = r3;
					break;
					default:
						System.out.println(registers[i] + " does not exist");
				}
				if(firstChar == '(' && registers[i].charAt(registers[i].length()-1) == '+') {
					temp[i].setValue(temp[i].getValue() + 1);
				}
			}
			else if(firstChar == '#') {
				registers[i] = registers[i].replaceAll("#", "");
				value = Integer.parseInt(registers[i]);
				Register tempReg = new Register();
				tempReg.setValue(value);
				temp[i] = tempReg;
			}
			else {
				value = Integer.parseInt(registers[i]);
				Register tempReg = new Register();
				tempReg.setValue(value);
				temp[i] = tempReg;
			}
		}
		return temp;
	}

	private void readInstruction(){
		bus.setControlLines("read");
		if(bus.getControlLines().equals("read"))
		{
			try {
				reader.storeValue();
			} catch (IOException e) {
				e.printStackTrace();
			}
		bus.setDataLines(reader.getValue());
		r0.setValue(bus.getDataLines());
		}
	}
	
	private void moveInstruction(Register ra, Register rb) {
		System.err.println("\t\t\tMOVE " + ra + "," + rb + "\n"); //for instruction trace
		rb.setValue(ra.getValue());

	}
	
	private void addInstruction(Register ra, Register rb, Register rc) {
		System.err.println("\t\t\tADD " + ra + "," + rb + "," + rc); //for instruction trace
		adder.setValues(ra.getValue(), rb.getValue());
		rc.setValue(adder.add());
		System.out.println(rc.getValue());
		if(rc.getValue() == 0) {
			status.setZero();
		}
		else
			status.unsetZero();
	}
	
	private void printInstruction() {
		bus.setControlLines("write");
		if(bus.getControlLines().equals("write")) {
			bus.setDataLines(r0.getValue());
			printer.setValue(bus.getDataLines());
			printer.printValue();
		}
	}
	
	private void subInstruction(Register ra, Register rb, Register rc) {
		System.err.println("\t\t\tSUBTRACT " + ra + "," + rb + "," + rc); //for instruction trace
		complementer.setValue(ra.getValue());
		adder.setValues(complementer.getValue(), rb.getValue());
		rc.setValue(adder.add());
		if(rc.getValue() == 0) {
			status.setZero();
		}
		else
			status.unsetZero();
	}
	
	private void loadInstruction(Register destination, int source) {
		MAR.setAddress(source);
		bus.setAddressLines(MAR.getAddress());
		bus.setControlLines("read");
		memoryControl.execute(bus);
		MDR.setValue(bus.getDataLines());
		destination.setValue(MDR.getValue());
	}
	
	private void loadInstruction(Register destination, Register source) {
		loadInstruction(destination, source.getValue());
	}
	
	private void storeInstruction(Register source, int destination) {
		MAR.setAddress(destination);
		bus.setAddressLines(MAR.getAddress());
		MDR.setValue(source.getValue());
		bus.setDataLines(MDR.getValue());
		bus.setControlLines("write");
		memoryControl.execute(bus);
	}
	
	private void storeInstruction(Register source, Register destination) {
		storeInstruction(source, destination.getValue());
	}
	
	private void decInstruction(Register ra) {
		adder.setValues(ra.getValue(), -1);
		ra.setValue(adder.add());
		if(ra.getValue() == 0) {
			status.setZero();
		}
		else
			status.unsetZero();
	}
	
	private void loopInstruction(String instruction, Scanner program) {
		boolean zero = false;
		String instructionLine = "";
		String instructions = "";
		if(!instruction.trim().isEmpty())
			instructions = instruction + "@";
		while(!instructionLine.contains("brnz")) {
			instructionLine = program.nextLine().toLowerCase();
			if(!instructionLine.trim().isEmpty())
				instructions += instructionLine + "@";
		}
		String[] instructionLoop = instructions.split("@");
		int index = 0;
		while(!zero) {
			instruction = instructionLoop[index++];
			zero = callInstruction(instruction);
			if(index == instructionLoop.length)
				index = 0;
		}
		
	}

}
