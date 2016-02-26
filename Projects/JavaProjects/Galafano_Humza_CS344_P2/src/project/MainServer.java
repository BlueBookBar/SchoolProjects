package project;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainServer {

	private ServerSocket socket;
	private ServerSocket socket2; 
	private String Ip;
	public MainServer(String IP, int PORT) {		
		try{
			Ip = IP;
			Scanner sc;
			int i= 0;
			String Id;
			String type= null;
			socket = new ServerSocket(PORT);
			socket2= new ServerSocket(0);//Generate another available port number and pass it to the server thread
			int newPortNumber= socket2.getLocalPort();

			while(true){
				
				Socket serverssocket = socket.accept();
				sc = new Scanner(serverssocket.getInputStream());
				Id= sc.nextLine();
				type = sc.nextLine();
				
				//spawn a helper
				if(type.contains("ue")){

					PrintStream g = new PrintStream(serverssocket.getOutputStream());
					g.print(newPortNumber+"\n");
					new StudentServer(Integer.parseInt(Id), "Blue", newPortNumber).start();
				}
				else if (type.contains("ang")){

					PrintStream g = new PrintStream(serverssocket.getOutputStream());
					g.print(newPortNumber+"\n");
					new StudentServer(Integer.parseInt(Id), "Orange", newPortNumber).start();
					//send over port number of new port  and make it static for both clock and student threads
					//Now 
				}
				else{
					PrintStream g = new PrintStream(serverssocket.getOutputStream());
					g.print(newPortNumber+"\n");
					new ClockServer(Ip,newPortNumber).start();
			//send over port number of new port 
					//PrintStream ps = new PrintStream(serverssocket.getOutputStream());
					//i.print(Name);
				}
				i++;
				
				
				sc.close();
			
				
			}
		}//catch(InputMismatchException e){}
		catch(IOException e){
			System.out.println("Unable to listen to port.");
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		try{
			new MainServer(args[0], Integer.parseInt(args[1]));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		
	}

}
