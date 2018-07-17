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
	
	public void dump() {
		System.out.print("Address\t\t\tContent\n");
		System.out.println("-------------------------------");
		for(int i = 0; i < memory.length; i++) {
			if(memory[i] != 0) {
				System.out.println(i + "\t\t\t" + memory[i]);
			}
		}
	}
}
