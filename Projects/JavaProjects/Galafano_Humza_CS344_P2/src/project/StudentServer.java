package project;

import java.net.ServerSocket;

public class StudentServer extends Thread {

	public static int Id;
	public static int Port;
	
	public static ServerSocket socket = ClockServer.socket;
	
	public StudentServer(int id, String school, int port) {
		Id=id;
		Port = port;
		
	}

	public void run(){
		//System.out.println(Id);
	}

}
