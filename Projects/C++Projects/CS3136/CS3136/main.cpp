#include <fstream>
#include <iostream>
using namespace std;

string faultyfile;

bool check(string name){
    ifstream File; int num1, num2;
    File.open(name);//Step 4
    if(File.fail()){
        return false;
    }
    File >> num1;//Step 5
    while(!File.eof()){//Step 7
        File >> num2;//Step 5
        if(num1 > num2){//Step 6
            faultyfile = name;
            File.close();
            return false;
        }
        num1 = num2;
    }
    File.close();
    return true;
}

void mergeSort(string first, string second, string output){
    int num1,num2;
    ifstream firstFile, secondFile;
    ofstream outFile;
    firstFile.open(first);// Step 8
    secondFile.open(second);// Step 8
    outFile.open(output);// Step 8
    if(firstFile.fail() || secondFile.fail() || outFile.fail()){
        cout <<"File not opened."<< endl;
    }
    firstFile >> num1;//Step 9
    secondFile >> num2;//Step 9
    while(!firstFile.eof() && !secondFile.eof()){//Step 11
        if(num1 < num2){//Step 10

            outFile << (num1);
            outFile << (" ");
            firstFile >> num1;
        }
        else if(num2 < num1){//STep 10
            outFile << (num2);
            outFile << (" ");
            secondFile >> num2;
        }
        else{//Step 10
            outFile << (num1);
            outFile << (" ");
            firstFile >> num1;
            secondFile >> num2;
        }
    }
    while(!firstFile.eof()){//Step 12
        firstFile >> num1;
        outFile << (num1);
        outFile << (" ");
    }
    while(!secondFile.eof()){//Step 12
        secondFile >> num2;
        outFile << (num2);
        outFile << (" ");
    }
    firstFile.close();
    secondFile.close();
    outFile.close();
    cout << "Merge was successful." << endl;
}

int main() {//Step 0
    string first, second, output, done = "yes";
    while(done == "Yes" || done == "yes"){//Step 3
        
         cout << "What is the name of the first file to be merged?" <<endl;
         cin>> first; cout << endl;
         cout << "What is the name of the second file to be merged?" << endl;
         cin>> second; cout << endl;
         cout << "What is the name of the merged file?" << endl;
         cin>> output; cout << endl;
        
        if(check(first) && check(second)){//Step 1
            mergeSort(first, second, output);//Step 2
        }
        else{
              cout << faultyfile <<" is not sorted, therefore it will not be merged." << endl;
        }
        
        cout << "Are there any other files to be inserted?(Enter 'yes' or 'no')" << endl;
        cin >> done;
    }
    return 0;
}
