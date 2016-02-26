import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MergeFile {

	static String faultyfile = null;

	public static void main(String[] args)throws IOException{//Step 0
		int okay = 0;
		while(okay == 0){//Step 3
			String first = JOptionPane.showInputDialog("What is the name of the first file to be merged?");
			String second = JOptionPane.showInputDialog("What is the name of the second file to be merged?");
			String output = JOptionPane.showInputDialog("What will the name of the merged file be?");			
			if(check(first) == true && check(second) == true){// Step 1
				mergeSort(first, second, output);//Step 2
			}
			else{
				System.out.println(faultyfile+" is not in alphabetical order. Therefore, "+ faultyfile+ " will not be merged.");
			}
			okay = JOptionPane.showConfirmDialog(null, "Are there other files to be merged?");
		}
	}

	public static void mergeSort(String first, String second, String output) throws IOException{
		int firnum, secnum;
		Scanner firstFile = new Scanner( new FileReader(first));//Step 8
		Scanner secondFile = new Scanner( new FileReader(second));// Step 8
		Writer outFile =  new FileWriter(output);// Step 8	
		firnum = firstFile.nextInt();// Step 9
		secnum = secondFile.nextInt();// Step 9
		while(firstFile.hasNext() && secondFile.hasNext()){// Step 11
			if(firnum < secnum){// Step 10
				outFile.write(new Integer(firnum).toString());
				outFile.write(" ");
				firnum = firstFile.nextInt();
			}
			else if(secnum < firnum){// Step 10
				outFile.write(new Integer(secnum).toString());
				outFile.write(" ");
				secnum = secondFile.nextInt();
			}
			else{// Step 10
				outFile.write(new Integer(firnum).toString());
				outFile.write(" ");
				firnum = firstFile.nextInt();
				secnum = secondFile.nextInt();
			}
		}
			while(firstFile.hasNext()){// Step 12
				firnum = firstFile.nextInt();
				outFile.write(new Integer(firnum).toString());
				outFile.write(" ");
			}
			while(secondFile.hasNext()){// Step 12
				secnum = secondFile.nextInt();
				outFile.write(new Integer(secnum).toString());
				outFile.write(" ");
			}
		outFile.close();
		secondFile.close();
		firstFile.close();
		System.out.println("Merge was successful.");
	}

	public static boolean check(String name) throws IOException{
		Scanner test = new Scanner (new FileReader(name));//Step 4
		int num1 = test.nextInt(), num2;//Step 5
		while(test.hasNext()){//Step 7
			num2 = test.nextInt();
			if(num1 > num2){//Step 6
				faultyfile = name;
				test.close();
				return false;
			}
			num1 = num2;
		}
		test.close();
		return true;
	}
}
