package Project1;

public class Teacher extends Thread {
	public static long time = System.currentTimeMillis();
	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

	public Teacher() {//constructor
	}
	public Teacher(String Name) {//constructor
		super(Name);
		setName(Name);
	}

	public void teachClass() throws InterruptedException{
		while(Today.Hall.countClasses < Today.Hall.numClasses ){//the number of classes to be taught
			try {
				Thread.sleep(200);//Gets to the auditorium and dreams of vacation
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(Today.Hall.countClasses==0){//first class
				msg("class# "+Today.Hall.countClasses);
				Today.Hall.openDoor();//Opens the auditoirum
				Thread.sleep(100);//Waits for students to enter
				Today.Hall.closeDoor();
				Today.Hall.classStart();
				Thread.sleep(1000);//Teaches for set amount of time
				Today.Hall.classIsDone();
				//				Today.wakeUpStudent();//wake up sleeping students
				Today.Hall.nextClass();
			}
			else if(Today.Hall.countClasses == 1){//second class
				Thread.sleep(100);//break
				msg("class# "+Today.Hall.countClasses);
				Today.Hall.openDoor();//Opens the auditoirum
				Thread.sleep(100);//Waits for students to enter
				Today.Hall.closeDoor();
				Today.Hall.classStart();
				Thread.sleep(1000);//Teaches for set amount of time
				Today.Hall.classIsDone();
				//			Today.wakeUpStudent();//wake up sleeping students
				Today.Hall.nextClass();
			}
			else{//third class
				Thread.sleep(100);//office hours+break
				msg("class# "+Today.Hall.countClasses);
				Today.Hall.openDoor();//Opens the auditoirum
				Thread.sleep(100);//Waits for students to enter
				Today.Hall.closeDoor();
				Today.Hall.classStart();//being class
				Thread.sleep(1000);//Teaches for set amount of time
				Today.Hall.classIsDone();//class is over
				//			Today.wakeUpStudent();//wake up sleeping students
				Today.Hall.nextClass();
			}		
		}
	}

	public void wakeUpBums(){

	}

	public void goToClass(){
		System.out.println("I am in charge.");
		try {
			msg("");
			Thread.sleep(3000);//Before the professor starts, he waits
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		goToClass();
		try {
			teachClass();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
