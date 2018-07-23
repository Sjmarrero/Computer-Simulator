package part3;

public class MemoryControl {
	
	private Memory<String> memory;
	
	public MemoryControl() {
		memory = new Memory<String>();
	}
	
	public void execute(Bus bus) {
		String controlSignal = bus.getControlLines();
		int address;
		String data;
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
	
	public void add(String instruction, int index) {
		memory.set(instruction, index);
	}
	
	public void dumpMemory() {
		memory.dump();
	}

}
