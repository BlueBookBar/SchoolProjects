public class Asign3 {
	
	public int size = 1;// the size of the object
	private int[]arr = new int [size];// the array of the object
	private int lastindex = 0;// the last index of the object

	
	public Asign3 GSSArray = new Asign3(10);// new object,							
		
	public Asign3(int a){ //constructor
		size = a;
	}

	
	public void Insert(int a) {
		if(size > 0){
		if(lastindex >= arr.length - 1){
			arr = increaseSize(arr);		
		}
		arr[lastindex++] = a;	
		}
		arr= sortb(arr);
	}

	private int[] increaseSize(int[] a){
		int d = (a.length)*2;
		int [] b = new int [d];
		for (int c = 0; c < a.length; c++ ){
		b[c] = a[c];
		}
		return b;
//passes old & returns the new array of double size and same values
	}
	
	private int[] sortb(int [] a){
		boolean y = true;
		int c =0, d = 0;
		
		while(y){
			d = 0;
			for (int b = 1; b <= (lastindex); b++){
				if(a[b-1] > a[b]){
				c = a[b-1];
				a[b-1] = a[b];
				a[b] = c;
				}
				else {
					d++;
				}
			}
			if(d == (lastindex)){
				y = false;
			}
		}
	return a;
}
	
	public boolean delete(int a){
		if(size <= 0){
			return false;
		}
		int d, j = 0;
		
		for (int y = 0; y < arr.length; y++) {
			
			if(arr[y] == a){
				a = 0;
				j = y;
				break;
			}
			
		}
		if (j > 0){
			for(int h = j; h < (arr.length - 1); h++){
			d = arr[j];
			arr[j] = arr[j+1];
			arr[j+1] = d;
			}
			lastindex--;
			arr = decreasesize(arr);
			return true;
		}
		
		return false;
//turns 1st instance of a number into 0 then brings it to the back of arr
	}
	
	private int[] decreasesize(int[] a){
		int d = (a.length) - 1;
		int [] b = new int [d];
		for (int c = 0; c < d; c++ ){
			b[c] = a[c];
			}
		return b;
//decreases size by one and copies to new smaller array
	}
	
	
	
	
}
