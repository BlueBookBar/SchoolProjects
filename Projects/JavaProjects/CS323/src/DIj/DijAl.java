package DIj;

import javax.swing.*;

import java.io.*;
import java.util.Scanner;

public class DijAl {





	public static void main(String[] args) {
		System.out.println("Please pass 3 arguments, [2]-output file, [0][1]- input files");
		try{
			Scanner infile = new Scanner(new FileReader(args[0]));
			Scanner sourcenodefile = new Scanner(new FileReader(args[1]));
			int numonodes=0,minnode=0, column,row,cost;
			numonodes = infile.nextInt();
			minnode = sourcenodefile.nextInt();
			int sourcenode =minnode;
			int[][] costMatrix = new int[numonodes+1][numonodes+1];
			int[] marked = new int[numonodes];
			int[] bestCost= new int[numonodes];
			int[] parent= new int[numonodes];


			while(infile.hasNext()){
				row = infile.nextInt();
				column = infile.nextInt();
				cost = infile.nextInt();
				costMatrix[row][column] = cost;
			}

			parent[minnode] = minnode;
			for(int r= 0; r < numonodes;r++){
				bestCost[r]= costMatrix[minnode][r];
			}

			boolean filled = false;

			int tempcost=0;
			int newNode =-67;



			while(filled){


				marked[minnode] = 1;


				for(int t = 0; t < numonodes; t++){
					if(costMatrix[minnode][t] != 0 && 1 != marked[t]){
						newNode = t;
						tempcost = bestCost[minnode]+ costMatrix[minnode][newNode];
						if(tempcost < bestCost[newNode]){
							bestCost[newNode] = tempcost;
							parent[newNode] = minnode;
						}
					}
				}
				int temp=bestCost[minnode] + bestCost[minnode];
				for(int k =0; k < numonodes; k++){
					if(bestCost[k] >= 1){
						if(temp < bestCost[k]){
							temp = bestCost[k];
							minnode = k;
						}
					}
				}


				int counterhere = 0;
				for(int y = 0; y < numonodes;y++){
					if(marked[y] == 1){
						counterhere++;
					}
					if(counterhere == numonodes){
						filled = true;
					}
				}

			}

			Writer outfile = new FileWriter(args[2]);

			outfile.write("Cost Matrix:"+ "\r\n");
			for(int i = 0; i < numonodes; i++){//initalized the arrays
				for(int j = 0; j < numonodes; j++){

					outfile.write(costMatrix[i][j] + " "); 
				}
			outfile.write("\r\n");
			}
			outfile.write("The source node is: "+sourcenode+ "."+ "\r\n");


			int prev= -34;
			for(int e =0; e < numonodes; e++){
				outfile.write("Source to "+ e+ " ("+ bestCost[e]+ ") and path:"+ e );
				prev=parent[e];
				while(prev != parent[prev]){
					outfile.write(" <-- "+ prev); 
					prev=parent[prev];
				}

				outfile.write(" <-- "+ sourcenode + "\r\n"); 
			}


			outfile.close();
			infile.close();
			sourcenodefile.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
