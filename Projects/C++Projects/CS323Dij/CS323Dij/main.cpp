//
//  main.cpp
//  CS323Dij
//
//  Created by Humza Galafano on 4/19/15.
//  Copyright (c) 2015 Humza Galafano. All rights reserved.
//



#include <string>
#include <fstream>
#include <cstdlib>
#include <iostream>
using namespace std;







int main(int argc, const char * argv[]) {
    
    cout << "Please pass 3 arguments, [3]-output file, [1][2]- input files" << endl << endl;
    if(argc < 4){
        cout<< "Not enough arguments" << endl << endl;
        return 0;
    }
    
    try{
        fstream infile;
        fstream sourcenodefile;
        infile.open(argv[1]);
        sourcenodefile.open(argv[2]);
        int numonodes= 0;
        int minnode = 0;
        int column, row, cost;
        infile >> numonodes;
        sourcenodefile >> minnode;
        int sourceNode = minnode;
        int costMatrix[numonodes][numonodes];
        int marked[numonodes];
        int bestCost[numonodes];
        int parent [numonodes];
        
        
        
        
        for(int i = 0; i < numonodes; i++){//initalized the arrays
            for(int j = 0; j < numonodes; j++){
                costMatrix[i][j] = 0;
            }
            marked[i] = 0;
            bestCost[i]= 0;
            parent [i] = 0;
        }
        while(!infile.eof()){//fill costMatrix
            infile >> row;
            infile >> column;
            infile >> cost;
            costMatrix[row][column] = cost;
        }
        
        parent[minnode] = minnode;
        for(int r= 0; r < numonodes;r++){
            bestCost[r]= costMatrix[minnode][r];
        }
        
        
        
        bool filled = false;
        
        int tempcost=0;
        int newNode =-67;
        
        
        while(filled){
            
            
            marked[minnode] = 1;
            
            
            //go odwn the martix row of minnode and find a non zero number and that is the next node
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
 
        
        
        
        
        ofstream outfile;
        outfile.open(argv[3]);
        
        
        outfile << "Cost Matrix:"<< endl;
                for(int i = 0; i < numonodes; i++){//initalized the arrays
            for(int j = 0; j < numonodes; j++){
                
               outfile << costMatrix[i][j] << " ";
            }
                    outfile << endl;
        }
        outfile << "The source node is: " << sourceNode << "." <<endl;
        
        int prev= -34;
        for(int e =0; e < numonodes; e++){
        outfile << "Source to " <<e << " ("<< bestCost[e]<< ") and path:"  << e;
        //source to n2 (cost) and path: n2 <-- .. <-- .. <--.... <-- source
                           prev=parent[e];
            while(prev != parent[prev]){
                outfile <<" <-- " << prev;
                            prev=parent[prev];
            }
            
            outfile <<" <-- " <<  sourceNode << endl;
        }
        
        
        
        
        
        outfile.close();
        infile.close();
        sourcenodefile.close();
        

        
        
    }
    catch(ifstream::failure e){
        cout << endl << "IOException when reading/writing file." << endl;
        return -1;
    }
    
    
    
    
    return 0;
}
