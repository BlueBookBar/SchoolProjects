import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



public class Tester2 {
	public static void main(String[] args) throws IOException{
		int ture = JOptionPane.showConfirmDialog(null, "HAHA");
		System.out.println(ture);//no is 1, yes is 0, cancel is 2
		Writer tes =  new FileWriter("ou.txt");
		int g =1, q=2, w=3,e=4,r=5,t=6,y=7; 
		tes.write(new Integer(g).toString());
		
		
		tes.close();
	}
}
