package project2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {

	public MainServer(String IP, int PORT) {		
		try{
			Scanner sc;
			int Id;
			String type;
			ServerSocket s1;
			while(true){
				 s1 = new ServerSocket(PORT);
				Socket ss = s1.accept();
				
				//pass thread Id and then Name
				sc = new Scanner(ss.getInputStream());
				//Id = sc.nextInt();
				//type = sc.next();
				System.out.println("WHAT");
				
				//spawn a helper
				//new ServerHelper(Id, type, PORT);
				sc.close();
			}
		}
		catch(IOException e){
			System.out.println("Unable to listen to port.");
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
//		System.out.println(args[0]+" "+ args[1]);
		try{
			new MainServer(args[0], Integer.parseInt(args[1]));
		}
		catch(Exception e){
			e.printStackTrace();
		}

		
	}

}
