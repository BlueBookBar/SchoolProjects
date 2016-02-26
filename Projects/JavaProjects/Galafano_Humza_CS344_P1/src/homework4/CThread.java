package homework4;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class CThread extends Thread  {


	private ObjectInputStream  ois;

	// to write raw bytes
	private InputStream        isA;
	private InputStream        isB;
	private OutputStream       os;


	public CThread( InputStream isA, InputStream isB,  OutputStream os, ObjectInputStream ois) {
		this.isA=isA; this.isB=isB;   this.os=os;
		this.ois=ois;
	}

	public void run(){
		try{
			//Receive input Pipe data from A			
			int s =(int) isA.read();
			System.out.println("C reads "+s +".");
			
			//Test the output pipe to B			
			System.out.println("C wrote 10.");
			os.write(10);
			
			//Receive input Pipe Object from B	
            ois = new ObjectInputStream ( isB );
            Message m = (Message) ois.readObject();
			
            System.out.println("C received object with value "+m.number+ " and id " + m.id+" from B.");
			
		} // end TRY
		catch ( Exception exc ) {
			System.out.println
			( "Error Sender: " + exc );
		} // end CATCH
	}


}
