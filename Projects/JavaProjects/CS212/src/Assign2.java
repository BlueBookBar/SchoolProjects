import javax.swing.JOptionPane;

public class Assign2 {
    public static void main(String []args){
        
    String st1 = JOptionPane.showInputDialog("Enter a number.");
    
    int st5 = st1.length(); 
    int f, a = 0;
    String st3 = "";
    String st2 ="";
    
    for(f = 0 ;f < st5; f++){
    
    char cht = st1.charAt(f);
    if ((Character.isDigit(cht)) ) {
        a++;
    }
    else{
    	st3 = st3 + (st1.charAt(f));
    	
    }
    }
    
    int st4 = st3.length();
    
    if(a == st5){
        
   
    

        
            for(int k = (st5 -1); k >= 0; k--){
                st2 = st2 + (st1.charAt(k));  
               
                                    
    
    }
    JOptionPane.showMessageDialog(null, st2);
    }
    else {
   
    	
    if(st4 > 1) {
    	
    JOptionPane.showMessageDialog(null, st3 + " are not numbers. Try again...");
    }
    else{
    	JOptionPane.showMessageDialog(null, st3 +" is not a number. Try again...");
  
    }
    
    }
    
    
    }
}