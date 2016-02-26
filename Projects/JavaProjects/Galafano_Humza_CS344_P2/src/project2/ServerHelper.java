package project2;

import java.io.Writer;

import project1.Clock;

public class ServerHelper extends Thread {

	public String Type;
	public int id;
	public int port;
	static Thread [] Students;
	static Clock Timing;
	static int numOrange=0;
	static int numBlue=0;
	static int numSeat=0;
	static int maxGroupSize =0;
	static int ThreadId = 1;
	static Writer outfile;

	
	
	public ServerHelper() {
	}
	
	public ServerHelper(int Id,String type , int PORT) {
		id = Id;
		port = PORT;
		Type = type;
	}

	public void checkMethod(int method){//add the method number that is requested through the 
		if((Type =="Orange")||(Type== "Blue")){
/*			switch(methodNumber){
			case 0:
			
			case 1:
				
			case 2:
				
			case 3:
				
			
			} */
		}
		else{
			
		}
		
	}
	
	public void run(){
	//	checkMethod();
	}
}
