package part3;

public class ProgramCounter {
	
	int address;

	public ProgramCounter() {
		address = 0;
	}
	
	public void increment() {
		address++;
	}
	
	public void setAddress(int location) {
		address = location;
	}
	
	public int getAddress() {
		return address;
	}
}
