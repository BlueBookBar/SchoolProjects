import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JOptionPane;



public class tester {

	public static void main(String[] args) throws FileNotFoundException{
	System.out.println("It works");
	int count = 0, data, last = 0, index, found;
	String a = JOptionPane.showInputDialog("What is the name of the input file?");
	Scanner fileb = new Scanner(new File(a));
	while(fileb.hasNext()){
		fileb.next();
		count++;
	}
	fileb.close();
	
	
	int arry[] = new int[count];
	Scanner file = new Scanner(new File(a));
	System.out.println("BOOM");
	arry[last] = file.nextInt();
	while(file.hasNext()){
		data= file.nextInt();
		for(found = 0; found < last; found++){
			if(arry[found] > data){
				break;
			}
			last++;
			index = last;
			while(index > found){
				arry[index] = arry[index-1];
				index--;
			}
			arry[found] = data;

		}
	}
	file.close();
	
	}
	
	public int nonMultiple23Count(int [] irrelevant, int maxNumber){//begin function
		boolean[] trueFalseArrayCount = new boolean[maxNumber+1];//will hold true/false for multiple of 2/3
		int returnCount = 0;//will return actual count
		trueFalseArrayCount[0] = trueFalseArrayCount[1]= false;// 0,1 dont count
		for(int index = 2; index <= maxNumber; index++){//first loop
			trueFalseArrayCount[index] = true;// make all true
			}//end loop
			for(int index = 2; index*index <= maxNumber;index++){//begin second loop
				if(trueFalseArrayCount[index]){//if they are ture, they all are
					for(int indexSecond =index; index*indexSecond<=maxNumber; indexSecond++){//begin 3 internal loop
						trueFalseArrayCount[index*indexSecond] = false;//multiple of 2/3 are false
						}//end if
				}//end loop
			}
		for(int index=2; index<=maxNumber;index++){//begin loop
			if(trueFalseArrayCount[index]){//counts true
				returnCount++;//counts numbers
				}//end count
			}
		return returnCount;//return count
	}
	
	
	
	
	GalafanoH_Project4Node listHead;
	
	public void storetext(String a) throws FileNotFoundException{
		String data; GalafanoH_Project4Node walker;
		Scanner input = new Scanner(new FileReader(a));
		//I need two cases, to insert in middle or to insert at end
		while(input.hasNext()){
			walker = listHead;// start from the node pointer
			data = input.next();// get the next character
			//while it has more characters
			//develop first case
			
			//middle case
			while(walker.next() !=null){// while there is a next
				if(walker.next().word().compareTo(data) ==0){//if the next string has been already added, then add one and move on
					walker.next().addcount();
					break;
				}
				if(walker.next().word().compareTo(data) < 0){//if the next string is less than my data then insert it there, middle insert
					GalafanoH_Project4Node temp = new GalafanoH_Project4Node(data, walker.next(), 1);
					walker.setNext(temp);
					break;
				}
				else {
					walker = walker.next();
				}
			}
		
			
		}
		
		
		
	}
}
