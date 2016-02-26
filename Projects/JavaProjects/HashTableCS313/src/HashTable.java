import java.util.Scanner;//Step 0
import java.io.FileNotFoundException;
import java.io.FileReader;

public class HashTable {//Step 0
	int func;
	int bucketSize;
	Node[] array;
	String File;

	public HashTable(){	// Step 0	
	}

	public HashTable(int arySize){//Step 0
		bucketSize = arySize; 
		array = new Node[bucketSize];//Step 4
		for(int i = 0; i < bucketSize ;i++){
			array[i]= new Node(-99999, null);
		}
	}

	public HashTable(int arySize, String file){// Step 0
		bucketSize = arySize; 
		File = file;
		array = new Node[bucketSize];// Step 4
		for(int i = 0; i < bucketSize ;i++){
			array[i]= new Node(-99999, null);
		}
	}

	public HashTable(int arySize, String file, int whichHash){ //Step 0
		bucketSize = arySize; 
		File = file;
		func= whichHash;
		array = new Node[bucketSize];//Step 4
		for(int i = 0; i < bucketSize ;i++){
			array[i]= new Node(-99999, null);
		}
	}

	void setFunc(int whichHash){//Step 0
		func = whichHash;
	}

	void setBucketSize(int Bsize){//Step 0
		bucketSize = Bsize;
		array = new Node[bucketSize];
	}

	void setFile(String file){// Step 0
		File = file;
	}

	void insertFromFile(String file, int whichHash) throws FileNotFoundException{// Step 5
		File = file;
		func = whichHash;
		if(func == 1){
			hashOne();
		}
		else if(func == 2){
			hashTwo();
		}
		else{
			hashThree();
		}
	}

	void printHashTable(){// Step 3
		System.out.println("Printing Hash Table...");
		for(int i = 0; i < bucketSize; i++){
			Node current = array[i];
			System.out.print("Bucket "+ (i+1) +" = ");
			while(current.getNext() != null){
				current = current.getNext();
				System.out.print(current.getData() + " ");
			}
			System.out.println();
		}
	}

	void printList(int index){
		System.out.println("Printing List...");
		Node current = array[index];
		while(current.getNext() != null){
			current = current.getNext();
			System.out.print(current.getData() + " -> " );
		}
		System.out.println("null");
		System.out.println();
	}

	Node findSpot(Node current, int data){
		Node previous= current;
		while(current.getNext() != null){
			previous = current;
			current= current.getNext();
			if(current.getData() > data  && previous.getData() < data){
				return previous;
			}
			else if(current.getData() == data ){
				return null;
			}
		}
		if(current.getData() < data){
			return current;
		}
		return previous;
	}

	void hashOne() throws FileNotFoundException{
		int data, index; Node spot = null;
		Scanner input = new Scanner(new FileReader(File));
		while(input.hasNext()){// Step 11
			data = input.nextInt();//Step 6
			index = hashOneFunction(data);//Step 7
			spot = findSpot(array[index],data);//Step 9
			if(spot == null){// Step 10
				System.out.println(data +" is already in the hash table.");
				System.out.println();
			}
			else if(spot.getNext() == null){//Step 10
				spot.setNext(new Node(data));
			}
			else{// Step 10
				Node next= spot.getNext();
				spot.setNext(new Node(data, next));
				printList(index);
			}
		}
		input.close();
	}

	void hashTwo()throws FileNotFoundException{
		int data, index; Node spot = null;
		Scanner input = new Scanner(new FileReader(File));
		while(input.hasNext()){//Step 11
			data = input.nextInt();//Step 6
			index = hashTwoFunction(data);//Step 7
			spot = findSpot(array[index],data);//Step 9
			if(spot == null){//Step 10
				System.out.println(data +" is already in the hash table.");
			}
			else if(spot.getNext() == null){// Step 10
				spot.setNext(new Node(data, null));
			}
			else{//Step 10
				Node next= spot.getNext();
				spot.setNext(new Node(data, next));
				printList(index);
			}
		}
		input.close();
	}

	void hashThree()throws FileNotFoundException{
		int data, index; Node spot = null; String datastr;			
		Scanner input = new Scanner(new FileReader(File));
		while(input.hasNext()){//Step 11
			data = input.nextInt();//Step 6
			datastr = Integer.toString(data);//Step 6
			data = Integer.parseInt(datastr);//Step 6
			index = hashThreeFunction(datastr);//Step 7
			spot = findSpot(array[index],data);//Step 9
			if(spot == null){//Step 10
				System.out.println(data +" is already in the hash table.");
			}
			else if(spot.getNext() == null){//Step 10
				spot.setNext(new Node(data));
			}
			else{//Step 10
				Node next= spot.getNext();
				spot.setNext(new Node(data, next));
				printList(index);
			}
		}
	}

	int hashOneFunction(int data){
		int index = data %bucketSize;
		return index;
	}

	int hashTwoFunction(int data){
		int moded= data, index= 0;
		while(moded > 0){
			index = index + (moded%10);
			moded= moded/10;
		}
		index = index%bucketSize;
		return index;
	}

	int hashThreeFunction(String data){
		int val= 1, index;
		for(int i = 0; i < data.length() ; i++){
			val = val*32 + data.charAt(i);
		}
		index = val%bucketSize;
		return index;
	}

}
