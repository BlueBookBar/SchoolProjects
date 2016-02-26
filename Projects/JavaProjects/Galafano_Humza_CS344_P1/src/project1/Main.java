package project1;

import java.io.*;


public class Main {

	//Universal variables
	static Thread [] Students;
	static Clock Timing;
	static int numOrange=0;
	static int numBlue=0;
	static int numSeat=0;
	static int maxGroupSize =0;
	static int ThreadId = 1;
	static Writer outfile;


	//Method validate input so that the 3 Integers are above 0
	public static boolean validInput(int orange, int blue, int seat){
		if(orange <=0){
			return false;
		}
		else if(blue <= 0){
			return false;
		}
		else if(seat <= 0){
			return false;
		}
		else{
			return true;
		}
	}



	public static void main(String[] args){
		try{
			//Less then 3 arguments ends the program
			if(args.length < 3){
				System.out.println("Not enough arguments.");
				System.exit(-1);
			}

			//Assign variables to input and check if they are valid
			numOrange =Integer.valueOf(args[0]);
			numBlue = Integer.valueOf(args[1]);
			numSeat =  Integer.valueOf(args[2]);


			//Determine max number of groups
			if((numOrange/2)> numBlue){
				maxGroupSize= numBlue;

			}
			else if((numOrange/2) <numBlue){
				maxGroupSize= numOrange;
			}


			//Invalid inputs?
			if(!validInput(numOrange, numBlue, numSeat)){
				System.out.println("Invalid command line inputs.");
				System.exit(-1);
			}


			//Make the Clock Thread to keep track of the timings
			Timing = new Clock();
			Timing.setPriority(10);
			Timing.start();

			//Make output File
//			outfile = new FileWriter("output.txt");
			
			//Make Thread array
			Students = new Thread[numBlue+ numOrange+1];


			//Make and start Orange students
			for(int i = 0; i < numOrange; i++){
				Students[i] = new Thread(new Student("Orange" ,ThreadId));
				Students[i].start();
				ThreadId++;

			}
			//Make and start Blue students
			for(int i = ThreadId; i < (numBlue+ numOrange+1); i++){
				Students[i] = new Thread(new Student("Blue",ThreadId));
				Students[i].start();
				ThreadId++;
				
			}
		//	outfile.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}


	}



}
