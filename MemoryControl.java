package part2;

public class MemoryControl {
	
	private Memory memory;
	
	public MemoryControl() {
		memory = new Memory();
	}
	
	public void execute(Bus bus) {
		String controlSignal = bus.getControlLines();
		int address;
		int data;
		if(controlSignal.equals("read")) {
			address = bus.getAddressLines();
			data = memory.get(address);
			bus.setDataLines(data);
		}
		else if(controlSignal.equals("write")) {
			address = bus.getAddressLines();
			data = bus.getDataLines();
			memory.set(data, address);
		}
		else
			System.out.println("ControlLine Error: " + controlSignal);
	}
	
	public void dumpMemory() {
		memory.dump();
	}

}
