package part2;

public class ControlLines {

	private boolean read;
	private boolean write;
	
	public ControlLines() {
		read = false;
		write = false;
	}
	
	public void setRead() {
		read = true;
		write = false;
	}
	
	public void setWrite() {
		read = false;
		write = true;
	}
	
	public boolean getRead() {
		return read;
	}
	
	public boolean getWrite() {
		return write;
	}
	
}
