package homework4;
import java.io.*;

public class Connector {

	static private PipedInputStream   pisAC;
	static private PipedOutputStream  posAC;

	static private PipedInputStream   pisCB;
	static private PipedOutputStream  posCB;

	static private PipedInputStream   pisBC;
	static private PipedOutputStream  posBC;

	static private PipedInputStream   pisBA;
	static private PipedOutputStream  posBA;

	static private ObjectOutputStream oosBC;
	static private ObjectInputStream  oisBC;

	static private ObjectOutputStream oosBA;
	static private ObjectInputStream  oisBA;


	public static void main(String[] args) {
		try{
			// set up a pipe
			System.out.println( "Pipe setup" );

			posAC = new PipedOutputStream( );
			pisAC = new PipedInputStream ( posAC );

			posCB = new PipedOutputStream( );
			pisCB = new PipedInputStream ( posCB );

			posBC = new PipedOutputStream( );
			pisBC = new PipedInputStream ( posBC );

			posBA = new PipedOutputStream( );
			pisBA = new PipedInputStream ( posBA );


			System.out.println( "Object creation" );
			//A has input Pipe from B(objects), has output Pipe for C(primitive)
			AThread TA = new AThread( pisBA, posAC, oisBA);
			//B has input Pipe for C(primitive), has output Pipe for A(objects) and C(objects)
			BThread TB = new BThread(pisCB, posBA, posBC, oosBA, oosBC);
			//C has input Pipe for A(primitive) and B(objects) , has output for B(primitive)
			CThread TC = new CThread(pisAC ,pisBC, posCB, oisBC);

			System.out.println( "Thread execution" );
			TA.start(); TB.start(); TC.start();

		}
		catch(Exception exc){

		}

	}

}
