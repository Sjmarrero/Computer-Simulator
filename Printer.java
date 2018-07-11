package part1;

public class Printer implements PrinterInterface {
	
	public Printer() {
		
	}
	
	public void printValue(int a) {
		System.out.println(">> " + a);
	}
}
