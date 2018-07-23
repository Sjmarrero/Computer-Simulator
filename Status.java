package part3;

public class Status {

	private boolean zero;
	private boolean state;
	
	public Status() {
		zero = false;
		state = true;
	}
	
	public void setZero() {
		zero = true;
	}
	
	public void unsetZero() {
		zero = false;
	}
	
	public boolean getZero() {
		return zero;
	}
	
	public boolean running() {
		return state;
	}
	
	public void setRunning(boolean value) {
		state = value;
	}
}
