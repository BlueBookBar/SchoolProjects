package project2;


import java.io.*;
import java.util.*;



/*
Known Issues:
	Grouping does not work well, but will make some proper groups
	LinkedList reading throws exception despite checking and rechecking null pointer
	
 */

public class StudentServer extends Thread {


	//Initialize Thread independent variables
	public int Id =0;//Which Id number the student has
	public String School= null;//Which school the student belongs to
	public long randomTime = (System.currentTimeMillis()*3+System.currentTimeMillis())%5000;
	public static long randomTimeShow = (System.currentTimeMillis()*3+System.currentTimeMillis())%100;
	public static long randomTimeSnack = (System.currentTimeMillis()*3+System.currentTimeMillis())%10;
	public String attendedParades="";// Keeps track of how many parades a student has seen
	public String attendedShows="";//Keeps track of how many shows the student has seen
	public boolean lastAttended=true;// if it did not see the previous show, cannot go further



	//Initialize Thread shared variables
	public static int NumberBlue=0;//Keeps track of how many are in the group
	public static int NumberOrange=0;//Keeps track of how many are in the group
	public static volatile int Seats=ServerHelper.numSeat;//Number of seats available
	public static Object door = new Object();//Blocking object
	public static Object groupForm = new Object();//Object to synchronize the group function
	public static Object groupForm2 = new Object();//Object make other threads wait
	public static Object waitForOthers = new Object();//Object used to wait for the rest of the group
	public static LinkedList<GroupServer> Groups = new LinkedList<GroupServer>();//Will keep track of groups
	public static int Numa=0;//Number of waiting threads to be grouped
	public static GroupServer temp= new GroupServer();//Group to be inserted into LL
	public static int Completed=0;//Number of waiting threads to be grouped



	//Constructor
	public StudentServer(String school,int id) {//
		Id= id;
		//	msg("ID ="+ this.Id + "     id= "+ id);
		School = school;
		//	msg("ID ="+ this.Id + "     id= "+ id);
		this.setName(school+ id);
		//		msg("ID ="+ this.Id + "     id= "+ id);
	}


	//Message function prints time, thread name and a message
	public static long time = System.currentTimeMillis();
	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+ getName()+": "+m);
	}

	/*
	//test monitor method
	public void some(){
		synchronized(door){
			try {
				msg("I enter");
				sleep(100);
				msg("	I exit?");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	 */

	//DONE
	//Threads sleep for a random time
	public void arrive(){
		try {
			sleep(randomTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}




	public void Grouping(){
		while(true){

			synchronized(groupForm){
				if((NumberOrange == 2) && (NumberBlue == 1)){
					//Go back into loop and try again
				}
				else if(this.School == "Orange"){
					NumberOrange++;
					break;
				}
				else if(this.School == "Blue"){
					NumberBlue++;
					break;
				}
			}


			try {//if there are no more groups available 
				if(Completed  >= ServerHelper.maxGroupSize){
					break;//break out of the while loop and make you're own group
				}
				else{
					groupForm2.wait();//wait for the group to be finished
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		synchronized(waitForOthers){
			if(Completed  < ServerHelper.maxGroupSize){//For complete groups
				//wait method
				Numa++;
				if(Numa == 3){
					temp = new GroupServer();
					Completed++;
					waitForOthers.notify();
					waitForOthers.notify();
					Numa=0;
				}
				else if(Numa < 3){//is the number of threads is less than 3 wait
					try {
						waitForOthers.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if(this.School == "Orange"){
					temp.addOrange(Id);
					temp.addOrangeS(getName());

				}
				else if(this.School == "Blue"){
					temp.addBlue(Id);
					temp.addBlueS(getName());
				}

				//They are just making their own groups here
				synchronized(groupForm2){
					if(School == "Blue"){
						Groups.add(temp);//it will add group once
					}

					NumberOrange=0; NumberBlue=0;
					groupForm2.notifyAll();
				}

			}
			else{//for incomplete groups

				synchronized(groupForm2){
					temp = new GroupServer();
					if(this.School == "Orange"){
						temp.addOrange(Id);
						temp.addOrangeS(getName());

					}
					else if(this.School == "Blue"){
						temp.addBlue(Id);
						temp.addBlueS(getName());
					}
				}

				if(Groups.peekLast() != temp){
					Groups.add(temp);//it will add group
				}

				NumberOrange=0; NumberBlue=0;
			}
		}
	}

	//Goes up LL and releases the groups
	public synchronized void verifyGroups(){

		while(!Groups.isEmpty()){
			try{
				GroupServer temporary = new GroupServer();
				if(Groups.peekLast()!= null){
					temporary= Groups.getLast();
					Groups.removeLast();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}




	//Student marches in stadium
	public void Marching(){
		try {
			synchronized(ClockServer.paradeMon){
				if(ServerHelper.Timing.isAlive()){
					ClockServer.paradeMon.wait();
					attendedParades =attendedParades+ " "+ClockServer.paradeTitle+", ";
					lastAttended= true;
					sleep(ClockServer.randomTimeParade);
				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	//Student goes to a show
	public void Watching(){
		try {
			synchronized(ClockServer.showMon){

				if(ServerHelper.Timing.isAlive()){
					if(ClockServer.showOver || (Seats== 0 )){//If the shows are over or Seats taken, do nothing
						lastAttended=false;
					}
					else{
						//				msg("NUmber of seats is"+Seats);
						Seats--;
						ClockServer.showMon.wait();
						attendedShows =attendedShows+ " "+ClockServer.showTitle+", ";
						lastAttended=true;
						sleep(ClockServer.randomTimeShow);
						Seats++;
					}
				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



	//Thread sleeps
	public void snackTime(){
		try {
			sleep(randomTimeSnack);//Snack time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	//Prints out report
	public void endDay(){
		try {
			Writer outfile = new FileWriter("output.txt", true);

			if(attendedShows ==""){
				outfile.write("["+(System.currentTimeMillis()-time)+"] "+ getName()+": "+"Attended "+attendedParades + " and no shows"+"\r\n");

			}
			else{
				outfile.write("["+(System.currentTimeMillis()-time)+"] "+ getName()+": "+"Attended "+attendedParades + " and "+ attendedShows+"\r\n");
			}
			outfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void run(){



		//Students will arrive first
		arrive();
		//While the last parade is not over
		while(!ClockServer.paradeOver){
			if(lastAttended){
			Grouping();//Group them
			verifyGroups();//Release groups
			Marching();//They March
			snackTime();//Snack break
		}
			else{//If they missed the parade, break
				break;
			}
			if(lastAttended){//if you marched, go to a show
				Watching();//Watch the shows
				if(ClockServer.showOver){
					break;//If that was the last show, break
				}
			}
			else{//If they missed the show, break;
				break;
			}
		}		
		endDay();//Print report
	}

}

