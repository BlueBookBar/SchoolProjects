package Project1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



public class Today {
	public static  Bathroom potty;
	public static Auditorium Hall;
	public static Thread Teach;

	public  static Thread thread1 = new Thread(new Student(1));
	public	static Thread thread2 = new Thread(new Student(2));
	public	static Thread thread3 = new Thread(new Student(3));
	public	static Thread thread4 = new Thread(new Student(4));
	public	static Thread thread5 = new Thread(new Student(5));
	public	static Thread thread6 = new Thread(new Student(6));
	public	static Thread thread7 = new Thread(new Student(7));
	public	static	Thread thread8 = new Thread(new Student(8));
	public	static	Thread thread9 = new Thread(new Student(9));
	public	static	Thread thread10 = new Thread(new Student(10));



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
		Hall = new Auditorium(3);

		//Initialize the threads and name them
		Teach = new Thread(new Teacher("Mr. Bundy"));
		Thread thread1 = new Thread(new Student(1));thread1.setName("Student1");
		Thread thread2 = new Thread(new Student(2));thread2.setName("Student2");
		Thread thread3 = new Thread(new Student(3));thread3.setName("Student3");
		Thread thread4 = new Thread(new Student(4));thread4.setName("Student4");
		Thread thread5 = new Thread(new Student(5));thread5.setName("Student5");
		Thread thread6 = new Thread(new Student(6));thread6.setName("Student6");
		Thread thread7 = new Thread(new Student(7));thread7.setName("Student7");
		Thread thread8 = new Thread(new Student(8));thread8.setName("Student8");
		Thread thread9 = new Thread(new Student(9));thread9.setName("Student9");
		Thread thread10 = new Thread(new Student(10));thread10.setName("Student10");

		//starts the Threads
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		Teach.start();


	}

}
