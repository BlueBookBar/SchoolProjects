#include <fstream>      //Step: 2.0
#include <string>
#include <vector>
#include <cstdlib>
#include <iostream>
using namespace std;



class Node{
public:
    Node* next= NULL;
    Node* left = NULL;
    Node* right = NULL;
    string charString = "";
    double prob= 0.0;
    string code= "";
    Node(){
    }
    
    Node(Node* b){
        next = b;
    }
    Node(Node* b, string a){
        next = b;
        charString = a;
    }
    Node(Node* b, string a, double c){
        next = b;
        charString = a;
        prob = c;
    }
    Node(string a, double c){
        charString = a;
        prob = c;
    }
    ~Node(){
    };
};



class Linkedlist{
public:
    Node* dummy = new Node(NULL,"dummy", 0);
   
    Node* root = new Node();
    int fold = 0;
    int count = 0;
    string *charArr;
    string *codeArr;

    
    Linkedlist(){
    };
    
    ~Linkedlist(){
        Node* current = new Node();
        killTree(root->left);
        killTree(root->right);
        current = dummy;
        while(current->next !=NULL){
            dummy = current->next;
            free(current);
            current = dummy;
        }
        delete dummy;
        delete charArr;
        delete codeArr;
    };
    
    void killTree(Node* temp){
        if(temp == NULL){
            return;
        }
        killTree(temp->left);
        killTree(temp->right);
        delete temp;
        
    }
    
    
    void tree(string fil){
        try{
            if(dummy->next== NULL){
                cout << "No linkedlist to build the tree from.";
                return;
            }
            ofstream outfile;
            outfile.open(fil);
            outfile <<endl << endl << "Second linkedlist with tree built: " << endl << "\r\n";
            Node* oldListHead= new Node(NULL,"dummy", 0);// Step 3.1
            oldListHead->next = dummy->next;
            while(dummy->next->next != NULL){// Step 3.8
                Node* temp = new Node();// Step 3.2
                Node* walker= new Node();
                temp->charString = dummy->next->charString + dummy->next->next->charString;
                temp->prob = dummy->next->prob + dummy->next->next->prob;
                temp->left = dummy->next;
                temp->right = dummy->next->next;
                dummy->next= dummy->next->next->next;
                
                walker = dummy;// Step 3.3
                while(walker->next != NULL && walker->next->prob < temp->prob){// Step 3.5
                    walker=walker->next;// Step 3.5
                }
                temp->next= walker->next;// Step 3.6
                walker->next = temp;
                
                
                Node* current=new Node(NULL);// Step 3.7
                current = dummy;
                outfile << "Iteration" << fold << ": listHead -->";
                fold++;
                while(current->next != NULL){
                    outfile <<"(" << current->charString << ", " << current->prob << ", " << current->next->charString << ")-->";
                    current= current->next;
                }
                outfile <<"(" << current->charString << ", " << current->prob << ", " << "NULL" << ")-->";
                outfile << "NULL" << endl << endl << endl;
            }
            
            root = dummy->next;// Step 3.9
            dummy = oldListHead;
            
            
            
            outfile.close();
            
        }
        catch(ifstream::failure e){
            cout << endl << "IOException when reading/writing file." << endl;
        }
    }
    
    
    void insert(Node* another){
        Node* walker = new Node(NULL);
        walker = dummy;
        count++;
        while(walker->next != NULL && walker->next->prob < another->prob){//Step 2.6+7
            walker= walker->next;
        }
        another->next = walker->next;//Step 2.8
        walker->next = another;
    }
    
    
    void insertfile(string outfle,string infle, string outflea){//Step 2.0
        try{
            string tempstr = "";
            int tempint= 0;
            Node* tempnd;
            Node* current = new Node(NULL, "dummy", 0);
            
            ifstream infile;
            ofstream outfile;
            ofstream outfilea;
            outfilea.open(outflea);
            outfile.open(outfle);
            infile.open(infle);
            
            outfile << "Building the original linkedlist:" << "\r\n" << "\r\n";
            
            outfile << "Iteration" << count << ": listHead -->";
            outfile <<"(" << current->charString << ", " << current->prob << ", " <<  "NULL )-->";
            outfile << "NULL" << endl << endl << endl;
            
            
            
            while(!infile.eof()){//Step 2.1
                
                current = dummy;//Step 2.5
                infile >> tempstr;//Step 2.2
                infile >> tempint;
                if(infile.eof()){
                    break;
                }
                outfile << "Iteration" << count << ": listHead -->";
                tempnd= new Node(NULL, tempstr, tempint);//Step 2.3+4
                insert(tempnd);
                
                while(current->next != NULL){//Step 2.10
                    
                    outfile <<"(" << current->charString << ", " << current->prob << ", " << current->next->charString << ")-->";
                    current= current->next;
                    
                }
                
                outfile <<"(" << current->charString << ", " << current->prob << ", " << "NULL" << ")-->" << "NULL" << endl << endl << endl;
                
            }
            
            
            current = dummy->next;
            outfile << "Iteration" << count << ": listHead -->";
            while(current->next != NULL){//Step 2.10
                outfile <<"(" << current->charString << ", " << current->prob << ", " << current->next->charString << ")-->";
                current= current->next;
            }
            outfile <<"(" << current->charString << ", " << current->prob << ", " << "NULL" << ")-->" << "NULL" << endl << endl << endl;
            
            
            
            current = dummy;
            while(current->next !=NULL){
                outfilea << current->next->charString << "   " << current->next->prob<< "\r\n" << "\r\n";
                current= current->next;
            }
            
            infile.close();
            outfile.close();
            outfilea.close();
        }
        catch(ifstream::failure e){
            cout << endl << "IOException when reading/writing file." << endl;
            
        }
    }
    
    
    void print(){//Step 2.0
        Node* current=new Node(NULL);
        current = dummy;
        cout << "Iteration" << count << ": listHead -->";
        while(current->next != NULL){
            cout <<"(" << current->charString << ", " << current->prob << ", " << current->next->charString << ")-->";
            current= current->next;
        }
        cout <<"(" << current->charString << ", " << current->prob << ", " << "NULL" << ")-->";
        cout << "NULL" << endl << endl << endl;
    }
    
    
    void hoffmanCode(Node* tempnd, string tempstr){// Step 4.1
        if(tempnd == NULL){// Step 4.2
            return;
        }
        else if(tempnd->left == NULL && tempnd->right == NULL){// Step 4.3
            tempnd->code = tempstr;
        }
        else{// Step 4.4
            hoffmanCode(tempnd->left, tempstr + "0");
            hoffmanCode(tempnd->right, tempstr + "1");
        }
    }

    
    void entropyTable(string fil){
        int probArray[count+1];
        int bitArray[count+1];
        int entrophyArray[count+1];
        
        string *codeArray = new string[count+1];
        string *charArray = new string[count+1];
        codeArr = codeArray;
        charArr = charArray;
        
        
        Node* current = new Node();
        current = dummy;
        int i =0;
        while(current->next != NULL){
            
            
            if(current->charString.length() >1){
                current = current->next;
                
            }
            else{
                charArray[i] = current->charString.at(0);
                probArray[i] = current->prob;
                codeArray[i] = current->code;
                bitArray[i] =static_cast<unsigned int>(current->code.size());
                entrophyArray[i] = probArray[i] * bitArray[i];
                
                
                
                
                current = current->next;
                i++;
            }
            
        }
        printEntropyTable("entro_output.txt", probArray, bitArray, entrophyArray);
        
    }
    
    
    void printEntropyTable(string fil, int* prob, int* bit, int* entro ){
        ofstream outfile;
        
        outfile.open(fil);
        outfile <<"Char     Prob       Code     #Bits     Entrophy" << "\r\n" <<"===================================================" << "\r\n";
        
        
        
        
        
        for(int i = 0; i < count; i++){
            if(prob[i] == 0){
                i++;
            }
            else{
                outfile << charArr[i] << "           " << prob[i] << "        " <<  codeArr[i]  <<  "         " << bit[i]  << "       " << entro[i]  <<"\r\n";
                
            }
        }
        outfile.close();
    }
    
    
    
