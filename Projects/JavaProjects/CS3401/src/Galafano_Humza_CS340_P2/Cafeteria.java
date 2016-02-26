package Galafano_Humza_CS340_P2;

import java.util.concurrent.Semaphore;

public class Cafeteria {
	public Semaphore CafeGo;// Students tell the teacher to head over to the cafeteria when class phase is over, then they all go
	public Semaphore CafeLock;//Teacher will open the Cafe lock, when all student have arrived
	public Semaphore mutexTeachTable;//tells the teacher when a new table is available
	public Semaphore  mutexStudentTable;//Lets the teacher control how many students can sit at a time
	public Semaphore  mutexTeacherSit;//stops teacher until all student are in line to sit
	public Semaphore teacherLeave;//signals the teacher to go home
	public Semaphore mutexStudentEat;//Student eats and leaves exclusively

	public Table[] tables;//the tables
	public int numTable;// the number of tables


	public Cafeteria() {// constructor
	}


	//table capacity -> b
	//numb tables -> a
	public Cafeteria(int a, int b) {// constructor
		//Initialize all Semaphore and Lock them

		CafeLock = new Semaphore(Today.Hall.studentNum);
		CafeLock.drainPermits();
		CafeGo = new Semaphore(1);
		CafeGo.drainPermits();
		mutexTeachTable = new Semaphore(1);
		mutexTeachTable.drainPermits();
		mutexStudentTable = new Semaphore(Today.Hall.studentNum, true);
		mutexStudentTable.drainPermits();
		mutexTeacherSit = new Semaphore(1);
		mutexTeacherSit.drainPermits();
		teacherLeave = new Semaphore(1);
		teacherLeave.drainPermits();
		mutexStudentEat = new Semaphore(1,true);

		//Record number of tables
		numTable = a;
		//create tables
		tables = new Table[a];
		for(int i = 0; i < a;i++){
			tables[i] = new Table(b);
		}
	}

	void tableAvailable(){//used by teacher		
		try {
			mutexTeacherSit.acquire();//Teacher starts when all students are ready to sit
			while(mutexStudentTable.hasQueuedThreads()){//while some students are still waiting to sit
				for(int i = 0; i < numTable;i++){//find an available table to seat students
					if(tables[i].available== true){//found one
						tables[i].available=false;//no longer available
						mutexStudentTable.release(3);//Teacher permits 3 students to sit 
					}
				}
				mutexTeachTable.acquire();//Waits for another table to become available
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
