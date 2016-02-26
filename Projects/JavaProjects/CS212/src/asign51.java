import javax.swing.JOptionPane;

public class asign51 {    
        

    //done, check works
	public static boolean check(char s[][], int row, int col){//checks for win
		int c = 0;
		for(int a = 0; a < row; a++){// checks the array for X & O
			for(int b = 0; b < col; b++){
				if(s[a][b] != ' '){
					if(s[a][b] == 'X'){
					
						for(c = 0; c < row; c++){//if 'O' is found break the loop, vertical
							if(s[c][b] != 'X'){
								break;
							}
							if(c == (row -1)) {
								return true;
							}
						}
						for(c = 0; c < col; c++){// if 'O' break, horizontal
							if(s[a][c] != 'X'){
								break;
							}
							if(c==(col - 1)) {
								return true;
							}
						}
					}
					else{
						for(c = 0; c < row; c++){//if 'X' is found break the loop, vertical
							if(s[c][b] != 'O'){
								break;
							}
							if(c==(row - 1)) {
								return true;
							}
						}
						for(c = 0; c < col; c++){// if 'X' break, horizontal
							if(s[a][c] != 'O'){
								break;
							}
							if(c==(col - 1)) {
								return true;
							}
						}
				
					}
				
				}				
				
			}
		}
		
		//checks for diagonals now
		
		if(s[1][1] != ' '){
			if(s[1][1] == 'X'){
				for(int h = 0; h < row; h++){// checks \ diagonal
					if(s[h][h] != 'X'){
						break;
					}
					if(h == (row - 1)){
						return true;
					}
				}
				int q = col;
				for(int k = 0; k < row; k++){// checks / diagonal
						q--;
						if(s[k][q] != 'X'){
							break;
							
						}
						if(k == (row - 1)){
							return true;
							}
				}
			}
			else{
				for(int h = 0; h < row; h++){// checks \ diagonal
					if(s[h][h] != 'O'){
						break;
					}
					if(h ==(row - 1)){
						return true;
					}
				}
				int q = col;
				for(int k = 0; k < row; k++){// checks / diagonal
						q--;
						if(s[k][q] != 'O'){
							break;
							
						}
						if (k == row -1){
							return true;
							}
				}
			}
		}
			
		return false;
	}
	
	
	
	
	
	
	//done, does not print well
	public static int print(char a[][], int row, int col){
		for(int b = 0; b < row; b++){
			for (int c = 0; c < col; c++){
				System.out.print(a[b][c]);
			}
			System.out.println();
		}
		System.out.println();
		return 0;
	}
    
	
	
	
    //done, it does work
    public static void main(String[] args){
        char [][] a = new char[3][3]; int d = 0, e = 0, f = 0;
        for(int b = 0; b < 3; b++){
        	for(int c = 0; c< 3; c++){
        		a[b][c] = ' ';
        	}
        }

        while(d < 10){
        	d++;
        	
        	
        	
        	if(d%2 == 0){
        		 e = Integer.parseInt(JOptionPane.showInputDialog("For player O: Select row, enter a number between 1 - 3")) - 1;
        		 f = Integer.parseInt(JOptionPane.showInputDialog("For player O: Select column, enter a number between 1 - 3")) - 1;
        		 while(e < 0 || e > 2){
            		 e = Integer.parseInt(JOptionPane.showInputDialog("For player O: Select row, enter a number between 1 - 3")) - 1;
        		 }
        		 while(f < 0 || f > 2){
            		 f = Integer.parseInt(JOptionPane.showInputDialog("For player O: Select column, enter a number between 1 - 3")) - 1;
        		 }
        		 if(a[e][f] != ' '){
        		 		d--;
        		 	}
        		 else{
        		 a[e][f] = 'O';
        		 }
        	}
        	
        	else{
        		e = Integer.parseInt(JOptionPane.showInputDialog("For player X: Select row, enter a number between 1 - 3")) - 1;
       		 	f = Integer.parseInt(JOptionPane.showInputDialog("For player X: Select column, enter a number between 1 - 3")) - 1;
       		 	while(e < 0 || e > 2){
       		 		e = Integer.parseInt(JOptionPane.showInputDialog("For player X: Select row, enter a number between 1 - 3")) - 1;
       		 	}
       		 	while(f < 0 || f > 2){
       		 		f = Integer.parseInt(JOptionPane.showInputDialog("For player X: Select column, enter a number between 1 - 3")) - 1;
       		 	}       
       		 	if(a[e][f] != ' '){
       		 		d--;
       		 	}
       		 	else{
       		 	a[e][f] = 'X';
       		 	}
        	}
        	print(a, 3, 3);
        	if(d > 4){
        		if(check(a, 3, 3)){
        			System.out.println("Game over!");
        			break; 		
        		}
        	}
        }
        
    }









}