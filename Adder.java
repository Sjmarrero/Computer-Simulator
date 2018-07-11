package part1;

public class Adder implements AdderInterface {
	
	private int sum;
	
	public Adder() {
		
	}
	
	public void add(int a, int b) {
		
		sum = a + b;
		
	}
	
	public int getSum() {
		
		return sum;
		
	}

}
