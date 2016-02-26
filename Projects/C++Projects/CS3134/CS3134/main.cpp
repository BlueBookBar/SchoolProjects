#include <iostream>
#include <fstream>
using namespace std;

//Count function
int counted(string file){
    int a =0, count =1;
    
    ifstream input;
    input.open(file);
    
    if(input.fail()){
        exit(1);
    }
    
    while(!input.eof()){
        input >> a;
        count++;
    }
    input.close();
    
    
    return 0;
}

//Swap function
void swap(int &a, int &b){
    int temp = b;
    b = a;
    a = temp;
}


void bubbleUp(int *array){              //Step 5
    int child = array[0], father = child/2;       //Step 1 & 2
    while(!(array[father] <= array[child])|| !(child == 1)){                   // Step4, Repeat steps 2 and 3
        father= child/2;                //Step 2
        if(array[child] < array[father]){       //Step 3
            swap(array[child],array[father]);
        }
        child = father;
    }
}

void bubbleDown(int *array){        //Step 10
    int father = 1; int smallkid = 0; int last = array[0];      //Step 1
    int leftkid = father*2, rightkid = (father*2)+1;            //Step 2
    while(array[father] > array[smallkid] || !(leftkid > last && rightkid > last)){	//Step 5
        leftkid = father*2;						//Step 2
        rightkid = (father*2) +1;
        //case 1            //Step 3
        
        if(rightkid <= last){//if there is a right kid and left
            if(array[leftkid] < array[rightkid]){// if the left is smaller
                smallkid = leftkid;
            }
            else{// if the right is smaller
                smallkid = rightkid;
            }
        }
        //case2 if there is only a left kid
        else if(leftkid <= last && rightkid > last){//looks at only the left kid
            smallkid = leftkid;
        }											//Step 3 end
        //Step 4 begins
        if(smallkid <= last && array[smallkid] < array[father]){// next iterations of the loop,dont mess up
            //swap the father and smallkid

            swap( array[smallkid], array[father]);

        }
        father = smallkid; // go down the tree
    }
}




void Heap_Sort_Build(int *array, string file){  //Build the heap sort
    int last = 0, data;
    
    ifstream input;
    input.open(file);
    
    if(input.fail()){
        exit(1);
    }
    array[0] = last;
    while(!input.eof()){
        input >> data;
        last++;
        array[last] = data;
        array[0]= last;
        bubbleUp(array);
    }
    input.close();
    
    
}

void Heap_Sort_Delete( int *array){    //Delete and print the heap

    int first = 1, last = array[0];//Step 8
    while(first < last){//Step 11
        cout<< array[first] <<endl;//Step 8
        array[first] = array[last];//Step 9
        last--;         //Step 9
        array[0] = last;
        bubbleDown(array);
    }
    
}






int main() {
    string file;
    cout << "Input the file name to be read" << endl;
    cin >>  file;
    int count = counted(file);
    int array[count];
    array[0] = count-1;
    Heap_Sort_Build(array, file);
    Heap_Sort_Delete(array);

    

    
    return 0;
}
