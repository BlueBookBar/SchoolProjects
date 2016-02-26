import java.io.FileNotFoundException;//Step 0
import javax.swing.JOptionPane;

public class GalafanoH_Project8_HashTable {//Step 0
	
	public static void main(String[] args) throws FileNotFoundException{
		int Bsize= Integer.parseInt(JOptionPane.showInputDialog("How many buckets will the hash table have?"));//Step 1
		String infile = JOptionPane.showInputDialog("What is the name of the file to be read?");
		int whichHash = Integer.parseInt(JOptionPane.showInputDialog("Which hash function will be used, 1, 2, or 3?"));
		while(whichHash < 0 && whichHash > 4){// Step 2
			whichHash = Integer.parseInt(JOptionPane.showInputDialog("The previous entry was not correct. Which hash function will be used, '1', '2', or '3'?"));
		}
		HashTable a = new HashTable(Bsize, infile, whichHash);
		a.insertFromFile(infile, whichHash);
		a.printHashTable();//Step 3
	}
}
