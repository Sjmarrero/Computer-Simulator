package part1;

public class Adder implements AdderInterface {
  
  private int a;
	
	private int b;
	
	private int sum;
	
	public Adder( int x, int y ) {
		
		a = x;
		
		b = y;
	
	}
	
	public void add() {
		
		sum = a + b;
		
	}
	
	public int getSum() {
		
		return sum;
		
	}

}
