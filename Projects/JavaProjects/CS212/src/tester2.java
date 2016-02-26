
public class tester2 {

	public static void main(String[] args) {


		int []a = new int []{8, 5, 1, 6, 0, 4, 10, 3, 9};
	
		assiggn2.Bubble(a);
	
		for(int t = 0; t < a.length; t++){
			System.out.println(a[t]);
		}
		char[][] s = new char [4][4];
		
		for (int c = 0; c < 4; c++){
			for(int g = 0; g < 4; g++){
			if((c%2 == 0) || (g%2 == 0)){
				s[c][g] = '1';
				System.out.print(s[c][g]);
			}
			else{
				s[c][g] = ' ';
				System.out.print(s[c][g]);
			}
			}
			System.out.println();
		}		
		if(s[-1][-1] != ' '){
			System.out.print("wHOAA");
		}
		else{
			System.out.print("No good buddy");
		}
		
	}

}
