package project_2;

import javax.swing.*;

import java.io.*;
import java.util.Scanner;

public abstract class buildTree extends LexArithArray {

	static int counts=0;



	/*
	 * GO THROUGH THE FILE AGAIN
	 * WHILE LOOP
	 * READ EACH STRING AND ADJUSTT ACCORDINGLIY
	 * 
	 * 
	 * 
	 * */


	public static void handle(String in){
		try{
			String tempstr= null;
			Scanner infile = new Scanner(new FileReader(in));
			Writer outfile = new FileWriter("output_tree.txt");
			int paren=0;//count how many parenthesis
			HEADER head = new HEADER(false);
			DEF def= new DEF(null);
			FUNCTION_NAME fun = new FUNCTION_NAME();
			PARAMETERS para = new PARAMETERS();
			boolean noparameters= false;//are parameters set?
			counts = 0;

			while(infile.hasNext()){
				tempstr = infile.next();
				int size= tempstr.length();
				for(int i= 0; i < size; i++){
					char tempchar = tempstr.charAt(i);




					if(tempchar =='('){
						paren++;
					}
					else if(tempchar == ')'){
						paren--;
					}
					if(tempchar== '='){
						noparameters = false;
					}
					
					
					//NEED FUNCTINO NAME AND PARAMETERS


				}

				if(tempstr == "def"){
					counts = 0;
					def = new DEF("def");
					outfile.write(def.output()+ "\r\n");
					head = new HEADER(true);
					outfile.write(head.output() + "\r\n");
					noparameters = true;
				}
				else if(counts == 2 && noparameters){//tunr this off when and = is found
					outfile.write((counts+1) + "   " + tempstr );
				}
				
				 else if(head.func()){
					outfile.write(fun.output() + " "+ tempstr);
					outfile.write(para.output());
					head.fun_used();
				}

				
			}
			if(paren !=0){
				System.out.print("ERROR, Parenthesis mismatch."+ "\r\n");
				outfile.write("ERROR, Parenthesis mismatch."+ "\r\n");
			}
			infile.close();
			outfile.close();
			return;

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return;
	}




}
