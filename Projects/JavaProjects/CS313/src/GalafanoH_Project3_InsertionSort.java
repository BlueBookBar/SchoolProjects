import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class GalafanoH_Project3_InsertionSort {//Step 0

	public static void main(String[] args) throws FileNotFoundException{
		int count = 0, data, last = 0, index, spot = 0;
		String a = JOptionPane.showInputDialog("What is the name of the input file?");
		a = "InsertionSortData.txt";
		Scanner fileb = new Scanner(new File(a));//Step 1				
		while(fileb.hasNext()){
			fileb.next();
			count++;//Step 2									
		}
		fileb.close();//Step 3
		
		
		int arry[] = new int[count];//Step 4
		Scanner file = new Scanner(new File(a));// Step 5
		while(file.hasNext()){		
			//Step 6
			arry[last] = file.nextInt();//Step 7			
			data = file.nextInt();//Step 8
			for(spot = 0; spot <= last; spot++){
				if(arry[spot] < data){// Step 10
					break;
				}
			}
				last++; 						
				index = last;//Step 11
				while(index > spot){
					arry[index] = arry[index-1];//Step 12
					index--;
				}
												
				arry[spot] = data;
				print(last,data,spot,arry);
			
		}
		file.close(); 							
			
		
		
		
	}
	
	public static void print(int last, int data, int spot, int[] arry){
		System.out.println("last = " + last + " data = " + data+ " spot = "+spot );
		int a = 0;										
		while(a <= last){
			System.out.print(" ary["+a+"] ");
			a++;	
		}
		System.out.println("");								
		a=0;
		while(a <= last){
			System.out.print(arry[a]+"      ");
			a++;	
		}
		System.out.println();
	}
}
