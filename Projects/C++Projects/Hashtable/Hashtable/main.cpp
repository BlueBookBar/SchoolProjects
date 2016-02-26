#include <fstream>//Step 0
#include <iostream>
#include <string>
using namespace std;

class Node{//Step 0
    public:
    int data;
    Node* next;
    
    public:
    
    Node(){
        next = NULL;
    }
    
    Node(int a){
        data = a;
        next = NULL;
    }
    
    Node(int a, Node* n){
        data = a;
        next = n;
    }
    
    Node* getNext(){
        return next;
    }
    
    int getData(){
        return data;
    }
    
    void setData(int a){
        data = a;
    }
    
    void setNext(Node* n){
        next = n;
    }
};





class hashTable{//Step 0
    public:
    int which;
    int bucketSize;
    Node* array;
    string File;
    hashTable(){
    };
    
    hashTable(int Bsize){//Step 0
        bucketSize = Bsize;
        array = new Node[bucketSize];//Step 4
        for(int i = 0; i < bucketSize ;i++){
            array[i]= *new Node(-99999, NULL);
        }
    };
    
    hashTable(int Bsize, string infile){//Step 0
        bucketSize = Bsize;
        File = infile;
        array = new Node[bucketSize];//Step 4
        for(int i = 0; i < bucketSize ;i++){
            array[i]= *new Node(-99999, NULL);
        }
    };
    
    
    hashTable(int Bsize, string infile, int whichHash){//Step 0
        bucketSize = Bsize;
        File = infile;
        which = whichHash;
        array = new Node[bucketSize];//Step 4
        for(int i = 0; i < bucketSize ;i++){
            array[i]= *new Node(-99999, NULL);
        }
    };
    
    
    void setFunc(int whichHash){//Step 0
        which = whichHash;
    }
    
    void setBucketSize(int Bsize){//Step 0
        bucketSize= Bsize;
        array = new Node[bucketSize];//Step 4
    }
    
    void setFile(string file){// Step 0
        File = file;
    }
    
    
    
    
    
    
    
    void printHashTable(){//Step 3
        Node current;
        cout << "Printing Hash Table..." <<endl;
        for(int i =0; i < bucketSize; i++){
            Node current = array[i];
            cout <<"Bucket " <<i+1 << " = "<<endl;
            
            while(current.next != NULL){
                current = *current.next;
                cout <<current.data <<" ";
            }
            cout << endl;
        }
    }
    void printList(int index){
        cout <<"Printing list " << index+1 << "... ";
        Node current = array[index];
        
        while(current.next != NULL){
            current= *current.next;
            cout <<current.data << " -> ";
        }
        cout << "NULL"<< endl;
    }
    
    
    
    
    
    
    
    Node* findSpot(int index, int data){
        Node current = array[index], previous= current;
        while(current.next != NULL){
            previous = current;
            current= *current.next;
            if(current.data > data  && previous.data < data){
                return
                &previous;
            }
            else if(current.data == data ){
                return NULL;
            }
        }
        if(current.data < data){
            return &current;
        }
        return &previous;
    }
    
    
    
    int hashOneFunction(int data){
        int index = data %bucketSize;
        return index;
    }
    
    int hashTwoFunction(int data){
        int moded= data, index= 0;
        while(moded > 0){
            index = index + (moded%10);
            moded= moded/10;
        }
        index = index%bucketSize;
        return index;
    }
    
    int hashThreeFunction(string data){
        int val= 1, index;
        for(int i = 0; i < data.length() ; i++){
            val = val*32 + data[i];
        }
        index = val%bucketSize;
        return index;
    }
    
    
    
    
    
    
    
    void hashOne(){
        int data, index; Node* spot = new Node(0, NULL);
        ifstream input;
        input.open(File);
        if(!input.is_open()){
            cout << "File Not Found" << endl;
        }
        
        while(!input.eof()){// Step 11
            input >> data ;//Step 6
            index = hashOneFunction(data);//Step 7
            *spot = *(findSpot(index,data));//Step 9
            if(spot == NULL){// Step 10
                cout <<data <<" is already in the hash table." << endl << endl;
            }
            
            else if(spot->next == NULL){//Step 10
                spot->next = new Node(data);
            }
            else{// Step 10
                Node next= *spot->next;
                spot->next =new Node(data, &next);
                printList(index);
            }
            
        }
        input.close();
    }
    
    
    void hashTwo(){
        int data, index; Node* spot = new Node(0, NULL);
        ifstream input;
        input.open(File);
        if(!input.is_open()){
            cout << "File Not Found" << endl;
        }
        
        while(!input.eof()){// Step 11
            input >> data ;//Step 6
            index = hashTwoFunction(data);//Step 7
            *spot = *(findSpot(index,data));//Step 9
            if(spot == NULL){// Step 10
                cout <<data <<" is already in the hash table." << endl << endl;
            }
            
            else if(spot->next == NULL){//Step 10
                spot->next = new Node(data);
            }
            else{// Step 10
                Node next= *spot->next;
                spot->next =new Node(data, &next);
                printList(index);
            }
            
        }
        input.close();
    }
    
    
    void hashThree(){
 
        int data, index; Node* spot = new Node(0, NULL); string datastr;
        ifstream input;
        input.open(File);
        if(!input.is_open()){
            cout << "File Not Found" << endl;
        }

        while(!input.eof()){// Step 11
            input >> data ;//Step 6
            datastr= to_string(data);
            index = hashThreeFunction(datastr);//Step 7
            *spot = *(findSpot(index,data));//Step 9
            if(spot == NULL){// Step 10
                cout <<data <<" is already in the hash table." << endl << endl;
  
            }
            
            else if(spot->next == NULL){//Step 10
                spot->next =new Node(data, NULL);
            }
            else{// Step 10
                Node next= *spot->next;
                *spot->next =*new Node(data, &next);
                printList(index);
            }
        }
        input.close();
        
        
        
    }
    
    void insertFromFile(string file, int whichHash){
        File = file;
        which = whichHash;
        if(which == 1){
            hashOne();
        }
        else if(which == 2){
            hashTwo();
        }
        else{
            hashThree();
        }
    }
};

int main() {
    int Bsize, whichHash; string infile;
    cout <<"How many buckets will the hash table have?" << endl;
    cin >> Bsize;
    cout <<"What is the name of the file to be read?" << endl;
    cin >> infile;
    cout << "Which hash function will be used, 1, 2, or 3?" << endl;
    cin >> whichHash;
    
    while(whichHash < 0 && whichHash > 4){
        cout << "The previous entry was not correct. Which hash function will be used, 1, 2, or 3?" << endl;
        cin >> whichHash;
    }
    hashTable work(Bsize, infile, whichHash);
    work.insertFromFile(infile,whichHash);
    work.printHashTable();
    return 0;
}
