package part3;

public class MemoryDataRegister {

	private String data;
	
	public MemoryDataRegister() {
		data = null;
	}
	
	public void setValue(String value) {
		data = value;
	}
	
	public void setValue(int value) {
		data = Integer.toString(value);
	}
	
	public String getValue() {
		return data;
	}
}
