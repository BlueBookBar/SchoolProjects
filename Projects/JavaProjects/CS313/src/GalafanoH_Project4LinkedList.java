import java.io.*;//Step 0
import java.util.Scanner;

import javax.swing.JOptionPane;


import java.lang.String;//Step 0

public class GalafanoH_Project4LinkedList {
	GalafanoH_Project4Node listHead = new GalafanoH_Project4Node();

	public GalafanoH_Project4LinkedList() {
	}


	public void storetext(String a) throws FileNotFoundException{//Step 0
		String data; GalafanoH_Project4Node walker;
		Scanner input = new Scanner(new FileReader(a));


		while(input.hasNext()){//Step 8
			data = input.next();
			walker = listHead;	
			while(walker.next()!= null){//Step 5
				if(walker.next().word().compareToIgnoreCase(data) < 0){//Step 4
					walker = walker.next();	
				}
				else{	
					break;
				}
			}
			if(walker.next() != null){//Step 6
				if(walker.next().word() == data){
					walker.next().addcount();
				}
			}
			else{//Step 6
				if(walker.next() != null){
					GalafanoH_Project4Node temp = new GalafanoH_Project4Node(data, walker.next(), 1);
					walker.setNext(temp);
				}
				else{//end
					walker.setNext(new GalafanoH_Project4Node(data,null,1));

				}

			}

		}	
		this.print();//Step 7
		input.close();//Step 9
	}




	public void print(){//Step 0
		GalafanoH_Project4Node current =listHead.next();
		System.out.print("Listhead " + "--> ");
		while(current.next() != null){
			System.out.println("("+current.word()+", "+current.count()+", "+current.next().word()+")"+ "--> " );
			current = current.next();
		}
		System.out.println("NULL");
	}




	public static void main(String[] args) throws FileNotFoundException{//Step 0
		String a = JOptionPane.showInputDialog("What is the name of the text file?");
		GalafanoH_Project4LinkedList b = new GalafanoH_Project4LinkedList();
		b.storetext(a); 
	}


}
