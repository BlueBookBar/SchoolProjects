package Testing;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.Date;
import javax.swing.JOptionPane;

public class dummy {
	
	
	//A METHOD TO RETURN THE TIMESTAMP AS STRING
	static String time(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		return ("Time: "+formattedDate);
	}
	
	
	
	

	//JDBC DRIVER STRING WILL BE INPUTTED TO THE DRIVER CLASS, THIS IS THE CLASS THAT WILL BE LOADED
	//DB_URL STRING WILL HOLD THE DATABASE URL
	static  String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static  String DB_URL = "jdbc:mysql://localhost:3306/";

	//Database credentials
	private static String USER;
	private static String PASS;

	public static void main(String [] args){


		//Change the DATABASE settings?
		USER = "root";
		PASS = "root";
		JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		DB_URL = "jdbc:mysql://localhost:3306/";
/*		
		int change = JOptionPane.showConfirmDialog(null, "Do you want to change the USER from the default?");

		if(change == 0){//if yes then change it
			String temp= JOptionPane.showInputDialog("Input the user of the database.");
			if(temp == null){
				USER = "root";
			}
			else{
				USER = temp;
			}
			
		}
		change = JOptionPane.showConfirmDialog(null, "Do you want to change the PASSWORD from the default?");

		if(change == 0){//if yes then change it
			String temp2= JOptionPane.showInputDialog("Input the password of database.");
			if(temp2 == null){
				PASS = "root";
			}
			else{
				PASS= temp2;
			}
		}
		change = JOptionPane.showConfirmDialog(null, "Do you want to change the JDBC_DRIVER class from the default?");

		if(change == 0){//if yes then change it
			String temp3= JOptionPane.showInputDialog("Input the JDBC Driver Class name.");
			
			if(temp3 == null){
				JDBC_DRIVER = "com.mysql.jdbc.Driver";
			}
			else{
				JDBC_DRIVER = temp3;
			}
		}
		change = JOptionPane.showConfirmDialog(null, "Do you want to change the Database Url from the default?");

		if(change == 0){//if yes then change it
			String temp4= JOptionPane.showInputDialog("Input the Database URL.");			
			if(temp4 == null){
				DB_URL = "jdbc:mysql://localhost:3306/";
			} 
			else{
				DB_URL = temp4;
			}
		}

*/


		//initialize a connection and statement variables
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;


		try{

			//Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);


			//BEGIN DATABASE MODIFICATION
			if (args.length == 2){		//read from the file and put into database from here

				try{

					//output/input files
					if(args[1] == null){
						args[1] = "output.txt";
					}
					Writer outfile = new FileWriter(args[1]);//output file will be the second argument
					Scanner infile = new Scanner(new File(args[0]));

					String tempStr = null;//HOLDS INPUT FILE LINE

					//Log begins
					outfile.write("Connecting to databases...	" + time()  + "\r\n" + "Connection successful!	" + time() + "\r\n");
					
					while(infile.hasNext()){//Continue reading until file is empty
						try{
							tempStr = infile.nextLine();// get the next line
							
							if(tempStr == ""){//if the next line is empty get another, or quit
								if(infile.hasNext()){
									tempStr = infile.next();// get the next line
								}
								else {
									break;
								}
							}
							outfile.write("Writing command.	"+ time() +"\r\n"+ tempStr + "\r\n");//log
							stmt = conn.createStatement();//create a new statement in sql
							String sql = tempStr;
							stmt.executeUpdate(sql);//input line into sql
							outfile.write("Command successfully executed.	"+ time() + "\r\n"+ "\r\n");	
							
							
						}
						catch( Exception e){
							System.out.println("Error:"+ e);
							outfile.write("File reading interrupted at command below.	"+ time()+"\r\n" +tempStr+ "\r\n" );
							break;
						}

					}

					outfile.write("Closing resources.	"+ time() + "\r\n");

					//Check if there is nothing left to be done.
					try{
						if(stmt!=null)
							stmt.close();
					}
					catch(SQLException se2){// No statement?
					}
					try{
						if(conn!=null)
							conn.close();
					}
					catch(SQLException se){// No connection to database?
						se.printStackTrace();

					}


					//CLOSE DATABASE RESOURCES
					stmt.close(); // Throws Null pointer if not initialised
					conn.close();

					//Close read/log files
					infile.close();
					outfile.write("Resources Closed.	"+ time() + "\r\n");
					outfile.close();
				}
				catch( Exception e){
					System.out.println("Error:"+ e);

					//CLOSE DATABASE RESOURCES
					stmt.close(); // Throws Null pointer if not initialised
					conn.close();
				}

			}







			else{ 	//go through manual entry for the database here

				try{
					//write a log into output file
					Writer outfile2 = new FileWriter("output.txt");

					outfile2.write("Connecting to databases...	"+ time() + "\r\n" + "Connection successful!	"+ time() + "\r\n");

					//Continue to work on the database?
					int continu = 0; 
					String Command = null;// Hold the inputed command
					String tempstr = null;
					//handles all inputs to work with the current database
					while(continu == 0){
						try{
							Command = JOptionPane.showInputDialog("Input a command, on the line, with proper mysql syntax.");
							outfile2.write("Writing command.	"+ time() +"\r\n"+ Command + "\r\n");//log
							stmt = conn.createStatement();//create a new statement in sql
							String sql = Command;
							stmt.executeUpdate(sql);//input line into sql
							outfile2.write("Command successfully executed.	"+ time() + "\r\n" + "\r\n");
							
							//Continue to modify database?
							continu = JOptionPane.showConfirmDialog(null, "Select 'Yes' to continue to modify database.");
						}
						catch( Exception e){
							System.out.println("Error:"+ e);
							continu = JOptionPane.showConfirmDialog(null, "Previous command not accepeted. Select 'Yes' to continue to modify database.");

						}
					}


					outfile2.write("Closing resources.	"+ time() + "\r\n");
					
					
					//Check if there is nothing left to be done.
					try{
						if(stmt!=null)
							stmt.close();
					}
					catch(SQLException se2){// No statement
					}
					try{
						if(conn!=null)
							conn.close();
					}
					catch(SQLException se){//No connection
						se.printStackTrace();
					}

					//CLOSE DATABASE RESOURCES
					stmt.close();
					conn.close();
					//Close Log file
					outfile2.write("Resources Closed.	"+ time() + "\r\n");
					outfile2.close();


				}
				catch( Exception e){
					System.out.println("Error:"+ e);
					//CLOSE DATABASE RESOURCES
					stmt.close();
					conn.close();
				}

			}
		}
		catch(Exception e){
			System.out.println("Error:"+ e);

		}
	}
}
