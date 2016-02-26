
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int* pJob;
int* parent;
int* jobm;
int* jobtime;
int* procU;



class Node{
public:
    Node* next= NULL;
    int jobID =0;
    int time =0;
    
    
    Node(){
    }
    Node(Node* b){
        next = b;
    }
    Node(int a){
        jobID=a;
    }
    ~Node(){
    };
    
};

class Linkedlist{
public:
    Node* dummy = new Node(-9);
    
    Linkedlist(){
    };
    ~Linkedlist(){
    };
};

class hashTable{
public:
    Node* array;
    int size=0;
    
    hashTable(){
    };
    hashTable(int a){//the array ignores 0, so an array of size 15 has indexes between 1-15
        array = new Node[a+1];
        for(int i = 0; i < a+1 ;i++){
            array[i]= *new Node(-9);
        }
    };
    ~hashTable(){
    };
    
    
    
    void insert(string s){
        try{
        
        ifstream graph;
        graph.open(s);
        
        int temp=0, number=0;
        graph >> number;
            size = number;
        array= new Node[number+1];
        for(int i = 0; i < number+1 ;i++){
            array[i]= *new Node(-9);
        }
        Node* current = new Node();
        
        while(!graph.eof()){
            graph >> temp;//The limiting number
            Node* newNode = new Node(temp);
            graph>> temp;//the dependant number
            current= &array[temp];
            while(current->next !=NULL){
                current=current->next;
            }
            if(newNode->jobID != temp){
                current->next = newNode;
            }
        }
        graph.close();
        
            
            
            //PRINT METHOD FOR INSERT
        /*
        for(int i = 0; i < number+1; i++){
            current= &array[i];
            cout << "index "<< i << "   ";
            while(current->next !=NULL){
                current=current->next;
                cout << current->jobID << " " ;
                

            }
            cout << "\r\n";
        }
        */
    }
        catch(exception e){}
    }
    
    
    
    
    bool isEmpty(){
        for(int i = 0; i < size+1 ; i++){
            if(array[i].next == NULL){
                return false;
            }
        }
        return true;
    }
    
    
    
    
};







int totalTime(string s){
    ifstream time;
    time.open(s);
    int temp=0, timeT =0, timeEnd = 0;
    time>> temp;
    while(!time.eof()){
        time >> temp;
        time >> timeT;
        jobtime[temp-1] = timeT;
        timeEnd =timeEnd + timeT;
    }
    time.close();
    return timeEnd;
}


void orphen(Linkedlist* a, int size){
    
    Node* current = new Node();
    Node* newNode = new Node();
    current = a->dummy;
    while(current->next != NULL){
        current = current->next;
    }
    
    for(int i = 0; i < size; i++){
        if(jobm[i] == 0 && parent[i]== 0){
            jobm[i]++;
            newNode = new Node(i+1);
            current->next = newNode;
            current = current->next;
        }
    }
}



int availP(int max){
    if(*procU < 0){
        return -1;
    }
    for(int i = 0; i < *procU ; i++){
        if(pJob[i] ==0){
            return i;
        }
    }
    return -1;
}




int main(int argc, const char * argv[]) {
    /*
     cout <<" " << argv[1] <<" \r\n";
     cout <<" " << argv[2] <<" \r\n";
     cout <<" " << argv[3] <<" \r\n";

     */

    
    try{                    //Step 0
        ifstream graph;
        graph.open(argv[1]);
        int numberNodes =0;
        int procNeed= std::stoi(argv[3]);
        graph >> numberNodes;
        graph.close();
        int ProcUsed = -1, Time =0, availProc =0;
        if(ProcUsed > numberNodes){
            procNeed = numberNodes;
        }
        int processJob[numberNodes];
        int processTime[numberNodes];
        int parentCount[numberNodes];
        int jobTime[numberNodes];
        int jobDone[numberNodes];
        int jobMarked[numberNodes];
        for(int i =0; i < numberNodes; i++){
            processJob[i]=0;
            processTime[i]=0;
            parentCount[i]=0;
            jobTime[i]=0;
            jobDone[i]=0;
            jobMarked[i]=0;
        }
        procU = &ProcUsed;
        pJob = processJob;
        jobtime = jobTime;
        parent = parentCount;
        jobm = jobMarked;
        int totalJobTime = totalTime(argv[2]);
        int scheduleTable[numberNodes][totalJobTime];
        
        hashTable hTable = *new hashTable(numberNodes);//index 0 is NULL, index 1-15 is occupied
        hTable.insert(argv[1]);
        
       
        Node* current = new Node();
        for(int i = 1; i < numberNodes+1 ; i++){
            current = &hTable.array[i];
            while(current->next != NULL){
                current = current->next;
                parentCount[i-1]++;
            }
        }
        
        Linkedlist Open = * new Linkedlist();       //Step 0
        
        
        
        

        orphen(&Open, numberNodes);//Step1
        
        Node* newJob = new Node();
        Node* current2 = new Node();
        
        while(Open.dummy->next !=NULL || ProcUsed >= procNeed){//Step3+2
        
        if(availP(procNeed)== -1){
            ProcUsed++;
            availProc = procNeed;
        }
            
            if(ProcUsed < procNeed){
                current = Open.dummy;
            while(current->next->next != NULL){
                current = current->next;
            }
                current2 = current;
                current= current->next;
                processJob[ProcUsed]=current->jobID;
                processTime[ProcUsed]= jobTime[(current->jobID)-1];
                
                for(int i = 0; i < processTime[ProcUsed]; i++){
                     scheduleTable[ProcUsed][i] = current->jobID;
                }
               
                
                current2->next =NULL;
            }
            
            if(Open.dummy->next == NULL &&  hTable.isEmpty()){
                EXIT_FAILURE;
            }
              break;
            
        }
      
        
        /*  PRINT METHOD FOR THE FOLLOWING ARRAYS
        for(int i =0; i < 15; i++){
     //                   cout << "Index " << i << "   " << jobTime[i] << " Job Time \r\n";
      //      cout << "Index " << i << "   " << parentCount[i] << " ParentNode \r\n";
       //     cout << "Index " << i << "   " << jobMarked[i] << " job marked \r\n" ;
        }
        current =Open.dummy;
         
        while(current->next !=NULL){
            current = current->next;
            cout << current->jobID << " Is the linkedlist  \r\n";
        }

         */
        
        
        
    }
    catch(exception e){
    }
    
    
    
    return 0;
}
