package Galafano_Humza_CS340_P2;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



public class Today {
	public static  Bathroom potty;
	public static Auditorium Hall;
	public static Thread Teach;
	public static Cafeteria Cafe;


	//Argument 0 Number of students
	//Argument 1 Size of Table capacity
	//Argument 2 Number of Tables


	public static void main(String[] args) throws InterruptedException{
		try {//output report file
			Writer outfile = new FileWriter("output.txt");
			outfile.write("Student Name   Total Number of classes taken   Class Number"+ "\r\n");
			outfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//open the object
		potty= new Bathroom();
		Hall = new Auditorium(3, Integer.valueOf(args[0]));
		Cafe = new Cafeteria(Integer.valueOf(args[2]),Integer.valueOf(args[1]));


		//initialize Threads
		Teach = new Thread(new Teacher("Mr. Bundy", Integer.valueOf(args[0])));
		Thread[] students = new Thread[Integer.valueOf(args[0])];

		for(int i = 0; i < Integer.valueOf(args[0]); i++){
			students[i] = new Thread(new Student(i));
			students[i].setName("Student"+ i);
		}

		//Run the Threads
		Teach.start();
		for(int i = 0; i < Integer.valueOf(args[0]); i++){
			students[i].start();
		}	
		Writer outfile;
		try {
			outfile = new FileWriter("output.txt");
			outfile.write("Student Name   Total Number of classes taken   Class Number"+ "\r\n");
			outfile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
