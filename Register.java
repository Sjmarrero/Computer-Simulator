package part3;

public class Register implements RegisterInterface {

	private int value;
	
	public Register() {
		value = 0;
	}

	public Register(int a) {
		value = a;
	}

	public void setValue(int integer) {
		value = integer;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(String str) {
		value = Integer.valueOf(str);
	}

}
