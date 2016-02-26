package project;

public class MainClient {//Starts Threads, Done

	public static void main(String[] args) {
		
		new ClockClient(args[0],Integer.parseInt(args[1]),0,Integer.parseInt(args[4])).start();
		int temp=0;
		for(int i = 0; i < Integer.parseInt(args[2]); i++){
			temp=i+1;
			new StudentClient(args[0],Integer.parseInt(args[1]),temp,"Blue").start();
			
		}
		
		for(int i = 0; i < Integer.parseInt(args[3]); i++){
			temp++;
			new StudentClient(args[0],Integer.parseInt(args[1]), temp,"Orange").start();
			
		}
	}

}
