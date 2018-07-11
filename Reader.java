package part1;

public class Reader implements ReaderInterface {
  
  private int a;
	
	public Reader() {
		
	}
	
	public void storeInteger() throws IOException {	
	
	System.out.println(" Enter the integer: ");
	
	BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	
	String str = br.readLine();
	
	a = Integer.parseInt(str);
	
	System .out.print("* ");
	
	}


}
