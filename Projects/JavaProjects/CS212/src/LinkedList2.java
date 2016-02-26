
public class LinkedList2 {
	private Node2 head;
	private Node2 last;
	
	public LinkedList2 (int x) {
		head = new Node2(x);
		last = head;
	}
	
	public LinkedList2 (Node2 n) {
		head = n;
		last = head;
	}
	
	public Node2 find(int x) {
		Node2 current = head;
		while (current!=null){
			if (current.getData()==x){
				break;
			} else {
				current = current.getNext();
			}
		}
		return current;
	}
	
	public void insert(int x) {
		if (head.getData()>x){
			head = new Node2(x, head); // case 1: insert before the head
			head.getNext().setPrevious(head);
		} else {
			Node2 current = head;
			while (current != null){
				if (current.getData()<x) {
					current = current.getNext();
				}else {
					// case 2: insert into the middle of the list
					current.getPrevious().setNext(new Node2(x, current, current.getPrevious()));
					current.setPrevious(current.getPrevious().getNext());
					break;
				}
				if (current == null){
					last.setNext(new Node2(x, null, last)); // case 3: insert at end
					last = last.getNext();
				}
			}
		
		}
	}
		
	public boolean delete (int x) {
		Node2 current = find(x);
		if (current == null){
			return false;
		}
		if (current.getPrevious() !=null){
			current.getPrevious().setNext(current.getNext());
		} else {
			head = current.getNext();
		}
		if (current.getNext()!=null){
			current.getNext().setPrevious(current.getPrevious());
		} else {
			last = current.getPrevious();
		}
		return true;
		
	}
	
	public void Replace2(int a, int b){// find node val a, new node val b
		Node2 current = find(a);
		Node2 another2 = new Node2(b);
		// two issues, if the previous or next pointer is null
		
		//set ANOTHER!!
		if(current.getPrevious() == null) {//first place *_<- -> _
			current.getNext().setPrevious(another2);//_ <-
			another2.setNext(current.getNext());//_ ->
		}
		if(current.getNext() == null){//last  _ <- -> _*
			current.getPrevious().setNext(another2);// -> _
			another2.setPrevious(current.getPrevious());// <- _
		}
		else{
			//set previous pointers of the next node and the new node
			// set next pointer of the previous node and new node
			current.getNext().setPrevious(another2);
			another2.setNext(current.getNext());
			current.getPrevious().setNext(another2);
			another2.setPrevious(current.getPrevious());
		}
	}	
		
		
		
		
		
}
