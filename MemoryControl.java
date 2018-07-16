package part2;

public class MemoryControl {
	
	public void execute(Bus bus) {
		String controlSignal = bus.getControlLines();
		if(controlSignal.equals("read")) {
			
		}
		else if(controlSignal.equals("write")) {
			
		}
		else
			System.out.println("ControlLine: " + controlSignal);
	}

}
