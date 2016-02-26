package Project1;

public class Auditorium {
	public boolean isDoorOpen;
	public int numClasses;
	public int countClasses;
	public volatile boolean classDone;


	public Auditorium() {
		isDoorOpen = false;
	}

	public Auditorium(int a) {
		isDoorOpen = false;
		numClasses= a;
		countClasses=0;
		classDone = false;
	}



	//used by teacher
	public int nextClass(){
		synchronized(this){
			countClasses++;
		}
		return countClasses;
	}
	//use by teacher
	public void openDoor(){
		synchronized(this){
			isDoorOpen = true;

		}
	}
	//used by teacher
	public void closeDoor(){
		synchronized(this){
			isDoorOpen = false;
		}
	}
	//seen by student, controlled by teacher
	public void classIsDone(){
		synchronized(this){
			classDone = true;
		}
	}
	//seen by student, controlled by teacher
	public void classStart(){
		synchronized(this){
			classDone = false;
		}
	}



}
