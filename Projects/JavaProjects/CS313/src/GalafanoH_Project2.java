import java.io.*;// Step 0: Prepare everything
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GalafanoH_Project2 {
	
	public static void main(String[] args) throws FileNotFoundException{
		int count = 0, b= 0, c = 0;	//Step 0
		String a = JOptionPane.showInputDialog("What is the name of the input file?");
		Scanner file = new Scanner(new File(a));// Step 1 : Find array size
		while(file.hasNext()){
			file.next();
			count++;
		}
		file.close();// Step 1
		int[] ara= new int[count+1];//Step 2// Copy values on to aray

		Scanner filea = new Scanner(new FileReader (a));
		while(filea.hasNext()){
			c = filea.nextInt();
			ara[b] = c;
			b++;
		}
		filea.close();
		for(b = 0; b <= count; b++){
			System.out.print(ara[b]+ " ");
		}
		selectionsort(ara, count-1);
	}
	
	public static int selectionsort(int[] a, int last){//Step3: Begin selection sort method
		int pos=0, walker=0, smallest = pos, temp;
		
		while(pos < last){//Step 4: repeat 3
			while(walker < last){ //Step 3
				walker++;
				if(a[walker] < a[smallest]){
					smallest = walker;
				}
			}
			temp = a[smallest];
			a[smallest] = a[pos];
			a[pos] = temp;
			System.out.println(" ");
			System.out.println("Result of iteration 1: "+" Position index "+pos+" Min index"+ smallest + " " );// Step 5: print
			for(int c = 0; c <= pos; c++){
				System.out.print(c + " ");
			}
			System.out.println(" ");
			for(int d = 0; d <= pos; d++){
				System.out.print(a[d] + " ");
			}
			pos++;
			walker = pos;
			smallest = pos;//Step 3
		}
		
		return 0;
	}
	
	
	
}
