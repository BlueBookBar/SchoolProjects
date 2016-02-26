import java.lang.Integer;
import java.lang.String;

public class assiggn2 {
	//locate smallest
	//push that up
	// look at the rest
	
	public static void Select (String sort[]) {
		int x = sort.length;
		String hold;
		for(int h = 1; h <= x; h++){
			int u = h -1;
			for(int g = h; g < x; g++){				
				if(sort[g].compareTo(sort[u]) < 0){
					u = g;
				}
			}
			hold = sort[h-1];
			sort[h-1]= sort[u];
			sort[u] = hold;
		}
			
	}
		
	
	
	
	public static void Bubble (int sort[]) {
		int x = sort.length; 
        int b = 0; boolean h = true;
		while (h) {
			h = false;
			for (int g = 1; g < x; g++) {
				if (sort[g] < sort[g-1]){
					b = sort [g - 1];
					sort[g-1] = sort [g];
					sort[g] = b;
		h = true;
				}
			}
		}
    }
	
	public static void main(String[] args){
		int num[] = new int[50];
		String let[] = new String [50];
		for(int y = 0; y < 50; y++){
			num[y] =(int) (1000* Math.random());
			let[y] =Integer.toString( num [y]);
		}
		for (int g = 0; g < 50; g++){
		System.out.print(num[g]);
		System.out.print(" ");
	}
		System.out.println();
		for (int h = 0; h < 50; h++){
		System.out.print(let[h]);
		System.out.print(" ");
	}
		System.out.println();
	
		Bubble(num);
		for(int g = 0; g < 50; g++){
		System.out.print(num[g]);
		System.out.print(" ");
	}
		System.out.println();
		
		Select(let);
		for(int g = 0; g < 50; g++){
		System.out.print(let[g]);
		System.out.print(" ");
	}
		System.out.println();
	}
	
	
}
