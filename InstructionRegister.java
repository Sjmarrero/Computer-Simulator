package part3;

public class InstructionRegister extends Register {

	private String value;
	
	public InstructionRegister() {
		value = null;
	}
	
	public void setValue(String instruction) {
		value = instruction;
	}
	
	public String getInstruction() {
		return value;
	}
	
}
