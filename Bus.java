package part3;

public class Bus implements BusInterface {
  
  private ControlLines control;
  private AddressLines address;
  private DataLines data;
  
  public Bus() {
	  control = new ControlLines();
	  address = new AddressLines();
	  data = new DataLines();
  }
  
  public void setAddressLines(int value) {
	  address.setAddress(value);
  }
  
  public int getAddressLines() {
	  return address.getAddress();
  }
  
  public void setControlLines(String controlSignal) {
	  if(controlSignal.equals("read")) {
		  control.setRead();
	  }
	  else if(controlSignal.equals("write")) {
		  control.setWrite();
	  }
  }
  
  public String getControlLines() {
	  if(control.getRead())
		  return "read";
	  else if(control.getWrite())
		  return "write";
	  else
		  return "error";
  }
  
  public void setDataLines(String value) {
	  data.setData(value);
  }
  
  public void setDataLines(int value) {
	  data.setData(Integer.toString(value));
  }
  
  public String getDataLines() {
	  return data.getData();
  }
	
}
