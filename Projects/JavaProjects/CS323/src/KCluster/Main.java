package KCluster;

import java.io.*;
import java.util.Scanner;

public class Main {

	static int computeMin(int Kx, int Ky, int x, int y){//step 2
		int Newx = 0, Newy = 0, z=0;
		Newx =(Kx-x);
		Newy = (Ky-y);
		if(Newy < 0){
			Newy= Newy*(-1);
		}
		else if(Newx < 0){
			Newx= Newx*(-1);
		}
		z= Newx+Newy;
		return z;
	}


	public static void main(String[] args) {
		try {
			Scanner input = new Scanner(new FileReader(args[0]));
			Writer output = new FileWriter("output.txt");
	        int row = 0, col = 0, totalx=0, totaly=0, numPoints=0;
	        int K= Integer.parseInt(args[1]);
	        int[][] Ktotals= new int[K+1][2];
	        boolean change=true;

			System.out.print(args[0]+ "  " + args[1] + "\r\n");
	        if(K  < 1){
	            System.out.println("Error, K is less than 1.");
	        	return;
	        }
	        
	        row = input.nextInt();
	        col = input.nextInt();
	        int[][] coordinates = new int[row][col];//step 0
	        
	        for(int a = 0; a < K+1; a++){
	            for(int b = 0; b < 2; b++){
	                coordinates[a][b] = 0;
	            }
	        }
	        
	        for(int i = 0; i < row; i++){//step 0
	            for(int j = 0; j < col; j++){
	                coordinates[i][j] = 0;
	            	System.out.print(coordinates[i][j]);
	            }
            	System.out.print("\r\n");
	        }
	        int i = 0;
	        int j = 0;
	        while(input.hasNext()){//step 0
	            for(int k = 1; k < K+1; k++){

	        i= input.nextInt();
	        j= input.nextInt();
	                coordinates[i][j] = k;
	                numPoints++;
	                if(!input.hasNextInt()){
	                    break;
	                }
	            }
	        }
	        
	        


	        while(change){//step 4
	            change = false;


	            for(int k = 1; k < K+1; k++){//step 2
	                for( i = 0; i < row; i++){
	                    for( j = 0; j < col; j++){


	                        if(coordinates[j][k] == k){
	                            totalx = totalx + i;
	                            totaly = totaly + j;
	                        }
	    	            }
	                }
	                Ktotals[k][0] = totalx/numPoints;
	                Ktotals[k][1]= totaly/numPoints;
	            }
	            int x = 0, y = 0,mini =0, oldmini =0;

	            for( i = 0; i < row; i++){//step 3
	                for( j = 0; j < col; j++){
	                    for(int k = 1; k < K; k++){
	                        x = 0;
	                        y = 0;
	                        mini =0;
	                        if(coordinates[i][j] == k){
	                            oldmini = k;
	                            x=i;
	                            y=j;
	                            if(computeMin( Ktotals[k][0], Ktotals[k][1],x,y) < computeMin( Ktotals[k+1][0], Ktotals[k+1][1],x,y)){

	                                mini= k;
	                            }
	                            else if(computeMin( Ktotals[k][0], Ktotals[k][1],x,y) > computeMin( Ktotals[k+1][0], Ktotals[k+1][1],x,y)){
	                                mini= k+1;
	                            }
	                            if(oldmini != mini){
	                                coordinates[i][j] = mini;
	                                change = true;
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        
	        
	        for( i = 0; i < row; i++){
	            for( j = 0; j < col; j++){
	            	output.write(coordinates[i][j]+ "");
	            }
            	output.write("\r\n");
	        }
	        
	        

	        input.close();
	        output.close();
	        
	        
	        
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

	
