
public class LinkedList {
	Node dummy;

	LinkedList(){
		dummy = new Node(-99999, null);	
	}
	
	//insert integer
	boolean insertInt(int data){
		Node previous = findSpotInt(data), current;
		if(previous.getNext() == null){
			previous.setNext(new Node(data));
			return true;
		}
		else{
			current = previous.getNext();
			previous.setNext(new Node(data));
			previous.getNext().setNext(current);
			return true;
		}
	}
	
	//insert String NOT DONE
	boolean insertString(int data){// need to change it into string
		Node previous = findSpotString(data), current;
		if(previous.getNext() == null){
			previous.setNext(new Node(data));
			return true;
		}
		else{
			current = previous.getNext();
			previous.setNext(new Node(data));
			previous.getNext().setNext(current);
			return true;
		}
	}
	
	//deletes, however i couldn't program it to backflip and bark
	boolean delete(int data){
		Node previous = search(data);
		if(previous == null){
			return false;
		} 
		Node current= previous.getNext();
		if(current.getNext() == null){
			previous.setNext(null);
			return true;
		}
		else{
			previous.setNext(current.getNext());
			return true;
		}
	}

	//if the node isn't found, it prints message and returns null, returns previous node!!!
	Node search(int a){
		Node current = dummy, previous= dummy;
		while(current.getNext() != null){
			previous = current;
			current= current.getNext();
			if(a == current.getData()){
				return previous;
			}
		}
		System.out.println("Data not found.");
		return null;
	}

	//returns previous so to insert between previous and current
	Node findSpotInt(int data){
		Node current = dummy, previous= dummy;
		while(current.getNext() != null){
			previous = current;
			current= current.getNext();
			if(current.getData() > data ){
				return previous;
			}
		}
		return previous;
	}
	
	//NOT DONE
	Node findSpotString(int data){//need to make it for string
		Node current = dummy, previous= dummy;
		while(current.getNext() != null){
			previous = current;
			current= current.getNext();
			if(current.getData() >= data ){
				return previous;
			}
		}
		return previous;
	}
	
	//prints, down the list and print
	void print(){
		Node current = dummy;
		while(current.getNext() != null){
			current = current.getNext();
			System.out.print(current.getData()+ " ");
		}
	}
	
}
