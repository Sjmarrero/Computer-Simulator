package part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProgramLoader {
	
	String fileName;

	public ProgramLoader(String file) {
		fileName = file;
	}

	public int loadProgram(MemoryControl memoryControl) {
		String instruction;
		File programFile = new File(fileName);
		Scanner program;
		try {
			program = new Scanner(programFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
		String name = program.nextLine();
		int firstWord = Integer.valueOf(program.nextLine());
		int instructionIndex = Integer.valueOf(program.nextLine());
		System.out.println(name);
		int firstInstruction = instructionIndex;
		
		for(int i = firstWord; i < instructionIndex; i++) {
			instruction = program.nextLine().trim();
			instruction = instruction.substring(0, instruction.indexOf(" "));
			memoryControl.add(instruction, i);
		}
		
		while(program.hasNext()) {
			instruction = program.nextLine().trim();
			if(instruction.isEmpty())
				continue;
			else {
				memoryControl.add(instruction.toLowerCase(), instructionIndex);
				instructionIndex++;
			}
		}
		program.close();
		return firstInstruction;
	}
}
