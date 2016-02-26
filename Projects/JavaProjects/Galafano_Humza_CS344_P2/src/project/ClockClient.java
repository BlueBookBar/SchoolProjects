package project;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClockClient extends Thread {
	int Seats;
	
	int Port;
	int newPortNumber=-1;
	String Ip;
	Socket s;
	Socket s1;

	public ClockClient(String ip, int port, int id,int seats) {
		Ip= ip;
		Port= port;
		Seats = seats;
	}


	public void run(){//Connect with client and send request for code to run
		try {
			s = new Socket(Ip, Port);
			
			PrintStream i = new PrintStream(s.getOutputStream());
			i.print(0+"\n");
			i.print("Clock"+"\n");
			
			Scanner	g = new Scanner(s.getInputStream());
			newPortNumber= Integer.parseInt(g.nextLine());
			/*s1= new Socket(Ip, newPortNumber);//use the new port number
			PrintStream i1 = new PrintStream(s1.getOutputStream());//new printstream
			Scanner g1 = new Scanner(s1.getInputStream());
			
			s.close();
			i.close();
			g.close();
			s1.close();
			i1.close();
			g1.close();
			*/
			
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testRun(){
		
	}
	
}
