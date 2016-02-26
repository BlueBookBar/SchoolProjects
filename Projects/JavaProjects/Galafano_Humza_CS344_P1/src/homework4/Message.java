package homework4;

import java.io.Serializable;

public class Message implements Serializable {

	public int number, id;
	   
	public Message(int tempnumber, int tempid) { 
		this.number = tempnumber; this.id = tempid;
		}
	
}
