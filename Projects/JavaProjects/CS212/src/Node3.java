

public class Node3<T> implements Comparable<T> {
	
	private T data;
	
	private Node3<T> next;
	
	public Node3 (T d){
		data = d;
		
	}
	
	public Node3 (T d, Node3<T> n){
		data = d;
		next = n;
		
	}
	
	public T getData(){
		return data;
	}
	
	public Node3<T> getNext(){
		return next;
	}
	
	public void setNext(Node3<T> a){
		next= a;
	}
	
	

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		//FIDDLE WITH THIS ONE 
		//IF GREATER THEN RETURN 1
		//IF LESSER THEN RETURN -1
		//ELSE RETURN 0
		//But how?
		//"this"
		try{		if(this.getData().toString().length() > o.toString().length()){
			return 1;
		}
		if(this.getData().toString().length() < o.toString().length()){
			return -1;
		}
		else{
		return 0;
		}

			
		}
		catch(IllegalArgumentException a){
					if(this.getData().toString().length() > ((Node3<T>) o).getData().toString().length()){
				
			}
			if(this.getData().toString().length() < ((Node3<T>) o).getData().toString().length()){
				
			}
			else{
				return 0;
			}

		}
		return 0;
	}
	
	
	
	
}