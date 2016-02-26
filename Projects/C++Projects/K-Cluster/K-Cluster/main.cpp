//
//  main.cpp
//  K-Cluster
//
//  Created by Humza Galafano on 5/20/15.
//  Copyright (c) 2015 Humza Galafano. All rights reserved.
//

#include <iostream>
#include <fstream>
using namespace std;





int computeMin(int Kx, int Ky, int x, int y){//step 2
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

int main(int argc, const char * argv[]) {
    try{
        ifstream input;//step 0
        ofstream output;
        input.open(argv[1]);
        output.open("output.txt");
        int row = 0, col = 0, i=0, j=0, totalx=0, totaly=0, numPoints=0;
        int K= std::stoi(argv[2]);
        int Ktotals[K+1][2];
        
        bool change=true;
        
        cout << argv[1] << "  " << argv[2] << "\r\n";
        
        if(K  < 1){
            return -1;
        }
        input >> row;
        input >> col;
        int coordinates[row][col];//step 0
        
        for(int i = 0; i < K+1; i++){
            for(int j = 0; j < 2; j++){
                coordinates[i][j] = 0;
            }
        }
        
        for(int i = 0; i < row; i++){//step 0
            for(int j = 0; j < col; j++){
                coordinates[i][j] = 0;
            }
        }
        
        while(!input.eof()){//step 0
            for(int k = 1; k < K+1; k++){
                input >>i;
                input >>j;
                coordinates[i][j] = k;
                numPoints++;
                if(input.eof()){
                    break;
                }
            }
        }
        
        while(change){//step 4
            change = false;
            
            
            for(int k = 1; k < K+1; k++){//step 2
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++){
                        
                        
                        if(coordinates[j][k] == k){
                            totalx = totalx + i;
                            totaly = totaly + j;
                        }
                        cout << coordinates[j][k];
                    }
                    cout << "\r\n";
                }
                Ktotals[k][0] = totalx/numPoints;
                Ktotals[k][1]= totaly/numPoints;
            }
            int x = 0, y = 0,mini =0, oldmini =0;
            
            for(int i = 0; i < row; i++){//step 3
                for(int j = 0; j < col; j++){
                    for(int k = 1; k < K; k++){
                        int x = 0, y = 0,mini =0;
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
        
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                output << coordinates[i][j];
            }
            output << "\r\n";
        }
        
        
        
        
        
        input.close();
        output.close();
    }
    catch(exception e){
        cout <<"Error.";
    }
    
    
    return 0;
}

