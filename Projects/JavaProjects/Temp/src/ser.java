import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ser {

	

	public static void main(String[] args) {
		try {

			
			ServerSocket s1 = new ServerSocket(1342);
			Socket ss = s1.accept();
			Scanner sc = new Scanner(ss.getInputStream());
			int number = sc.nextInt();
			int temp = number*2;
			number = sc.nextInt();
			
			temp += number;
			
			PrintStream p= new PrintStream(ss.getOutputStream());
			p.println(temp);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