    /*
    encoding
     
    -PROBLEM, INSERT THE & FOR SPACE
                INSERT + FOR ENETER
     (use scanner to add " " after each word you take in)
     (OR use scanner to read the entireline have it read the spaces int the string and mark it)
     (for c++ use the get line method and then go down the string )(string does take in " ")
     -For each cahracter,read the letter in and search the array
     (checj the code array algortihm to try to map it instead of searching)
     -Insert code into new file
     -Repeat until file is empty
     
     decoding
     -Read every character(0,1)
     -For each character cehck the tree. 
     Go down the tree until you hit the leaf node
     -Print the letter
     */
    
    
    string encode(string file_read, string new_file){
        try{
            ifstream infile;
            ofstream outfile;
            infile.open(file_read);
            outfile.open(new_file);
            string read = "";
            
                /*
                 
                CAN'T GET probArr POINTER to work without magic numnbers
                 unable to use efficent search method
            //PREPARE THE  SEARCH ARRAYS
            string *CodeArray = new string[count];
            string *CharArray = new string[count];
            int new_count = 0;
            for(int i = 0; i < count; i++){
                if((charArr[i]).length()  > 1){
                    cout <<"LENGTH " <<(charArr[i]).length();
                    i++;
                }
                else{
                    cout <<"LENGTH " <<(charArr[i]).length();
                    CodeArray[new_count]= codeArr[i];
                    CharArray[new_count]= charArr[i];
                 //   cout <<"CHAR " << CharArray[new_count] <<"CODE " << CodeArray[new_count] << endl;
                    new_count++;
                }
            }
    
            */
            //use a for loop to read characters between ! and  ~
            //filter the old character array into a temporary array for this method(perhaps use a pointer for decoding)
            //match the code array with the character array in another two tables for faster access
            
            while(std::getline(infile,read)){
                
                
                for(int outlop = 0; outlop < read.length(); outlop++){
                    
                    if(read[outlop] == ' '){
                        outfile << "&";
                    }
                    //OUTPUT THE CODE FROM THE ARRAY TO THE FILE
                    //GO DOWN THE LINKEDLIST AND WHILE
                    for(int inlop = 0; inlop < count; inlop++){
                        //Find the charArr iteration and output the code iteration to the file
                 //        cout << "CHAR " << (int)charArr[inlop].at(0) << endl; cout << "READ " << read[inlop] << endl;
                        
                        //CHAR DOESN'T HAVE A VALUE AT CERTAIN PLACES
                        
                        if(read[outlop] == charArr[inlop].at(0)){
                            
                            
                            
                     //       cout << charArr[inlop] << endl;
                        }
                           }
                           
                           }
                           outfile << "+";
                           }
                           
                           
                           
                           
                           
                           
                           
                           
                           
                           
                           outfile.close();
                           infile.close();
                           return new_file;
                           }
                           catch(ifstream::failure e){
                               cout << endl << "IOException when reading/writing file." << endl;
                               return new_file;
                           }
                           }
                           
                           
    
    
    
};



