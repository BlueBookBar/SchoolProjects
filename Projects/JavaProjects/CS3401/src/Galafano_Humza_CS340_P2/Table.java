package Galafano_Humza_CS340_P2;

public class Table {

	int chairs;//how many can sit
	int studentSit[];// which students are sitting
	boolean available;//is the table available?

	public Table() {// constructor
	}

	public Table(int a) {// constructor
		available= true;//table is available initially
		chairs=a;// 3 chairs
		studentSit = new int[a];//Initialize array 
		for(int i = 0; i < a;i++){
			studentSit[i] =-9;//Garbage value first
		}

	}

}


