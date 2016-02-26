import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HGalafano_Project6Main {

	BinarySearchTree first = new BinarySearchTree(99999);

	public static void main(String[] args) throws FileNotFoundException{//STEP 0: SET UP EVERYTHING
		BinarySearchTree first = new BinarySearchTree(99999);
		String data;
		String file = JOptionPane.showInputDialog("What is the name of the file?");
		BSTNode Spot = null, node = null; int dataA;
		Scanner input = new Scanner(new FileReader(file));
		while(input.hasNext()){// STEP 2: REPEAT UNTIL FILE IS EMPTY			
			data= input.next();
			if(data.compareTo("+") == 0){//STEP 1.3: IF COMMAND IS +
				data = input.next();
				dataA= Integer.valueOf(data);
				Spot = first.findSpot(first.root, dataA);
				if(Spot == null){
					System.out.println("The data " + dataA + " exists in the tree!");
				}
				else{
					node = new BSTNode(dataA);
					System.out.println("Inserting "+ dataA +"...");
					first.leafInsert(Spot,node);
					System.out.println(dataA + " inserted.");
					System.out.println();
					System.out.println();
				}
			}
			else if(data.compareTo("*") == 0){ //STEP 1.2: SEARCH FOR DATA
				data = input.next();
				dataA= Integer.valueOf(data);
				first.Search(dataA);
			}
			else if(data.compareTo("p") == 0){// STEP1.1: PRINT DATA
				System.out.println("Printing full tree...");
				first.print(first.getRoot());
				System.out.println();
				System.out.println();
			}
		}
		input.close();//STEP 3: CLOSE FILE
	}
}

