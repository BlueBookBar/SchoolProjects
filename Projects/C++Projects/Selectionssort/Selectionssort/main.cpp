#include <fstream>
#include <iostream>
using namespace std;

int selectionSort(int a[], int last);

int main(int argc, const char * argv[]) {
    int count= 0, b= 0, c= 0; string a;// Step 0
    cout << "What is the name of the file to be read?" << endl;
    cin >> a;
     ifstream sample;
     sample.open(a);
    if(sample.fail()){
        cout << "File not Found"<< endl;
        return 9;
    }
    while(!sample.eof()){//Step 1
        sample >> c;
        count++;
    }
    sample.close();
    int ara[count];
    
    ifstream input;
    input.open(a);
    if(input.fail()){
        cout << "File not Found"<< endl;
        return 9;
    }
    
    while(!input.eof()){// Step 2
        input >> c;
        ara[b] = c;
        b++;
    }
    input.close();
    for(b = 0; b<= count; b++){
        cout<< ara[b] << " ";
    }
    selectionSort(ara, count);
    return 0;
}

int selectionSort(int a[], int last){// Step 3
    int pos=0, walker=0, smallest = pos, temp;
    while(pos < last){//Step 4: repeat 3
        while(walker < last){ //Step 3
            walker++;
            if(a[walker] < a[smallest]){
                smallest = walker;
            }
        }
        temp = a[smallest];
        a[smallest] = a[pos];
        a[pos] = temp;
        cout << " " << endl;
        cout <<"Result of iteration 1: " << " Position index " << pos << " Min index" << smallest <<  " "  <<endl;// Step 5
        for(int c = 0; c <= pos; c++){
            cout << c << " ";
        }
        cout << " " << endl;
        for(int d = 0; d <= pos; d++){
            cout << a[d] << " ";
        }
        pos++;
        walker = pos;
        smallest = pos;//Step 3
    }

    
    
        return 0;
}