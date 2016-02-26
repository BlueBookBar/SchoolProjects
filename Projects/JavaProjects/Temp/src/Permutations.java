
public class Permutations {

	//Taken from 'http://stackoverflow.com/questions/2799078/permutation-algorithm-without-recursion-java/11471673#11471673'
	private static void printPermutationsIterative(String string){
        int [] factorials = new int[string.length()+1];
        factorials[0] = 1;
        for (int i = 1; i<=string.length();i++) {
            factorials[i] = factorials[i-1] * i;//1, 1, 2, 6, 24...
        }

        for (int i = 0; i < factorials[string.length()]; i++) {
            String onePermutation="";
            String temp = string;
            int positionCode = i;// 0 		1, 2, 3
            for (int position = string.length(); position > 0 ;position--){//4, 3		, 2, 1
                int selected = positionCode / factorials[position-1];//0/3
                onePermutation += temp.charAt(selected);// A
                positionCode = positionCode % factorials[position-1];// 0%1
                temp = temp.substring(0,selected) + temp.substring(selected+1);
              //  System.out.println("position is "+ position);
               // System.out.println("positionCode is "+positionCode);
                //System.out.println("factorials[position-1] is "+ factorials[position-1]);
              //  System.out.println("Selected is "+selected);
             //   System.out.println("Temp is "+ temp);
            //    System.out.println("One Permutation "+onePermutation);
           //     System.out.println("");
            }
         //   System.out.println("_________Next Iteration. End inner for loop");
            System.out.println(onePermutation);
        }
    }
	public static void main(String[] args){
		printPermutationsIterative("ABCD");
	}

}
