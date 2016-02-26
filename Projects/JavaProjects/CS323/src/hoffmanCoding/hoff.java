package hoffmanCoding;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;


public class hoff {

	static String histogram(String f){//Step 4.0
		try{
			int size, countarysize = 0;
			String out = JOptionPane.showInputDialog("Enter size of histogram array.");
			size = Integer.parseInt(out);

			out = "data.txt";			//change back to data.txt
			char temp = 0;
			String tempstr = null;
			int charconv = 0;
			Scanner infile = new Scanner(new FileReader(f));
			Writer outfile = new FileWriter(out);
			int count[]= new int[size];
			for(int loop = 0; loop < size; loop++){//Step 4.0
				count[loop] = 0;
			}
			while(infile.hasNext()){//Step 4.1

				tempstr = infile.next();

				for(int innerloop = 0; innerloop < tempstr.length(); innerloop++){

					countarysize++;//Step 4.2
					temp= tempstr.charAt(innerloop);
					charconv = temp;
					count[charconv]= count[charconv] + 1;
				}


			}
			char charstr;
			int index = 33;//Step 4.3
			while(index < 177){//Step 4.7
				charstr = (char) index;//Step 4.4
				if(count[index] == 0){
					index++;
				}
				else{
				outfile.write(charstr+ "  "+((count[index]*100)/countarysize) +"\r\n");//Step 4.5
				index++;//Step 4.6
				}
			}
			infile.close();
			outfile.close();
			return out;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}





	public static void main(String [] args){// Step 2.0
		if (args.length < 3){
			System.out.println("Not enough arguments passed.");
			return;
		}

		Linkedlist run = new Linkedlist();
		String temp= histogram(args[0]);
		run.insertFile(args[1], temp, args[2]);
		run.tree("tree_output.txt");
		run.hoffmanCode(run.root, "");
		run.entropyTable("entrophy_table.txt");


	}
}
