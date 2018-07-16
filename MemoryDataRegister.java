package part2;

public class MemoryDataRegister {

	private int data;
	
	public MemoryDataRegister() {
		data = 0;
	}
	
	public void setValue(int value) {
		data = value;
	}
	
	public int getValue() {
		return data;
	}
}
