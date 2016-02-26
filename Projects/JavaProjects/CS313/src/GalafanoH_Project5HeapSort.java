import java.io.*;	//PREPARE EVERYTHING
import java.util.*;
import javax.swing.JOptionPane;

public class GalafanoH_Project5HeapSort{// PREPARE EVERYTHING

	
	//MAIN WILL RUN THE BUILD AND DELETE
	public static void main(String[] args) throws FileNotFoundException{	
		String a = JOptionPane.showInputDialog("What is the name of the input file?");
		int b[] = HeapSortBuild(a);			
		HeapSortDelete(b);
	}

	
	//Heapsort Build Algorithm
	public static int[] HeapSortBuild(String a) throws FileNotFoundException{
		int  count = 1, last = 0, data; 	//Step 1
		Scanner input1 = new Scanner(new FileReader(a)); // Heap_Sort_Data.txt
		while(input1.hasNext()){			// Step 0
			input1.nextInt();		// Appropriate count of file
			count++;
		}
		input1.close();
		int[] array = new int[count];
		array[0] = last;			//extra slot carries the count of the last  
		Scanner input = new Scanner(new FileReader(a));
		while(input.hasNext()){				//Step 6
			data = input.nextInt();			// Step 2
			last++;					// Step 3
			array[last] = data;
			array[0] = last;		//Update last
			BubbleUp(array);		// Step 5
		}
		input.close();				// Step 7
		return array;

	}

	//HeapSort delete algorithm
	public static void HeapSortDelete(int array[]){
		int first = 1, last = array[0];//Step 8 
		while(first < last){//Step 11
			System.out.println(array[first] + " ");// Step 8
			array[first] = array[last];//Step 9
			last--;// Step 9
			array[0] = last;// UPDATE LAST
			BubbleDown(array);
		}
	}

	
	//The bubbleDown Algorithm
	public static void BubbleDown(int[] array){				//Step 10
		int father = 1; int smallkid = 0, temp =0; int last= array[0];	//Step 1 			
		int leftkid = father*2, rightkid = (father*2) +1;				//Step 2
		while(array[father] > array[smallkid] || !(leftkid > last && rightkid > last)){	//Step 5
			 leftkid = father*2;						//Step 2
			 rightkid = (father*2) +1;
			//case 1									//Step 3
			if(rightkid <= last){//if there is a right kid
				if(array[leftkid] < array[rightkid]){// if the left is smaller
					smallkid = leftkid;
				}
				else{// if the right is smaller
					smallkid = rightkid;
				}
			}
			//case2 if there is only a left kid
			else if(leftkid <= last && rightkid > last){//looks at only the left kid
				smallkid = leftkid;
			}											//Step 3 end
														//Step 4 begins
			if(smallkid <= last && array[smallkid] < array[father]){// next iterations of the loop,dont mess up
				//swap the father and smallkid			
				temp = array[smallkid];
				array[smallkid] = array[father];
				array[father] = temp;
			}
			father = smallkid; // go down the tree
		}
	}

	
	//The bubbleUp Algoritm
	public static void BubbleUp(int[] array){	//Step 5
		int child= array[0], temp, father; 		//Step 1 & 2
		while(child > 1){					//Step 4, repeat steps 2 and 3
			father= child/2;				//Step 2
			if(array[child] < array[father]){ 	//Step 3
				temp = array[child];		//SWAP
				array[child]= array[father] ;
				array[father]=temp;
			}
			child = father;
		}
	}
}
