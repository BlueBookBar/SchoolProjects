
public class HW2 {
	
	public static void main(String[] args){
int y = 0;
		Thread threadA = new Thread(new HW(), "First_Thread");
		Thread threadB = new Thread(new HW(), "Second_Thread");
		Thread threadC = new Thread(new HW(), "Third_Thread");
		threadA.start();
		threadB.start();
		threadC.start();
		
		while(true){


		
		try{
			Thread.currentThread().sleep(1000);
		}
		catch(InterruptedException e){	
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread());
		}
	}

}