string histogram(string f){//Step 4.0
    try{
        int size, countarysize = 0;
        ifstream infile;
    //    cout << "Enter size of histogram array." << endl;
        size = 256;
   //     cin >> size;
        ofstream outfile;
        infile.open(f);
        string out = "out.txt";
        outfile.open(out);
        int count[size];
        char temp;
        int charconv = 0;
        
        for(int loop = 0; loop <size; loop++){//Step 4.0
            count[loop]= 0;
        }
        while(!infile.eof()){//Step 4.2
            countarysize++;//Step 4.1
            infile >> temp;
            charconv = temp;
            count[charconv]++;
        }
        char charstr;
        int index = 33;//Step 4.3
        while(index < 256){//Step 4.7
            charstr = index;//Step 4.4
            outfile << charstr << " " << ((count[index]*100)/countarysize) << "\r\n" ;//Step 4.5
            index++;//Step 4.6
        }
        infile.close();
        outfile.close();
        return out;
    }
    catch(ifstream::failure e){
        cout << endl << "IOException when reading/writing file." << endl;
        return "-1";
    }
}



int main(int argc, char * argv[]) {//Step 2.0
    cout << "Please pass 3 arguments, [1]-input file, [2][3]-output files" << endl << endl;
    if(argc < 3){
        cout<< "Not enough arguments" << endl << endl;
        return 0;
    }

    Linkedlist* a = new Linkedlist();
    string temp = histogram(argv[1]);
    a->insertfile(argv[2], temp, argv[3]);
    a->tree("tree_output.txt");
    a->hoffmanCode(a->root, "");
    a->entropyTable(argv[2]);
    temp = a->encode(argv[1], "encoded.txt");
    
    
    return 0;
}





