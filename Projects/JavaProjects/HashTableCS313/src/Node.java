
public class Node {//Step 0
	private int data;
	private Node next;

	public Node(){
		next = null;		
	}
	
	public Node(int a){
		data = a;
		next = null;
	}
	
	public Node(int a, Node n){
		data = a;
		next = n;
	}
	
	public Node getNext(){
		return next;
	}
	
	public int getData(){
		return data;
	}
	
	public void setData(int a){
		data = a;
	}
	
	public void setNext(Node n){
		next = n;
	}
}
