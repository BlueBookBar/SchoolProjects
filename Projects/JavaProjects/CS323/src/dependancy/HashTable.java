package dependancy;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;


public class HashTable {
	int size=0;
	Node[] array;

	public HashTable() {
	}
	public HashTable(int a){
		array = new Node[a+1];
		for(int i =0; i < a+1; i++){
			array[i] = new Node(-9);
		}
		
	}
	
	
	
    boolean isEmpty(){
        for(int i = 0; i < size+1 ; i++){
            if(array[i].next == null){
                return false;
            }
        }
        return true;
    }
    
    void insert(String s){
        try{
        
        Scanner graph = new Scanner(new FileReader (s));
        
        int temp=0, number=0;
        number= graph.nextInt();
            size = number;
        array= new Node[number+1];
        for(int i = 0; i < number+1 ;i++){
            array[i]= new Node(-9);
        }
        Node current = new Node();
        
        while(graph.hasNext()){
        	temp = graph.nextInt();//The limiting number
            Node newNode = new Node(temp);
            temp = graph.nextInt();//the dependant number
            current= array[temp];
            while(current.next != null){
                current=current.next;
            }
            if(newNode.jobID != temp){
                current.next = newNode;
            }
        }
        graph.close();
        
            
            
            //PRINT METHOD FOR INSERT
        
        for(int i = 0; i < number+1; i++){
            current= array[i];
            System.out.println("index "+ i );
            while(current.next !=null){
                current=current.next;
                
                System.out.println(current.jobID + " ");

            }
            System.out.println("\r\n");
        }
        
    }
        catch(Exception e){}
    }

    
    
}
