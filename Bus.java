package part1;

public class Bus implements BusInterface {
  
  private int integer;
  
  public Bus() {
	  integer = 0;
  }
  
  public void setValue(int a ) {
		
	  integer = a;
	  
  }
	
  public int getValue() {
	  
	  return integer;
  }
	
}
