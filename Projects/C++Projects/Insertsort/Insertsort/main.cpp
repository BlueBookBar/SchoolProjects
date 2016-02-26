
#include <iostream>
#include <fstream>
using namespace std;



void print( int last, int data, int spot, int arry[]){//Step 0
    cout<< "last = " << last << " data = "<< data << " spot= " << spot <<endl;
    int a = 0;
    while (a <= last){
        cout <<"ary[" << a <<"] ";
        a++;
    }
    cout << endl;
    a=0;
    while(a <= last){
        cout <<arry[a] << "      ";
        a++;
    }
    cout << endl;
}


int main() {//Step 0
    string a; int x, count = 0, last = 0, data, spot = 0, index;//Step 0
    cout << "What is the name of the file?" << endl;
    cin >> a;
    ifstream filea;//Step 1
    filea.open(a);
    if(filea.fail()){
        cerr << "Error opening file." << endl;
        exit(5);
    }
    while(!filea.eof()){//Step 2
        filea >> x;
        count++;
    }
    
    filea.close();//Step 3
    
    ifstream fileb;//Step 5
    fileb.open(a);
    if(fileb.fail()){
        cerr << "Error opening file." << endl;
        exit(5);
    }
    
    int arry[count];//Step 4
    fileb >> arry[last];//Step 8
    while(!fileb.eof()){
        fileb >> data;//Step 7
        spot = 0;
        while(spot <= last){
            if(arry[spot] > data){//Step 10
                break;
            }
            spot++;
        }
        last++;
        index = last;//Step 11
        
        while(index > spot){
            arry[index]= arry[index-1];//Step 12
            index--;
        }
        
        arry[spot] = data;
        
        print(last,data,spot,arry);
    }
    fileb.close();//Step 3
    return 0;
}