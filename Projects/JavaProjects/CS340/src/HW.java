
public class HW extends Thread {
	
	protected static final long age(){
		return System.currentTimeMillis();
	}
	
 	public HW(){
	}
	
	public HW(String tName){
		super (tName);
		System.out.println(this);
		start();
	}
	
	
	public void run(){
		System.out.println(HW.age());
		System.out.println(Thread.currentThread());
	}
	
}
