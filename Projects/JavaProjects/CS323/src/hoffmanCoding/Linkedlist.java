package hoffmanCoding;

import java.io.FileReader; // Step 2.0
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Linkedlist {
	Node dummy = new Node(null, "dummy", 0);
	int fold = 0;
	int count = 0;
	Node root = new Node();
	
    int[] probArray;
    int[] bitArray;
    int[] entrophyArray;
    String[] codeArray;
    String[] charArray;

	Linkedlist(){
	}

	void insert(Node another){
		Node walker = new Node();
		walker = dummy;
		count++;
		while(walker.getNext() != null && walker.getNext().getProb() < another.getProb()){ // Step 2.6+7
			walker=walker.getNext();
		}
		another.setNext(walker.getNext());// Step 2.8
		walker.setNext(another);
	}
	
	
	
	
	void hoffmanCode(Node tempnd, String tempstr){// Step 4.1
        if(tempnd == null){// Step 4.2
            return;
        }
        else if(tempnd.getLeft() == null && tempnd.getRight() == null){// Step 4.3
        	tempnd.setCode(tempstr);
        	
        }
        else{//Step 4.4
        	hoffmanCode(tempnd.getLeft(), tempstr +"0");
        	hoffmanCode(tempnd.getRight(), tempstr +"1");
        }
	}
	
	
	void entropyTable(String fil){
	Node current = new Node();
	current = dummy;
	int i = 0;
	while(current.getNext() != null){
		if(current.getString().length() > 1){
			current = current.getNext();
		}
		else{
			
		    probArray[i] = current.getProb();
		    bitArray[i] = current.getCode().length();
		    codeArray[i] =current.getCode();
		    charArray[i] = current.getString();
		    entrophyArray[i]= probArray[i]*bitArray[i];
		    current = current.getNext();
		    i++;
		}
	}
	printEntropyTable(fil);
	}

	
	
	
	void printEntropyTable(String fil){
		try{
		Writer outfile = new FileWriter(fil);
		outfile.write("Char     Prob       Code     #Bits     Entrophy" + "\r\n" +"===================================================" + "\r\n");
		
        for(int i = 0; i < charArray.length; i++){
        	if(charArray[i] == null){
        		break;
        	}
        	if(probArray[i] == 0){
        		i++;
        	}
        	else{	
            outfile.write(charArray[i] + "           " + probArray[i] + "        " +  codeArray[i]  +  "         " + bitArray[i]  + "       " + entrophyArray[i]  +"\r\n");
        } 
         }
		
		outfile.close();
		}
		catch(IOException e){
			System.out.println("File error.");
			return;
		}
	}
	
	
	
	
	void tree(String fle){
		try{
			if(dummy.getNext() == null){
				System.out.println("No list to build the tree from.");
				return;
			}
			Writer outfile = new FileWriter(fle, true);
			outfile.write("\r\n"+ "\r\n" + "Second linkedlist with tree built: " + "\r\n" + "\r\n");
			Node oldListHead = new Node(null, "dummy", 0);//Step 3.1
			oldListHead.setNext(dummy.getNext());
			while(dummy.getNext().getNext() != null){//Step 3.8
				Node temp = new Node();// Step 3.2
				temp.setString(dummy.getNext().getString() + dummy.getNext().getNext().getString());
				temp.setProb(dummy.getNext().getProb() + dummy.getNext().getNext().getProb());
				temp.setLeft(dummy.getNext());
				temp.setRight(dummy.getNext().getNext());				
				dummy.setNext(dummy.getNext().getNext().getNext());
				
				this.insert(temp);// Step 3.3-3.5
				
				Node current = new Node();//Step 3.7
				current = dummy;
				outfile.write("Iteration "+ fold + ": listHead -->");
				fold++;
				while(current.getNext() != null){
					outfile.write("(" + current.getString() + ", "+ current.getProb() + ", " + current.getNext().getString() + ")-->");
					current = current.getNext();
					
				}
				outfile.write("(" + current.getString() + ", "+ current.getProb() + ", " + "NULL)-->NULL"+ "\r\n"+ "\r\n"+ "\r\n");
				
			}
			root= dummy.getNext();
			dummy= oldListHead;

			outfile.close();	
		}
		catch(IOException e){
			System.out.println("File error.");
			return;
		}
	}


	void insertFile(String outfle, String infle, String outflea){// Step 2.0
		try{
			String tempstr= "";
			int tempint = 0;
			Node tempnd= new Node();
			Node current = new Node();


			Scanner infile = new Scanner(new FileReader(infle));
			Writer outfile =  new FileWriter(outfle);
			Writer outfilea =  new FileWriter(outflea);
			outfile.write("Iteration" + count + ": listHead -->(" + current.getString() + ", " + current.getProb() + ", " +  "NULL )-->NULL" + "\n" + "\n" + "\n");
			count++;

			while(infile.hasNext()){// Step 2.1
				current = dummy;// Step 2.5
				outfile.write("Iteration" + count + ": listHead -->");
				tempstr = infile.next();// Step 2.2
				tempint = infile.nextInt();
				tempnd = new Node(null, tempstr, tempint);// Step 2.3.4
				insert(tempnd);

				while(current.getNext() != null){// Step 2.10
					outfile.write("(" + current.getString() + ", " + current.getProb() + ", " + current.getNext().getString() + ")-->");
					current=current.getNext();
				}

				outfile.write("(" + current.getString() + ", " + current.getProb() + ", " + "NULL" + ")-->NULL"+ "\n" + "\n" +"\n" );
			}

			current = dummy;
			while(current.getNext() != null){
				outfilea.write(current.getNext().getString() + "   " + current.getNext().getProb() + "\n" + "\n" );
				current=current.getNext();
			}
		    probArray = new int[count+1];
		    bitArray = new int[count+1];
		    entrophyArray = new int[count+1];
		    codeArray = new String[count+1];
		    charArray = new String[count+1];

			outfile.close();
			outfilea.close();
			infile.close();
		}
		catch(IOException a){
			System.out.println("File Error.");
			return;
		}
	}

}
