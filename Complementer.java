package part1;

public class Complementer implements ComplementerInterface {
  
	private int value;
	
	public Complementer( ) {
		value = 0;
		
	}
	
	public void setValue(int a) {
		value = a;
	}
	
	public int getValue() {
		return -value;
	}

}
