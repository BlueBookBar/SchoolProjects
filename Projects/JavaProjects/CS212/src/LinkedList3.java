

public class LinkedList3<T> {
	
	private Node3<T> head;
	
	public LinkedList3 (T d){
		head = new Node3<T>(d);
	}
	
	public LinkedList3 (Node3<T> a){
		head = a;
	}
	
	public void append(T x) {

		if(head!=null)

		{

			Node3<T> current = head;

			while ( current.getNext()!=null)

				{

					 current =  current.getNext();

				}

			current.setNext(new Node3<T>(x));

		}

		else 

		{

			head = new Node3<T>(x);

		}

	}

	public void insert(T x){

		if (((Comparable<T>) head.getData()).compareTo(x) > 0){

			head = new Node3<T>(x,head); 

		} else {

			Node3<T> current = head;

			Node3<T> previous = head;

			boolean inserted = false;

			while (current!=null){

				if(((Comparable<T>) head.getData()).compareTo(x) < 0){

					previous = current;

					current = current.getNext();

				}

				else {

					previous.setNext(new Node3<T>(x,current)); 

					inserted = true;

					break;

				} 

			} 

			if (!inserted) {

				previous.setNext(new Node3<T>(x)); 

			}

		} 

	}
	
	public Node3<T> find(T x) {

		Node3<T> current = head;

		while (current!=null){

			if (current.getData()==x){

				break;

			} else {

				current = current.getNext();

			}

		}

		return current;

	}





	public boolean delete (T x){

		if (head.getData()==x){

			head = head.getNext();  

			return true;

		}

		Node3<T> current = head;

		Node3<T> previous = head;

		while (current!=null){

			if (current.getData()==x){

				previous.setNext(current.getNext());

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

		Node3 <T> current =head;

		while (current!=null){

			s = s + current.getData() + " ";

			current = current.getNext();

		}
		System.out.println(s);
		return s;

	}
	

	public  Node3<T> toString2(){

		Node3 <T> current = head.getNext();
		Node3 <T> current1 = head.getNext();
		
		System.out.println(((Node3<T>) current.getNext().getData()).getData());
				
		while (current1!=null){
			current = (Node3<T>) current1.getData();
			System.out.println(current.getData());
			current1 = (Node3<T>) current.getNext();
		}
		
		
return current;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}