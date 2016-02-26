import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class cli {

	public cli() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		System.out.println("Input the IP address of the server");

		int number;
		Scanner find = new Scanner(System.in);
		Scanner sc= new Scanner(System.in);
		Scanner temp1= new Scanner(System.in);
		Socket s;
		try {
		s = new Socket(find.nextLine(), 1342);

		Scanner sc1 = new Scanner(s.getInputStream());
		System.out.println("Enter any number");
		number = sc.nextInt();
		
		PrintStream p = new PrintStream(s.getOutputStream());
		p.println(number);
		p.println(number);
		int temp= sc1.nextInt();
		System.out.println(temp+ "WHAT");
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
