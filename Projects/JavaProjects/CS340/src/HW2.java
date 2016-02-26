
public class HW2 {
	
	public static void main(){
		//Thread threadA = new Thread();
		HW threadB = new HW("seco");
		//Thread threadC = new Thread(new HW(), "Third_Thread");
		//threadA.start();
		threadB.start();
		//threadC.start();
		
		try{
			Thread.currentThread().sleep(1000);
		}
		catch(InterruptedException e){	
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread());
		
	}

}
