package part2;

public class Adder implements AdderInterface {
	
	private int value1;
	private int value2;
	
	public Adder() {
		value1 = 0;
		value2 = 0;
	}
	
	public void setValues(int a, int b) {
		value1 = a;
		value2 = b;
	}
	
	public int add() {
		
		return value1 + value2;
		
	}

}
