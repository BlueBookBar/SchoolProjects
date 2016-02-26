package Phase2;

//IMPORT NEEDED LIBRARIES

import java.net.*;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Scanner;
import java.util.Date;
import javax.swing.JOptionPane;

public class ScanWeb {

	//GET FROM FILE
	//LOG ON TO INTERNET AND BRING INFORMATION INTO TEXT FILE
	//


	public static void main(String[] args) {
		try{
			Scanner infile = new Scanner(new FileReader(args[0]));//input file
			Writer outfile = new FileWriter(args[1]);//output file will be the second argument
			String nameoffile= "html0.txt";int count = 1;
			
			
			
			
			while(infile.hasNext()){//run through the file until it is empty, check each HTML
				String line= infile.nextLine();//Get the next html
				URL Website = new URL(line);
				BufferedReader in = new BufferedReader(new InputStreamReader(Website.openStream()));
				Writer Htmlfile = new FileWriter(nameoffile);//output file to hold html
				String inputLine;//holds the url string
				
				
				
				while ((inputLine = in.readLine()) != null){//throws all data into html file
					Htmlfile.write(inputLine);		
					Htmlfile.write("\r\n");		
				}
				
				
				
				nameoffile = "html"+(count++) +".txt";//rename the next html file
				in.close();//close the file
			}
			
			
			for(int i = 0;i < count-1;i++){//Go through each html file and output the title
				String tempstr;//analyze the string
				Scanner htmlfile = new Scanner(new FileReader("html"+i+".txt"));//input file
				
				while(htmlfile.hasNextLine()){
					tempstr = htmlfile.nextLine();
					if(tempstr.contains("<title>")){
						outfile.write(tempstr+ "\r\n");
					}
				}
				
				
				htmlfile.close();
			}
			
			
			
			
			infile.close();
			outfile.close();
		}
		catch(Exception e){//Catch the exception
			e.printStackTrace();
		}

	}

}
