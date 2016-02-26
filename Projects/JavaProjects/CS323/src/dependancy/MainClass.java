package dependancy;
import javax.swing.*;

import java.io.*;
import java.util.Scanner;

public class MainClass {
	static HashTable hTable;
	static LinkedList Open = new LinkedList();
	static int numberNodes;
	static int procNeed;
	static int ProcUsed = -1;
	static int Time =0;
	static int availProc =0;
    static int processJob[];
    static int processTime[];
    static int parentCount[];
    static int jobTime[];
    static int jobDone[];
    static int jobMarked[];
    static int totalJobTime;
    static int scheduleTable[][];
	
	public static void main(String[] args) {
		try{
			
	        Scanner graph = new Scanner(new FileReader (args[0]));// Step 0
	        numberNodes =0; 
	        procNeed= Integer.parseInt(args[2]);
	        numberNodes= graph.nextInt();
	        graph.close();
	         ProcUsed = -1; 
	         Time =0;
	         availProc =0;
	        if(ProcUsed > numberNodes){
	            procNeed = numberNodes;
	        }
	         processJob = new int[numberNodes];
	         processTime = new int[numberNodes];
	         parentCount = new int[numberNodes];
	         jobTime = new int[numberNodes];
	         jobDone = new int[numberNodes];
	         jobMarked = new int[numberNodes];
	         for(int i =0; i < numberNodes; i++){
	             processJob[i]=0;
	             processTime[i]=0;
	             parentCount[i]=0;
	             jobTime[i]=0;
	             jobDone[i]=0;
	             jobMarked[i]=0;
	         }
	         
	          totalJobTime= totalTime(args[1]);
	         scheduleTable= new int[numberNodes][totalJobTime];
	         
	         hTable.insert(args[1]);
	         
	         Node current = new Node();
	         for(int i = 1; i < numberNodes+1 ; i++){
	             current = hTable.array[i];
	             while(current.next != null){
	                 current = current.next;
	                 parentCount[i-1]++;
	             }
	         }
	         
	         orphen();//Step 1
	         
	         Node newJob = new Node();
	         Node current2 = new Node();
	         
	         while(Open.dummy.next !=null || ProcUsed >= procNeed){//Step3+2
	         
	         if(availP() == -1){
	             ProcUsed++;
	             availProc = procNeed;
	         }
	             
	             if(ProcUsed < procNeed){
	                 current = Open.dummy;
	             while(current.next.next != null){
	                 current = current.next;
	             }
	                 current2 = current;
	                 current= current.next;
	                 processJob[ProcUsed]=current.jobID;
	                 processTime[ProcUsed]= jobTime[(current.jobID)-1];
	                 
	                 for(int i = 0; i < processTime[ProcUsed]; i++){
	                      scheduleTable[ProcUsed][i] = current.jobID;
	                 }
	                
	                 
	                 current2.next =null;
	             }
	             
	             if(Open.dummy.next == null &&  hTable.isEmpty()){
	            	 System.out.println("Error");
	                 return;
	             }
	             
	             for(int i =0; i < 15; i++){
	          //                   cout << "Index " << i << "   " << jobTime[i] << " Job Time \r\n";
	           //      cout << "Index " << i << "   " << parentCount[i] << " ParentNode \r\n";
	            //     cout << "Index " << i << "   " << jobMarked[i] << " job marked \r\n" ;
	             }
	             current =Open.dummy;
	              
	             while(current.next !=null){
	                 current = current.next;
	                 System.out.println(current.jobID + "  Is the linkedlist  \r\n");
	             }

	              
	             
	        //       break;
	             
	         }
	         
		}
		catch(Exception e){
			
		}
        
	}
	
	static int availP(){
	    if(ProcUsed < 0){
	        return -1;
	    }
	    for(int i = 0; i < ProcUsed ; i++){
	        if(processJob[i] ==0){
	            return i;
	        }
	    }
	    return -1;
	}
	
	
	static int totalTime(String s) throws FileNotFoundException{
	    Scanner time = new Scanner(new FileReader(s));
	    int temp=0, timeT =0, timeEnd = 0;
	    temp = time.nextInt();
	    while(time.hasNext()){
		    temp = time.nextInt();
	    timeT = time.nextInt();
	        jobTime[temp-1] = timeT;
	        timeEnd =timeEnd + timeT;
	    }
	    time.close();
	    return timeEnd;
	}
	
	static void orphen(){
	    
	    Node current = new Node();
	    Node newNode = new Node();
	    current = Open.dummy;
	    while(current.next != null){
	        current = current.next;
	    }
	    
	    for(int i = 0; i < numberNodes; i++){
	        if(jobMarked[i] == 0 && parentCount[i]== 0){
	            jobMarked[i]++;
	            newNode = new Node(i+1);
	            current.next = newNode;
	            current = current.next;
	        }
	    }
	}
	
}
