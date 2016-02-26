package Galafano_Humza_CS340_P2;

import java.util.concurrent.Semaphore;

public class Auditorium {
	public Semaphore doorAud1;//class# 1
	public Semaphore doorAud2;//Class# 2
	public Semaphore doorAud3;//Class# 3 


	public int numClasses;//Total number of classes
	public int countClasses;// count which class is on
	public int studentNum;//Total number of students
	public int studentoutside=0;


	public Auditorium() {
	}

	public Auditorium(int a) {
		numClasses= a;// get number of classes
		countClasses=0;//Counts the current class
	}

	public Auditorium(int a, int max) {
		doorAud1 = new Semaphore(max, true);//initialize the semaphores
		doorAud1.drainPermits();//prevent all access through the semaphores
		doorAud2 = new Semaphore(max, true);
		doorAud2.drainPermits();
		doorAud3 = new Semaphore(max, true);
		doorAud3.drainPermits();
		numClasses= a;
		countClasses=0;
		studentNum= max;

	}

	public void nextClass(){//next class
		countClasses++;
	}

	public void letIn(int a){//checks how many students are in queue and then opens enough permits for the queued student to be let in
		if(a == 1){
			nextClass();
			System.out.println(countClasses+" class has begun.");
			studentoutside = doorAud1.getQueueLength();
			doorAud1.release(studentoutside);
		}
		else if( a == 2){
			nextClass();
			System.out.println(countClasses+" class has begun.");
			studentoutside = doorAud2.getQueueLength();
			doorAud2.release(studentoutside);
		}
		else{
			nextClass();
			System.out.println(countClasses+" class has begun.");
			studentoutside = doorAud3.getQueueLength();
			doorAud3.release(studentoutside);
		}
	}

}
