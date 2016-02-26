package homework4;


import java.io.*;

public class AThread extends Thread  {

	private ObjectInputStream  ois;

	// to write raw bytes
	private InputStream        is;
	private OutputStream       os;


	public AThread( InputStream is,  OutputStream os, ObjectInputStream ois) {
		this.is=is;   this.os=os;
		this.ois=ois;
	}

	public void run(){
		try{			
			//Test the output pipe to C
			System.out.println("A wrote 5.");
			os.write(5);
			
			//Receive input Pipe Object from B	
            ois = new ObjectInputStream ( is );
            Message m = (Message) ois.readObject();
			
            System.out.println("A received object with value "+m.number+ " and id " + m.id+" from B.");
            
		} // end TRY
		catch ( Exception exc ) {
			System.out.println
			( "Error Sender: " + exc );
		} // end CATCH
	}

}
