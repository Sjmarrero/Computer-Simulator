package part3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Reader implements ReaderInterface {
  
	private int a;
	private Scanner program;
	
	public Reader() {
		a = 0;
		program = null;
	}
	
	public void openFile(String fileName) {
		File programFile = new File(fileName);
		try {
			program = new Scanner(programFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void storeValue() throws IOException {	
	
		if(program != null) {
			a = Integer.valueOf(program.nextLine().trim());
		}
		else {
			System.out.print("* ");
	
			BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	
			String str = br.readLine();
	
			a = Integer.parseInt(str);
		}
	}

	public int getValue() {
		return a;
	}


}
