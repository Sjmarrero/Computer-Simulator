package part1;

public class Register implements RegisterInterface {

	private int value;
	
	public Register() {
		value = 0;
	}

	@Override
	public int getValue() {
		return value;
	}

	public void setValue(int integer) {
		value = integer;
	}

}
