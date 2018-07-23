package part3;

public class Memory<T> {

	private T[] memory;
	
	@SuppressWarnings("unchecked")
	public Memory() {
		memory = (T[]) new Object[1024];
	}
	
	public T get(int address) {
		return memory[address];
	}
	
	public void set(T data, int location) {
		memory[location] = data;
	}
	
	public void dump() {
		System.out.print("Address\t\t\tContent\n");
		System.out.println("-------------------------------");
		for(int i = 0; i < memory.length; i++) {
			if(memory[i] != null) {
				System.out.println(i + "\t\t\t" + memory[i]);
			}
		}
	}
}
