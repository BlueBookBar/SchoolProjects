package hoffmanCoding;

public class Node {	// Step 2.0
	private Node next = null;
	private Node left = null;
	private Node right = null;
	private String charString = "dummy";
	private String code = "";
	private int prob = 0;
	private int count = 0;
	
	
	Node(){	
	} 
    Node(Node b){
        next = b;
    }
    Node(Node b, String a){
        next = b;
        charString = a;
    }
    Node(Node b, String a, int c){
        next = b;
        charString = a;
        prob = c;
    }
    Node(String a, int c){
        charString = a;
        prob = c;
    }
    
    
    
    Node getNext(){
    	return next;
    }
    Node getLeft(){
    	return left;
    }
    Node getRight(){	
    	return right;
    }
    String getString(){
    	return charString;
    }
    int getProb(){
    	return prob;
    }
    int getCount(){
    	return count;
    }
    String getCode(){
    	return code;
    }
    void setCode(String add){
    	code = code+add;
    }
    void setNext(Node a){
    	next = a;
    }
    void setLeft(Node a){
    	left = a;
    }
    void setRight(Node a){	
    	right = a;
    }
    void setString(String a){
    	charString = a;
    }
    void setProb(int a){
    	prob = a;
    }
    void setCount(int a){
    	count = a;
    }
}
