package part2;

public class Status {

	private boolean zero;
	
	public Status() {
		zero = false;
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
}
