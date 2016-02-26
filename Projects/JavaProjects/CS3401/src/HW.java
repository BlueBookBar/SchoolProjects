
public class HW extends Thread {
	 int t=0;
	protected  int times(){
		return t++;
		
	}
	protected static final long age(){
		return System.currentTimeMillis();
	}
	
 	public HW(){
	}
	
	public HW(String tName){
		super (tName);
	}
	
	
	public void run(){
		System.out.println(HW.age());
		System.out.println(Thread.currentThread());
		times();
		System.out.println(t);
		
	}
	
	public int number(){
		return (int) (age()%3);
	}
}
