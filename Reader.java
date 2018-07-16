package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader implements ReaderInterface {
  
	private int a;
	
	public Reader() {
		a = 0;
	}
	
	public void storeValue() throws IOException {	
	
	System.out.println("* ");
	
	BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	
	String str = br.readLine();
	
	a = Integer.parseInt(str);
	}

	public int getValue() {
		return a;
	}


}
