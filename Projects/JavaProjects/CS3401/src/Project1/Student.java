package Project1;

import java.io.*;

public class Student extends Thread {
	public int id = -9;//Holds which thread goes first
	public int numclasses=0;
	public static long time = System.currentTimeMillis();
	public long rantime = (System.currentTimeMillis()*5+System.currentTimeMillis())%3000;
	public long rantime2 = (System.currentTimeMillis()*5+System.currentTimeMillis())%2500;
	public boolean attended=false;
	int student_id;//name of the student number
	public static boolean endday[]= new boolean [10];


	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

	public Student() {//constructor
	}
	public Student(int a) {//constructor
		student_id = a;
		setName("Student"+ student_id);
		for(int i = 0; i < 10; i++){
			endday[i] = false;
		}
	}
	public Student(String Name) {//constructor
		super(Name);
	}







	public void waitBath(){
		while(Today.potty.bathLine[id]== false){//If it is not your turn, listen for you turn
			if(Today.potty.turn == id){//if it is your turn then go
				Today.potty.bathLine[id]= true;//your turn has been called
				break;
			}
			yield();//Yield
		}
		while(Today.potty.isOccupied == true){// if the bath room is not open, keep knocking...
			yield();
		}
	}

	public void bathTime(){
		wakeUp();//Wake up the students
		waitBath();//Wait for the bathroom
		try {
			Today.potty.useB();//uses the bathroom
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

	public void wakeUp(){
		try {//Sleeps first and wake up at a random time
			Thread.sleep(rantime);
			if(Today.potty.previousTime ==rantime){//if the threads happen at the same time, then yield
				yield();
			} 
			Today.potty.previousTime =rantime;//record the previous time

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(id == -9){//if id is not yet assigned
			synchronized (this){
				id = Today.potty.countThread++;//Get the order of bathroom requests
			}
		}
	}





	public void run(){	
		bathTime();//Bathroom phase

		try { goToClass();} 
		catch (InterruptedException e) {e.printStackTrace();}
		
		synchronized(this){
			writeUp();
		}
		try {endOfDay();} 
		catch (InterruptedException e) {e.printStackTrace();}
		
		

	}

	public boolean checkEndDay(){
		endday[id]= true;
		for(int i=0 ; i < 10; i++){
			if(endday[i] == false){return true; }
		}
		return false;
	}


	//NOT DONE
	public void endOfDay() throws InterruptedException{//they all leave together

		while(Today.Teach.isAlive()){
		}
		while(checkEndDay()){//Wait until all the students are done with what they are doing
		}
//		System.out.println(id +" got past");
		if(student_id== 1 ){
			Today.thread1.join();
		}
		else if(student_id== 3){
			Today.thread3.join();
		}
		else if(student_id== 5){
			Today.thread5.join();
		}
		else if(student_id== 7){
			Today.thread7.join();
		}
		else if(student_id== 9){
			Today.thread9.join();
		}
	}



	public void inClass(){
		synchronized(this){
			attended= true;
		}
	}



	public void notInClass(){
		synchronized(this){
			attended= false;
		}
	}


	public void goToClass() throws InterruptedException{
		while(Today.Teach.isAlive()){//while the teacher is still teaching today
			while(Today.Hall.isDoorOpen== false){//if the door is close, do nothing
				if(Thread.interrupted()){//if it is interrupted
					//Do nothing
				}
			}

			numclasses++;//number of classes attended
			msg("    " + numclasses);

			inClass();//went to class	
			try{

				while(Today.Hall.classDone){//unable to use interrupt method, so I did this instead
					Thread.sleep(100);
				}

			}
			catch (InterruptedException e) {}
			notInClass();//no longer in class, after interruption
			int temp= getPriority();//having fun outside of class
			setPriority(getPriority());
			Thread.sleep(rantime2);//sleep again
			setPriority(temp);
		}
	}

	public void writeUp(){
		try {
			Writer outfile = new FileWriter("output.txt", true);
			String f= "";
			if(numclasses == 3){
				f="0, 1, 2";
			}
			else if(numclasses == 2){
				f="0, 1";
			}
			else if(numclasses == 1){
				f="0";
			}
			outfile.write("Student"+ student_id+ "                 "+numclasses+ "                    "+ f + "\r\n");
			outfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
