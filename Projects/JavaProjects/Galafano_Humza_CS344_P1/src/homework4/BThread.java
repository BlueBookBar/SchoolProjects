package homework4;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class BThread extends Thread {


	private ObjectOutputStream oosC;
	private ObjectOutputStream oosA;
	
	// to write raw bytes
	private InputStream        is;
	private OutputStream       osA;
	private OutputStream       osC;


	public BThread( InputStream is,  OutputStream osA, OutputStream osC,  ObjectOutputStream oosA ,  ObjectOutputStream oosC ) {
		this.is=is;   this.osA=osA; this.osC=osC;
		this.oosC=oosC;  this.oosA=oosA;
	}

	public void run(){
		try{			
			//Receive input Pipe data from C
			int a= is.read();
			System.out.println("B reads "+ a+".");
			
			//Create and send object to A through the pipe
			Message mA = new Message(15, 1);
			System.out.println("B sent object with number " +mA.number+ " and id "+ mA.id+" to A." );
			oosA = new ObjectOutputStream( osA );
            oosA.writeObject( mA );
            

			//Create and send object to C through the pipe
			Message mC = new Message(20, 2);
			System.out.println("B sent object with number " +mC.number+ " and id "+ mC.id+" to C." );
			oosC = new ObjectOutputStream( osC );
            oosC.writeObject( mC );
			
		} // end TRY
		catch ( Exception exc ) {
			System.out.println
			( "Error Sender: " + exc );
		} // end CATCH
	}

}
