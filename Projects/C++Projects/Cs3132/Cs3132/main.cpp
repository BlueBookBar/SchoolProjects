
#include <iostream>
#include <fstream>
using namespace std;



void print( int last, int data, int spot, int arry[]){
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
    }
    cout << endl;
}

int main() {
    string a; int x, count = 0, last = 0, data, spot, index;
    cout << "What is the name of the file?" << endl;
    cin >> a;
    
    ifstream filea;
    filea.open(a);
    if(filea.fail()){
        cerr << "Error opening file." << endl;
        exit(5);
    }
    while(!filea.eof()){
        filea >> x;
        count++;
    }
    
    filea.close();
    
    ifstream fileb;
    fileb.open(a);
    if(fileb.fail()){
        cerr << "Error opening file." << endl;
        exit(5);
    }
    
    int arry[count];
    while(!fileb.eof()){
        fileb >> arry[last];
        fileb >> data;
        for(int spot = 0; spot <= last; spot++){
            if(arry[spot] < data){
                break;
            }
        }
        last++;
        index = last;
    
    while(index > spot){
        arry[index]= arry[index-1];
        index--;
    }
    
    arry[spot] = data;
    
        print(last,data,spot,arry);
    }
    
    return 0;
}
