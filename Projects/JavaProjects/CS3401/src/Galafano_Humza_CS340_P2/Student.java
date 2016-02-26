package Galafano_Humza_CS340_P2;

import java.io.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class Student extends Thread {
	public  Semaphore mutex = new Semaphore(1);
	public  Semaphore mutexEnd = new Semaphore(1, true);//End of the day mutex
	public  Semaphore mutex2 = new Semaphore(1, true);

	public Semaphore write = new Semaphore(1);


	int student_id;//name of the student number
	//Initialize the times
	public static long time = System.currentTimeMillis();
	public long rantime = (System.currentTimeMillis()*5+System.currentTimeMillis())%1000;
	public long rantime2 = (System.currentTimeMillis()*5+System.currentTimeMillis())%2000;
	public long rantime3 = (System.currentTimeMillis()*5+System.currentTimeMillis())%25000;
	public long rantime4 = (System.currentTimeMillis()*5+System.currentTimeMillis())%5000;

	//Initialize the counters
	static int counterCafe =0;//When all student are ready to enter the cafeteria, teacher will unlock the doors
	static int counterSit =0;//When all students are ready to be seated, teacher will seat students	
	static int counterLeave = 0;// When the counter reaches total number of students, last student tells teacher to go home
	public boolean attended=false;
	public boolean[] whichClass = new boolean[4];

	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

	public Student() {//constructor
	}
	public Student(int a) {//constructor
		student_id = a;
		setName("Student"+ student_id);
		rantime= (rantime*student_id+(student_id*333))%1000;
		rantime2= (rantime2*student_id+(student_id*333))%2000;
		rantime3= (rantime3*student_id+(student_id*333))%25000;
		rantime4= (rantime4*student_id+(student_id*333))%5000;

		for(int i = 0; i < 4; i++){
			whichClass[i]=false;
		}
	}
	public Student(String Name) {//constructor
		super(Name);
	}


	public void run(){
		try {
			//Wake up & Bathroom phase------------------------------------

			Student.sleep(rantime);//Wakes up at random time
			Today.potty.inUse();//Goes to bathroom, if it is locked then wait
			msg(" is in the bathroom.");
			Student.sleep(rantime2);//Uses bathroom for some time
			msg(" is done with the bathroom.");
			Today.potty.outOf();//exits bathroom and releases lock

			//Class phase--------------------------------------------------

			waitForClass(1);
			waitForClass(2);
			waitForClass(3);//STUDENTS LEAVE BEFORE THE NEXT CLASS BEGINS

			//Cafeteria Phase--------------------------------------------------

			waitCafe();//tell the teacher you're done with the class phase
			Today.Cafe.CafeLock.acquire();//Teacher released the locks and the student takes it
			EatSleep();//Finish up the day


			//End of Day--------------------------------------------------
			write.acquire();
			Report();//Write the report 
			write.release();

		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	public void waitForClass(int a) {
		try{
			if(a == 1){
				if(Today.Hall.doorAud1.tryAcquire(rantime3,TimeUnit.MILLISECONDS)){//if the class door is open then enter

					whichClass[Today.Hall.countClasses] = true;//record class number boolean
					msg(" is in class " + Today.Hall.countClasses);
				}

				else {// Otherwise sleep
					Student.sleep(rantime4);
				}
			}
			else if(a == 2){		
				if(Today.Hall.doorAud2.tryAcquire(rantime3,TimeUnit.MILLISECONDS)){//if the class door is open then enter

					whichClass[Today.Hall.countClasses] = true;//record class number boolean
					msg(" is in class " + Today.Hall.countClasses);
				}

				else {// Otherwise sleep
					Student.sleep(rantime4);
				}
			} 
			else{
				if(Today.Hall.doorAud3.tryAcquire(rantime3,TimeUnit.MILLISECONDS)){//if the class door is open then enter

					whichClass[Today.Hall.countClasses] = true;//record class number boolean
					msg(" is in class " + Today.Hall.countClasses);
				}

				else {// Otherwise sleep
					Student.sleep(rantime4);
				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void waitCafe(){// lets teacher know to go to cafe after all students are here
		try {
			mutex.acquire();//one student at a time
			msg(" is hungry, and ready to go to cafeteria.");
			counterCafe++;
			if(counterCafe == Today.Hall.studentNum){//When all student are done with break and sleep, then go cafeteria
				Today.Cafe.CafeGo.release();//tell the teacher that they have arrived
			}
			mutex.release();//Next student can access
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void EatSleep(){//ends the day
		try {

			mutexEnd.acquire();
			counterSit++;
			if(counterSit==15){
				Today.Cafe.mutexTeacherSit.release();//Teacher will being to seat students
			}

			Today.Cafe.mutexStudentTable.acquire();//Student waits to have permission to be seated
			Today.Cafe.mutexStudentEat.acquire(); //one student at a time

			msg("Eats.");
			counterLeave++;//counts how many kids leave
			msg("Leaves cafteria");
			if(counterLeave%3==0){// if a table is empty

				Today.Cafe.tables[1].available= true;//the table is ready
				Today.Cafe.mutexTeachTable.release();//if three students have left signal teacher to get another table
			}

			if(counterLeave == 15){//if last student is on his way out he tell the teacher to leave
				Today.Cafe.teacherLeave.release();//releases teacher
			}
			Today.Cafe.mutexStudentEat.release();//other student can 

			mutexEnd.release();//other student can access
			msg(" Goes home and sleeps.");

		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

	public void Report(){//Write the report in a text file (output.txt)
		try {
			Writer outfile;
			String cl="";
			int numclasses=0;
			outfile = new FileWriter("output.txt", true);
			for(int i = 0; i < 3; i++){
				if(whichClass[i]){
					numclasses ++;
					cl =cl+ " "+ i ;
				}
			}
			outfile.write("Student"+ student_id+ "                 "+numclasses+ "                    "+ cl + "\r\n");
			outfile.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}


}
