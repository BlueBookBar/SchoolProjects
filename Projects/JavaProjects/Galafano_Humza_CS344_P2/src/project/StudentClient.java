package project;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class StudentClient extends Thread {
	String Name = null;
	String Ip = null;
	int Id;
	int Port;
	int newPortNumber=-1;
	Socket s;
	Socket s1;
	//Scanner is to read from the input steeam
	//printstream is to print to the output stream
	public StudentClient(String ip, int port ,int id, String name) {
		Ip= ip;
		Port= port;
		Name = name;
		Id=id;
	}

	public void run(){// Connect with client and if the connection is made then start request method numbers
		//System.out.println("My name and id is "+Name+" "+Id);
		try {
			s = new Socket(Ip, Port);
			
			PrintStream i = new PrintStream(s.getOutputStream());
			i.print(Id+"\n");
			i.print(Name+"\n");
			Scanner	g = new Scanner(s.getInputStream());
			
			
			
		/*	newPortNumber= Integer.parseInt(g.nextLine());
			System.out.println(newPortNumber);
			s1= new Socket(Ip, newPortNumber);//use the new port number
			PrintStream i1 = new PrintStream(s1.getOutputStream());//new printstream
			Scanner g1 = new Scanner(s1.getInputStream());
			
			*/
		
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
