
public class LinkedList {
	protected Node head;
	protected int count;
	
	public LinkedList(){
	}

	public LinkedList(int x){
		head = new Node(x);
		count = 1;
	}
	
	public LinkedList(Node n) {
		head = n;
		count = 1;
	}
	
	public void append(int x) {
		if(head!=null){
			Node current = head;
			count = 1;
			while (current.getNext()!=null){
				current = current.getNext();
				count++;
			}
			current.setNext(new Node(x));
		}else {
			head =new Node(x);
			count = 1;
		}
	}
	
	public void insert(int x){
		if (head.getData()>x){
			head = new Node(x,head); // case 1: insert at beginning
		} else {
			Node current = head;
			Node previous = head;
			boolean inserted = false;
			while (current!=null){
				if(current.getData()<x){
					previous = current;
					current = current.getNext();
					count++;
				}
				else {
					previous.setNext(new Node(x,current)); // case 2: insert into the middle
					inserted = true;
					count++;
					break;
				} // if - else (current.getData()<x)
			} // while
			if (!inserted) {
				count++;
				previous.setNext(new Node(x)); // case 3: insert at end
			}
		} // if-else (head.getData>x)
	}
	
	public Node find(int x) {
		Node current = head;
		while (current!=null){
			if (current.getData()==x){
				break;
			} else {
				current = current.getNext();
			}
		}
		return current;
	}
	
	public boolean delete (int x){
		if (head.getData()==x){
			head = head.getNext();// case 1: deleting the head
			count--;
			return true;
		}
		Node current = head;
		Node previous = head;
		while (current!=null){
			if (current.getData()==x){
				previous.setNext(current.getNext()); // case 2: delete middle or end
				count--;
				return true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}
		return false;
	}
	
	public String toString(){
		String s = " ";
		Node current =head;
		while (current!=null){
			s = s + current.getData() + " ";
			current = current.getNext();
		}
		return s;
	}
	
	public boolean Replace(int a, int b){// a=search node val, b=new node val 
		Node current = head;// current node, new node
		Node another = new Node(b);
		if (head.getData() == a) {
			another = head.getNext();
			return true;
		}
		// go through the list to find x now
		while(current != null){
		if (current.getNext().getData() == a){
			if(current.getNext().getNext() == null){
			current.setNext(another);	
			}
			else{
			another.setNext(current.getNext().getNext()) ;
			current.setNext(another);
			}
			return true;
		}	
		else{
			current = current.getNext();
		}
		}
		return false;
		
	}
	
	public void sum() {
		Node current = head;
		int x = 0;
		while (current != null) {
			x = x+ current.getData();
			current = current.getNext();
		}
		System.out.println(x);
	}
	

	
}
