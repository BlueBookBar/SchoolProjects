package Galafano_Humza_CS340_P2;

import java.util.concurrent.Semaphore;


public class Teacher extends Thread {

	public int studentMax=0;//total number of students
	public static long time = System.currentTimeMillis();
	public long rantime = (System.currentTimeMillis()*5+System.currentTimeMillis())%5000;
	public long breaktime = (System.currentTimeMillis()*5+System.currentTimeMillis())%2000;
	public long teachtime = 10000;

	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

	public Teacher() {//constructor
	}
	public Teacher(String Name) {//constructor
		super(Name);
		setName(Name);
	}
	public Teacher(String Name, int max) {//constructor

		super(Name);
		setName(Name);
		studentMax = max;

	}

	public void run(){
		try {
			Teacher.sleep(rantime);

			//Teach Phase-----------------------------------------------


			Today.Hall.letIn(1);//let them into the classes
			teach();//teach
			breakTime(1);//break
			Today.Hall.letIn(2);
			teach();
			breakTime(2);		
			Today.Hall.letIn(3);
			teach();

			//Cafeteria Phase-----------------------------------------------


			Today.Cafe.CafeGo.acquire();//teacher stops until students tell him to go to cafeteria
			msg(" tells class to head over to cafeteria.");

			msg(" opens the Cafeteria doors.");
			Today.Cafe.CafeLock.release(studentMax);//let all the students through after they tell him

			msg("Begins to seat students");
			Today.Cafe.tableAvailable();//Seats students
			Today.Cafe.teacherLeave.acquire();//Tries to leave
			msg(" goes home and sleeps.");

		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}




	public void teach(){//teach the class
		try {
			msg(" has begun to teach.");
			Teacher.sleep(teachtime);
			msg(" Class is over");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void breakTime(long a){//Break time
		try {
			msg(" Break has begun");

			Teacher.sleep(breaktime * a);
			msg(" Break is over");

		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}


