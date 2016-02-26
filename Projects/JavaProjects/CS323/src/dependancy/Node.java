package dependancy;

public class Node {
    Node next= null;
    int jobID =0;
    int time =0;

	public Node() {
	}
    Node(Node b){
        next = b;
    }
    Node(int a){
        jobID=a;
    }
    
}
