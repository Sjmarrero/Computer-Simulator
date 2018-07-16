package part2;

public class Register implements RegisterInterface {

	private int value;
	
	public Register() {
		value = 0;
	}

	public void setValue(int integer) {
		value = integer;
	}
	
	public int getValue() {
		return value;
	}

}
