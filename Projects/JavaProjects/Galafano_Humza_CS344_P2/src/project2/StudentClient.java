package project2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class StudentClient extends Thread {
	static int PORT;
	static String IP;


	public StudentClient(String type) {
		//Connect to the main server and input the 
		try {
			Socket s = new Socket(IP, PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

	public static boolean validInput(int orange, int blue, int seat){
		if(orange <=0){
			return false;
		}
		else if(blue <= 0){
			return false;
		}
		else if(seat <= 0){
			return false;
		}
		else{
			return true;
		}
	}

	public static void main (String [] args)
	{
		PORT = Integer.parseInt(args[1]);
		 IP = args[0];
		validInput(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));

		for (int i=0; i< Integer.parseInt(args[2]); i++){
			new StudentClient("Orange").start();
		}

		for (int i=0; i< Integer.parseInt(args[3]); i++){
			new StudentClient("Blue").start();
		}
	}

}
