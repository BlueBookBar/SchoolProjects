
public class assign8 {	

	public static int summation(int a, int b){
		if(a == b){
			return a;
		}
		return (a + summation(a+1, b));
	}
	
	public static int summation2(int a, int b){
		if(a == b){
			return(a);
		}
		if(a < b){
			return(a + summation2(a + 1, b));
		}
		else{
			return(a + summation2(a - 1, b));
		}
	}
	
	public int partialSum(Node a){
		if(a.getNext() != null){
			int b = a.getData() + a.getNext().getData(); 
				if((a.getNext().getNext() != null)){ 
					return (b + partialSum(a.getNext().getNext()));
			 }
			 return b;
		}
		else {
			return a.getData();
		 }	 
	}
	
	
	public void printBack(Node a){
		if(a != null){
			printBack(a.getNext());
			System.out.print(a.getData());
		}
		else{
			return;
		}
	}
}
