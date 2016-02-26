#include <iostream>//Step 0
#include <fstream>
#include <cstdlib>
#include <string>
using namespace std;


class node{//Step 0
protected:
    int count = 1;
    string word;
    node* next;
    
public:

    node(){
    };
    node(string a){
        word = a;
    };
    node(string a, node* b){
        word = a;
        next = b;
    };
    node(string a, node* b, int c){
        word = a;
        next = b;
        count = c;
    };
    node(node* b){
        next = b;
    };
    
    string words(){
        return word;
    };
    node* nodes(){
        return next;
    }
    int counts(){
        return count;
    }
    void addcount(){
        count++;
    };
    void setNext(node* b){
        next = b;
    }
    void setWord(string a){
        word =a;
    }
    
    
};



class LinkedList{//Step 0
private:
    node head= NULL;//Step 1
public:
    
    LinkedList(){
    };
    
    
    void storetext(string a){//Step 0
        string data; node walker = head;//Step 0
        ifstream input;//Step 1
        input.open(a);
        if(!input.is_open()){
            cout << "It didn't open" << endl;
            exit(EXIT_FAILURE);
        }//Step 1
        while(!input.eof()){//Step 8
            input >> data;//Step 2
            walker = head;//Step 3
            while (walker.nodes() != NULL){//Step 4
                if(walker.nodes()->words() < data){
                    walker = walker.nodes();
                }
                else{
                    break;
                }
            }
            if(walker.nodes() != NULL){//Step 6
                if(walker.nodes()->words() == data){
                    walker.nodes()->addcount();
                }
            }
            else{//Step 6
                if(walker.nodes() != NULL){
                    node temp = new node(data, walker.nodes(), 1);
                    walker.setNext(&temp);

                }
                else{

                    node temp = new node(data, NULL, 1);
                    walker.setNext(&temp);
                }
                this->print();//Step 7
            }
        }
        input.close();//Step 9
    }
    
   
    void print(){//Step 0
        node current= head;
        while(current.nodes() != NULL){
            cout<< current.words();
            current = current.nodes();
        }
    }
};





int main() {
    string a;
    cout << "What is the name of the file?" << endl;
    cin >> a;
    LinkedList list;
    list.storetext(a);
    return 0;
}
