package part3;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Computer comp = getComp(args);
		
		comp.run();		
	}
	
	private static Computer getComp(String[] arguments) {
		if(arguments.length == 2) {
			return new Computer(arguments[0], arguments[1]);
		}
		else
			return new Computer(arguments[0]);
	}

}
