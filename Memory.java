package part2;

public class Memory {

	private int[] memory;
	
	public Memory() {
		memory = new int[1024];
	}
	
	public int get(int address) {
		return memory[address];
	}
	
	public void set(int data, int location) {
		memory[location] = data;
	}
}
