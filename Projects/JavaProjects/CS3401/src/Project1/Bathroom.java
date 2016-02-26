package Project1;

public class Bathroom {
	public int countThread=0;//dedicated counter for each thread, tracks the order
	public boolean[] bathLine;//dedicated turn array
	public int turn= 0;//keeps track of  whose turn it is
	public static int count=0;//which index I am on initially 
	public boolean isOccupied= false;//locks the bathroom
	public long previousTime=0;//tracks previous time
	

	public Bathroom(int queueSize) {//constructor
		bathLine = new boolean[queueSize];//queue for the bathroom line
		for(int y = 0; y < queueSize;y++){
			bathLine[y] = false;//Set all to false, no one has gone  yet
		}
	}

	public Bathroom() {//constructor

		bathLine = new boolean[10];//queue for the bathroom line
		for(int y = 0; y < 10;y++){
			bathLine[y] = false;//Set all to false, no one has gone  yet
		}
	}
	
	
	
	public void use() throws InterruptedException{//Student goes inside the bathroom
		synchronized(this){
		isOccupied= true;//He locks the door
		Thread.sleep(100);//He uses
		System.out.println("Bathroom is Occupied by " + Thread.currentThread().getName());//People know...
		}		
	}		
	
	
	public void useB() throws InterruptedException{// combine use() and doneUse()
		synchronized(this){
			use();//Enter the bathroom
			doneUse();// Exit the bathroom
		}
	}
	
	
	public void doneUse(){//He is leaving the bathroom	
		synchronized(this){
		System.out.println(Thread.currentThread().getName()+ " is done with the bathroom.");//He declares the deed to be done.
		isOccupied = false;// It is not occupied for the moment
		turn++;//next turn
		}
	}
	
}
