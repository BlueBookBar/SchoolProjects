package project1;





public class Clock extends Thread {//Keep the times for each

	public static long randomTimeParade = (System.currentTimeMillis()*3+System.currentTimeMillis())%100;
	public static long randomTimeShow = (System.currentTimeMillis()*3+System.currentTimeMillis())%100;

	public static long time = System.currentTimeMillis();
	public void msg(String m) {
		System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}
	public static boolean paradeLine= true;//If they can enter the line, then go
	public static String paradeTitle="11:00PM";//Initially the first parade
	public static String showTitle="11:45PM";//Initially the first show
	public static boolean showOver=false;//last show is done
	public static volatile boolean paradeOver= false;//last parade is done

	public static Object paradeMon = new Object();
	public static Object showMon = new Object();


	//Done
	//Updates and announced parade and then notifies waiting students
	public static void ParadeBegin(String temp){
		try {
			synchronized(paradeMon){
				paradeTitle = temp+ "-Parade";//Update parade title
				System.out.println("["+(System.currentTimeMillis()-time)+"] "+"Start the "+ temp+" parade!");//Announce parade
				paradeMon.notifyAll();//Let them march
				sleep(randomTimeParade);//Goes on for a time
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}




	public static void ShowStart(String temp){
		try {
			synchronized(showMon){
				showTitle = temp+ "-Show";
				System.out.println("["+(System.currentTimeMillis()-time)+"] "+"It's show time!"+"("+temp+")");
				showMon.notifyAll();//Let them march
				sleep(randomTimeShow);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public Clock() {
	}

	//Every second is 10 minutes
	// 6 seconds for every hour
	//Time = Hours*6
	public void run(){
		while((System.currentTimeMillis()-time)< (2400*6)){// Run for 24 hours
			//Parade Timing
			if((System.currentTimeMillis()-time)== (1100*6)){//11:00PM
				ParadeBegin("11:00AM");
			}
			if((System.currentTimeMillis()-time)== (1200*6)){//12:00PM
				ParadeBegin("12:00PM");
			}
			if((System.currentTimeMillis()-time)== (1300*6)){//1:00PM
				ParadeBegin("1:00PM");
			}
			if((System.currentTimeMillis()-time)== (1400*6)){//2:00PM
				ParadeBegin("2:00PM");
			}
			if((System.currentTimeMillis()-time)== (1500*6)){//3:00PM
				ParadeBegin("3:00PM");
			}
			if((System.currentTimeMillis()-time)== (1600*6)){//4:00PM
				ParadeBegin("4:00PM");
			}
			if((System.currentTimeMillis()-time)== (1700*6)){//5:00PM
				ParadeBegin("5:00PM");
				paradeOver=true;

			}

			//Show Times
			if((System.currentTimeMillis()-time)== (1175*6)){//11:45PM
				ShowStart("11:45PM");
			}	
			if((System.currentTimeMillis()-time)== (1325*6)){//1:15PM
				ShowStart("1:15PM");
			}	
			if((System.currentTimeMillis()-time)== (1475*6)){//2:45PM
				ShowStart("2:45PM");
				showOver= true;
			}
		}

		synchronized(paradeMon){
			paradeMon.notifyAll();//Get rid of any loitering Students
		}
		synchronized(showMon){
			showMon.notifyAll();//Get rid of any loitering students
		}
	}
}


