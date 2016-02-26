package project;

import java.io.IOException;
import java.net.ServerSocket;

public class ClockServer extends Thread {

	int Port;
	String Ip;
	public static ServerSocket socket;

	
	public ClockServer(String ip,int port) {
		// TODO Auto-generated constructor stub
		Ip = ip;
		Port = port;
	/*	if(!socket.isClosed()){
			try {
				socket = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
		
	}


	public void run(){
		
	}

}
