package part3;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
	Computer comp  = new Computer(args[0]);
		
	comp.run();		
	}

}
