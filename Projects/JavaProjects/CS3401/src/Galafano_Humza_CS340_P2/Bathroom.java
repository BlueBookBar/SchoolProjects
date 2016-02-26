package Galafano_Humza_CS340_P2;
import  java.util.concurrent.Semaphore;

public class Bathroom {
	public Semaphore mutexBath = new Semaphore(1);//mutex for the bathroom
	public static long time = System.currentTimeMillis();

	public void inUse(){//Mutex, only one student at a time
		try {
			mutexBath.acquire();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	public void outOf(){// end of mutex, other student can now enter
		mutexBath.release();
	}


}
