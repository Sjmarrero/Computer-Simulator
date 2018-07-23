package part3;

public class Printer implements PrinterInterface {
	
	private int value;
	
	public Printer() {
		value = 0;
	}
	
	public void setValue(int a) {
		value = a;
	}
	
	public void setValue(String a) {
		value = Integer.valueOf(a);
	}
	
	public void printValue() {
		System.out.println(">> " + value);
	}
}
