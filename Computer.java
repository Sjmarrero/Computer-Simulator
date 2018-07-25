package part3;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Computer implements ComputerInterface {
  
	private ProgramLoader programLoader;
	
	private ProgramCounter PC;
	
	private InstructionRegister IR;
	
	private Adder adder;
	
	private Complementer complementer;
	
	private Bus bus;
	
	private Printer printer;
	
	private Reader reader;
	
	private MemoryAddressRegister MAR;
	
	private MemoryDataRegister MDR;
	
	private MemoryControl memoryControl;

	private Register r0, r1, r2, r3, r4;
	
	private Status status;
		
	
	public Computer(String programName) {
		build(programName);
		int firstInstruction = programLoader.loadProgram(memoryControl);
		PC.setAddress(firstInstruction);
	}
	
	public Computer(String programName, String readFile) {
		build(programName);
		int firstInstruction = programLoader.loadProgram(memoryControl);
		reader.openFile(readFile);
		PC.setAddress(firstInstruction);
	}
	
	public void build(String program) {
		
		programLoader = new ProgramLoader(program);
		PC = new ProgramCounter();
		IR = new InstructionRegister();
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
		r4 = new Register();
		status = new Status();
	}
	
	public void run() throws FileNotFoundException {
		
		while(status.running()) {
			fetch();
			adjustPC();
			execute();
		}
		memoryControl.dumpMemory();
	}

	private void fetch() {
		loadInstruction(IR, PC.getAddress());
	}
	
	private void adjustPC() {
		String instruction = IR.getInstruction();
		if(instruction.contains("440") && !status.getZero()) {
			instruction = instruction.substring(instruction.indexOf(" "), instruction.indexOf("/")-1);
			instruction = instruction.replaceAll(" ", "");
			int newPC = Integer.valueOf(instruction);
			PC.setAddress(newPC);
		}
		else
			PC.increment();
	}
	
	private void execute() {
		String instruction = IR.getInstruction();
		callInstruction(instruction);
	}

	private void callInstruction(String instructionLine) {
		Register[] tempRegister = new Register[3];
		int firstSpace = instructionLine.indexOf(' ');
		int termination = instructionLine.indexOf('/');
		int instruction = Integer.valueOf(instructionLine.substring(0, firstSpace).trim());
		String registerString;
		
		switch(instruction) {
		case 110:
			registerString = instructionLine.substring(0, termination);
			tempRegister = getRegisters(registerString);
			addInstruction(tempRegister[0], tempRegister[1], tempRegister[2]);
			break;
		case 120:
			registerString = instructionLine.substring(0, termination);
			tempRegister = getRegisters(registerString);
			subInstruction(tempRegister[0], tempRegister[1], tempRegister[2]);
			break;
		case 160:
			registerString = instructionLine.substring(0, termination);
			tempRegister = getRegisters(registerString);
			decInstruction(tempRegister[0]);
			break;
		case 440:
			break;
		case 810:
			readInstruction();
			break;
		case 820:
			printInstruction();
			break;
		case 000:
			break;
		case 999:
			haltInstruction();
			break;
		case 510:
			registerString = instructionLine.substring(0, termination);
			tempRegister = getRegisters(registerString);
			moveInstruction(tempRegister[0], tempRegister[1]);
			break;
		case 610:
		case 620:
		case 630:
			registerString = instructionLine.substring(0, termination);
			tempRegister = getRegisters(registerString);
			loadInstruction(tempRegister[0], tempRegister[1]);
			break;
		case 640:
			registerString = instructionLine.substring(0, termination);
			tempRegister = getRegisters(registerString);
			moveInstruction(tempRegister[1], tempRegister[0]);
			break;
		case 710:
		case 720:
		case 730:
			registerString = instructionLine.substring(0, termination);
			tempRegister = getRegisters(registerString);
			storeInstruction(tempRegister[0], tempRegister[1]);
			break;
			
			default:
				haltInstruction();
				break;
		}
	}
	
	private Register[] getRegisters(String regString) {
		String[] registers;
		Register[] temp = new Register[3];
		
		regString = regString.replaceAll(" +", ",");
		registers = regString.split(",");
		
		switch(Integer.valueOf(registers[0])) {
		case 110:
		case 120:
			for(int i = 0; i < 3; i++) {
				temp[i] = getReg(Integer.valueOf(registers[i+1]));
			}
			break;
		case 160:
			temp[0] = getReg(Integer.valueOf(registers[1]));
			break;
		case 510:
			for(int i = 0; i < 2; i++) {
				temp[i] = getReg(Integer.valueOf(registers[i+1]));
			}
			break;
		case 610:
		case 710:
			temp = new Register[2];
			temp[0] = getReg(Integer.valueOf(registers[1]));
			temp[1] = new Register(Integer.valueOf(registers[2]));
			break;
		case 620:
		case 720:
			temp = new Register[2];
			temp[0] = getReg(Integer.valueOf(registers[1]));
			temp[1] = getReg(Integer.valueOf(registers[2]));
			break;
		case 630:
		case 730:
			temp = new Register[2];
			temp[0] = getReg(Integer.valueOf(registers[1]));
			temp[1] = getReg(Integer.valueOf(registers[2]));
			temp[1].setValue(temp[1].getValue() + 1);
			break;
		case 640:
			temp = new Register[2];
			temp[0] = getReg(Integer.valueOf(registers[1]));
			temp[1] = new Register(Integer.valueOf(registers[2]));
			break;
		}
		return temp;
		
	}
	
	private Register getReg(int regNumber) {
		switch(regNumber) {
		case 0:
			return r0;
		case 1:
			return r1;
		case 2:
			return r2;
		case 3:
			return r3;
		case 4:
			return r4;
			default:
				System.out.println("Register Number: " + regNumber + " does not exist");
				return null;
		}
	}
	
	private void haltInstruction() {
		status.setRunning(false);
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
		bus.setDataLines(Integer.toString(reader.getValue()));
		r0.setValue(Integer.valueOf(bus.getDataLines()));
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

}
