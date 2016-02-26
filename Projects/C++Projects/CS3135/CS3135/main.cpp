#include <fstream>
#include <iostream>
using namespace std;

class Node{// Step 0
    public:
    Node* left= NULL;
    Node* right = NULL;
    int data = 0;
    
    Node(){
    }
    
    Node(int a){
        data = a;
    }
    
    Node(int a, Node* b){
        data = a;
        left = b;
    }
    Node(int a, Node* b, Node* c){
        data = a;
        left = b;
        right = c;
    }
    
    void setData(int a){
        data = a;
    }
    
    void setLeft(Node* a){
        left = a;
    }
    void setRight(Node* b){
        right = b;
    }
    
    int getData(){
        return data;
    }
    
    Node* getRight(){
        return right;
    }
    
    Node* getLeft(){
        return left;
    }
    
    void Copy(Node* a){
        data = a->data;
        if(a->right == NULL){
            right = NULL;
        }
        else if(a->left == NULL){
            left = NULL;
        }
        left = a->left;
        right = a->right;
        
    }
};








class BST{// Step 0
    public:
    Node* root = new Node(99999);
    
    BST(){
    }
    BST(int a){
        root->data = a;
    }
    
    
    
    public:
    Node* findSpot(Node* Spot, int data){
        while(Spot->data != data){// Step 11
            if(Spot->left == NULL && Spot->right == NULL){// Step 6
                cout << "1" << endl;

                return Spot;
            }
            else if(Spot->right == NULL){//Step 7
                if(Spot->data < data){
                    cout << "2" << endl;

                    return Spot;
                }
                else{
                    Spot = Spot->left;
                    cout << "3" << endl;

                }
            }
            else if(Spot->left == NULL){// Step 8
                if(Spot->data > data){
                    cout << "4" << endl;

                    return Spot;
                }
                else{
                    cout << "5" << endl;

                    Spot = Spot->right;
                }
            }
            else{// Step 9
                if(Spot->data > data){
                    cout << "6" << endl;

                    Spot = Spot->left;
                }
                else{
                    cout << "7" << endl;

                    Spot = Spot->right;
                }
            }
            cout << "Searching..." << endl;
            print(root);            // Step 10
            cout << "8" << endl;
            cout << endl << endl;
        }
        return NULL;
    };
    
    
    void Search(int data){
        if(findSpot(root, data) == NULL){
            cout << "The data " << data << " exists in the tree!" << endl << endl;
        }
        else{
            cout << "Data " << data << " does not exist in the tree." << endl << endl;
        }
    };
    
    
    
    void leafInsert(Node* Spot, Node* leaf){
        if(Spot->right == NULL && Spot->left == NULL){// Step 4
            if(Spot->data > leaf->data){
                Spot->left = leaf;
            }
            else{
                Spot->right = leaf;
            }
        }
        else if(Spot->left == NULL){// Step 5
            Spot->right= leaf;
        }
        else{
            Spot->right = leaf;
        }
        
    };
    
    
    
    
    
    
    
    void print(Node* Spot){
        if(Spot == NULL){
            return;
        }
        if(Spot->data == 99999){
            print(Spot->left);
            print(Spot->right);
            
        }
        else{
            print(Spot->left);
            cout << Spot->data << endl;
            print(Spot->right);
            
        }
        
        
        
    };
    
};













int main() {
    string file, data; Node* Spot = NULL, node = NULL; int dataA; BST first(99999);
   cout << "What is the name of the file to be read?" <<endl;
      cin >> file;
    ifstream input;
    input.open(file);
    if(input.fail()){
        cout << "File not found";
        return 1;
    }
    while(!input.eof()){// Step 2
        input >> data;// Step 1
        if(data == "+"){// Step 1.3
            input >> data;
            dataA = stoi(data);
            Spot = first.findSpot(first.root, dataA);
            if(Spot == NULL){
                cout << " The data " << dataA << " exists in the tree!";
            }
            else{
                node = *new Node(dataA);
                cout << "Inserting " << dataA << "..." << endl;
                first.leafInsert(Spot, &node);
                cout << dataA << " inserted." << endl;
                cout << endl << endl;
            }
        }
        else if(data == "*"){// Step 1.2
            input >> data;
            dataA = stoi(data);
            first.Search(dataA);
        }
        else if (data == "p"){// STep 1.1
            cout << "Printing full tree..." << endl;
            first.print(first.root);
            cout << endl << endl;
        }
    }
    input.close();
    return 0;
}
