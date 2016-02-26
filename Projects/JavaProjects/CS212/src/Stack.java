
public class Stack extends LinkedList {
	

	public Stack(int x){
		Node d = new Node(x);
		head = new Node(0, d);
		count++;
	}
	
	public void insert(int x){// insert count
		if (head.getData()>x){
			head = new Node(x,head);
		} else {
			Node current = head;
			Node previous = head;
			boolean inserted = false;
			while (current!=null){
				if(current.getData()<x){
					previous = current;
					current = current.getNext();
				}
				else {
					previous.setNext(new Node(x,current)); 
					count++;
					inserted = true;
					break;
				} 
			} 
			if (!inserted) {
				previous.setNext(new Node(x)); 
			}
		} 
	}
	
	public boolean delete (int x){// x = number of nodes to be deleted
		if(head!= null){
			return false;
		}
		if(x <= 0){
			return false;
		}
		else{
			int b = 1; Node current = head; Node previous = current;	
			while(current.getNext() != null){
				current = current.getNext();
				b++;	//counts the number of total nodes
		}
			if(b < x){//if the number is greater than the amount of nodes then
				x = b;// x becomes the maximum amount of nodes as possible
			}	
			int c = b - x;// where to start from(c), max - amount = what's left
			count = c;//how many will be left, recorded by count
			current = head;// point to the beginning
			while(c >= 1){
				current = current.getNext();
				c--;
				//find where to start
			}
			while(current != null){
				previous.setNext(current.getNext());
//cuts off from the starting point
			}			
		}		
		return true;
	}
	
	public void Clearall(int x){
		int a = count; Node current = head;
		while(a >= 1){
			Node previous = current;
			current = current.getNext();
			a--;
		}
		
		
	}
	
	
	
}
