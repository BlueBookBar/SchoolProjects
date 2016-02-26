
import java.sql.*;

import javax.swing.JFrame;

import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class GreenFrame {


	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	//Log file variables
	public static FileWriter viewFile;//for logs to show when each command was made
	public static FileWriter recoveryFile;//for recovery

	//Database connection handle
	static Connection conn;


	//Opens a new connection with mysql with the above credentials, autocommit is on
	static void mySQLConnect(){
		conn = null;
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Autocommit is set to false
			conn.setAutoCommit(true);

			createDatabase();
			
			runGUI();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");


		}//end try
	}// end mySQLConnect


	//Takes current mySQL connection
	static void mySQLDisconnect(Statement stmt){
		try {
			conn.close();//close connection
			stmt.close();//close statements
		} 
		catch (SQLException e) {//Handles the errors for closing mySQL resources	
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");

		}
	}//end mySQLDisconnect


	//A METHOD TO RETURN THE TIMESTAMP AS STRING
	static String time(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		return ("Time: "+formattedDate);
	}// end time


	//A method to return the date_time
	static String date(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yyyy");
		String formattedDate = sdf.format(date);
		return (formattedDate);
	}// end date


	//Creates the log file
	//if the log file exists then it will append it to the old one
	static void openLog(){
		try {
			recoveryFile = new FileWriter("GreenLogBackUp"+ date()+".txt",true);
			viewFile = new FileWriter("GreenLog"+ date()+".txt",true);
		} 
		catch (IOException e) {//catches the exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");

		}
	}// end openLog


	//Closes the log file
	static void closeLog(){
		try {
			recoveryFile.close();
			viewFile.close(); 
		} 
		catch (IOException e) {//catches the exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");

		}
	}// end closeLog

	//Will write the passed string to files, 
	//returns true if the writes where successful 
	static boolean writeFile(String command){
		try {
			recoveryFile.write(command+ "\r\n");
			viewFile.write("Writing command.	"+ time() +"\r\n"+ command + "\r\n");
			viewFile.write("Command successfully executed.	"+ time() + "\r\n"+ "\r\n");
			return true;
		} 
		catch (IOException e) {//catch the exception and return false
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");

			return false;
		}
	}// end writeFile

	//RETURNS TRUE IF THE RECOVERYFILE READ THE COMMANDS AND PUT IT THROUGH TO MYSQL
	//Reads the files line by line,
	//A full mysql command must be on each line in the input file
	//Do not break up a command over several lines!!
	static boolean recover(String infile){
		try {
			Scanner file = new Scanner(new File(infile));//Opens the recoveryfile
			String command = null;//holds the line
			Statement stmt = null;//create new statement object
			while(file.hasNextLine()){
				command = file.nextLine();
				if(command==""){
					//Loop again if the string is empty
				}
				else{//if there is a line, input that into mysql
					try {
						stmt = conn.createStatement();
						stmt.executeUpdate(command);
					} 
					catch (SQLException e) {//handles the individual command exceptions
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "SQL Error.");

						return false;
					}
				}
			}
			return true;
		} 
		catch (FileNotFoundException e) {//catches the file exceptions
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");
			return false;
		}

	}// end Recover

	/*
	 * The recovery mode/button should have a message that says
	 * Locate the file that begins with "Greenlogbackup" the date will attached of the .txt file 
	 * Enter the name of the txtfile
	 * For example, enter "GreenLogBackUp08/31/2015.txt"
	 * It will read and reconstruct the database for the date of 8/31/2015 at 15:32:30 
	 * Put the File in the same folder as this .exe file
	 * Hit the button
	 * If no error occurred, the recovery was successful
	 * */



	//Creates the database if it isn't there, catches the exception if the database exists
	//Create database named GreenWayDatabeseAYW
	//Create table inventory
	/* Mysql commands are as follows
	  	CREATE DATABASE GreenWayDatabeseAYW;  
		USE GreenWayDatabeseAYW;
		CREATE TABLE inventory (skuNumb VARCHAR(20) NOT NULL PRIMARY KEY, 
		ItemName VARCHAR(30) NOT NULL, 
		SellingPrice float(7),
		BuyingPrice float(7),
		Quantity INT(10), 
		Sellers VARCHAR(30), 
		Buyers VARCHAR(20)); */
	static void createDatabase(){
		Statement stmt= null;
		String command = null;
		try {
			stmt = conn.createStatement();
			command= "CREATE DATABASE GreenWayDatabeseAYW;  ";
			writeFile(command);
			stmt.executeUpdate(command);			
			stmt = conn.createStatement();
			command= "USE GreenWayDatabeseAYW; ";
			writeFile(command);
			stmt.executeUpdate(command);			
			stmt = conn.createStatement();
			command= "CREATE TABLE inventory (skuNumb VARCHAR(20) NOT NULL PRIMARY KEY, ItemName VARCHAR(30) NOT NULL, SellingPrice VARCHAR(7), BuyingPrice VARCHAR(7), Quantity VARCHAR(10)); ";
			writeFile(command);
			stmt.executeUpdate(command);
			closeLog();
		} catch (SQLException e) {//handles the individual command exceptions
			e.printStackTrace();

		}
	}







	//The main function will connect the LOGFILE, MYSQLCONNECTION, GUI together and run them
	public static void main(String[] args) {
		
			openLog();
			mySQLConnect();
			closeLog();

	}


	//USE THIS METHOD TO INVOKE THE GUI METHOD
	public static void runGUI(){
		EventQueue.invokeLater(new Runnable() {
			//A new RUnnable object leave the method uninterrupted by the rest of the program
			public void run() {
				try {
					theGUI();// Runs the GUI Method
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error.");
				}
			}
		});
	}


	//Handles the GUI 
	//Creates the Frame and panels
	//The connection is remade in this due to visibility problems with the first panel's labels and buttons
	private static void theGUI() {
		

		try {
			recoveryFile = new FileWriter("GreenLogBackUp"+ date()+".txt",true);
			viewFile = new FileWriter("GreenLog"+ date()+".txt",true);

		} catch (IOException e3) {
			e3.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");
		}
		

		try {
			//The connection is remade
			 conn = DriverManager.getConnection(DB_URL, USER, PASS);
				
				String command =  "USE GreenWayDatabeseAYW; ";

				Statement stmt= null;
				stmt = conn.createStatement();
				stmt.executeUpdate(command);
				//Write the command in the files
				try {
					recoveryFile.write(command+ "\r\n");
					viewFile.write("Writing command.	"+ time() +"\r\n"+ command + "\r\n");
					viewFile.write("Command successfully executed.	"+ time() + "\r\n"+ "\r\n");
				} 
				catch (IOException e) {//catch the exception and return false
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error.");

				}
				
		} 
		catch (SQLException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error.");

		}




		//Initialize a new frame-------------------------------------
		//Visible, not resizable, with a card layout

		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 589, 403);
		frame.getContentPane().setLayout(new CardLayout(0, 0));




		//It will exit on close.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		//Initialize panels for use
		//Null layout
		//Different colors


		//Main Menu Panel-------------------------------------
		//Panel is colored, layout is set to null and then added to frame
		
		
		final JPanel MainMenuPanel = new JPanel();
		MainMenuPanel.setBackground(new Color(152, 251, 152));
		frame.getContentPane().add(MainMenuPanel, "name_1438519426436656000");
		MainMenuPanel.setLayout(null);
		MainMenuPanel.setVisible(true);

		//Recovery Panel-------------------------------------
		
		final JPanel RecoveryPanel = new JPanel();
		frame.getContentPane().add(RecoveryPanel, "name_1438565506229809000");
		RecoveryPanel.setLayout(null);
		

		// Inventory panel is accessed by Main Menu Panel-------------------------------------
		//Panel is colored, layout is set to null and then added to frame
		
		
		final JPanel InventoryPanel = new JPanel();
		InventoryPanel.setBackground(new Color(250, 235, 215));
		frame.getContentPane().add(InventoryPanel, "name_1438519429184646000");
		InventoryPanel.setLayout(null);


		//SearchInventory Panel is accessed by Inventory Panel-------------------------------------
		//Panel is colored, layout is set to null and then added to frame
		
		
		final JPanel SearchInventoryPanel = new JPanel();
		SearchInventoryPanel.setBackground(new Color(255, 250, 205));
		frame.getContentPane().add(SearchInventoryPanel, "name_1438528106488888000");
		SearchInventoryPanel.setLayout(null);


		// AddInventory Panel is accessed by Inventory Panel-------------------------------------
		//Panel is colored, layout is set to null and then added to frame
		
		
		final JPanel AddInventoryPanel = new JPanel();
		AddInventoryPanel.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(AddInventoryPanel, "name_1438528113758920000");
		AddInventoryPanel.setLayout(null);

		//Initialize TextArea of RecoveryPanel Panel-------------------------------------
		
		JTextArea txtrlocateTheFile = new JTextArea();
		txtrlocateTheFile.setText("-Locate the file that begins with \"Greenlogbackup\" the date will attached of \nthe .txt file \n-Put the File in the same folder as this .exe file\n-Hit the button\n-Enter the name of the txtfile \n-For example, enter \"GreenLogBackUp08/31/2015.txt\"\n-It will read and reconstruct the database for the date of 8/31/2015 at 15:32:30 \n-If no error occurred, the recovery was successful");
		txtrlocateTheFile.setBounds(34, 76, 509, 141);
		RecoveryPanel.add(txtrlocateTheFile);
		
		
		//Initialize Textfields of SearchInventory Panel-------------------------------------
		
		
		final JTextField textFieldSKUInventory;
		final JTextField textFieldNameInventory;
		final JTextField textFieldQuantityInventory;
		final JTextField textFieldBuyPriceInventory;
		final JTextField textFieldSellPriceInventory;

		//Instances of TextFields in SearchInventory Panel-------------------------------------
		//set sizes and add them to the panel
		
		
		textFieldSKUInventory = new JTextField();
		textFieldSKUInventory.setBounds(6, 102, 288, 28);
		SearchInventoryPanel.add(textFieldSKUInventory);
		textFieldSKUInventory.setColumns(10);

		textFieldNameInventory = new JTextField();
		textFieldNameInventory.setBounds(6, 246, 257, 28);
		SearchInventoryPanel.add(textFieldNameInventory);
		textFieldNameInventory.setColumns(10);


		textFieldQuantityInventory = new JTextField();
		textFieldQuantityInventory.setBounds(6, 314, 257, 28);
		SearchInventoryPanel.add(textFieldQuantityInventory);
		textFieldQuantityInventory.setColumns(10);

		textFieldBuyPriceInventory = new JTextField();
		textFieldBuyPriceInventory.setBounds(306, 246, 245, 28);
		SearchInventoryPanel.add(textFieldBuyPriceInventory);
		textFieldBuyPriceInventory.setColumns(10);

		textFieldSellPriceInventory = new JTextField();
		textFieldSellPriceInventory.setBounds(308, 314, 245, 28);
		SearchInventoryPanel.add(textFieldSellPriceInventory);
		textFieldSellPriceInventory.setColumns(10);


		//Initialize Textfields of ADDInventory Panel-------------------------------------
		//set sizes and add them to the panel

		final JTextField textFieldSKUAddInventory;
		final JTextField textFieldNameAddInventory;
		final JTextField textFieldBuyPriceAddInventory;
		final JTextField textFieldSellPriceAddInventory;
		final JTextField textFieldQuantityAddInventory;

		//Instances of TextFields in AddInventory Panel-------------------------------------


		textFieldSKUAddInventory = new JTextField();
		textFieldSKUAddInventory.setColumns(10);
		textFieldSKUAddInventory.setBounds(6, 151, 257, 28);
		AddInventoryPanel.add(textFieldSKUAddInventory);

		textFieldNameAddInventory = new JTextField();
		textFieldNameAddInventory.setColumns(10);
		textFieldNameAddInventory.setBounds(6, 220, 257, 28);
		AddInventoryPanel.add(textFieldNameAddInventory);

		textFieldBuyPriceAddInventory = new JTextField();
		textFieldBuyPriceAddInventory.setColumns(10);
		textFieldBuyPriceAddInventory.setBounds(326, 151, 257, 28);
		AddInventoryPanel.add(textFieldBuyPriceAddInventory);

		textFieldSellPriceAddInventory = new JTextField();
		textFieldSellPriceAddInventory.setColumns(10);
		textFieldSellPriceAddInventory.setBounds(326, 220, 257, 28);
		AddInventoryPanel.add(textFieldSellPriceAddInventory);

		textFieldQuantityAddInventory = new JTextField();
		textFieldQuantityAddInventory.setColumns(10);
		textFieldQuantityAddInventory.setBounds(6, 287, 257, 28);
		AddInventoryPanel.add(textFieldQuantityAddInventory);



		//JLabel of Main Menu Panel-------------------------------------
		
		
		JLabel TitleLabel = new JLabel("GreenWay");
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setBounds(155, 45, 264, 115);
		TitleLabel.setFont(new Font("PT Sans Caption", Font.PLAIN, 32));
		MainMenuPanel.add(TitleLabel);
		TitleLabel.setVisible(true);

		
		//JLabel of Recovery Panel-------------------------------------

		JLabel RecoveryTitleLabel = new JLabel("Recovery");
		RecoveryTitleLabel.setBounds(267, 6, 61, 16);
		RecoveryPanel.add(RecoveryTitleLabel);
		
		
		//JLabel of Inventory Panel-------------------------------------
		
		
		JLabel InventoryTitleLabel = new JLabel("Inventory");
		InventoryTitleLabel.setBounds(261, 6, 61, 16);
		InventoryPanel.add(InventoryTitleLabel);


		//JLabels of SearchInventory Panel-------------------------------------
		
		
		JLabel SearchInventoryTitleLabel = new JLabel("Search");
		SearchInventoryTitleLabel.setBounds(252, 6, 61, 16);
		SearchInventoryPanel.add(SearchInventoryTitleLabel);

		JLabel SearchInventoryQuantityLabel = new JLabel("Total Quantity in Stock");
		SearchInventoryQuantityLabel.setBounds(6, 286, 152, 16);
		SearchInventoryPanel.add(SearchInventoryQuantityLabel);

		JLabel SearchInventoryBuyPriceLabel = new JLabel("Price Bought For per Unit");
		SearchInventoryBuyPriceLabel.setBounds(306, 218, 165, 16);
		SearchInventoryPanel.add(SearchInventoryBuyPriceLabel);

		JLabel SearchInventorySellPriceLabel = new JLabel("Sell Price per Unit");
		SearchInventorySellPriceLabel.setBounds(306, 286, 165, 16);
		SearchInventoryPanel.add(SearchInventorySellPriceLabel);


		JLabel SearchInventorySKULabel = new JLabel("SKU");
		SearchInventorySKULabel.setBounds(6, 74, 61, 16);
		SearchInventoryPanel.add(SearchInventorySKULabel);

		JLabel SearchInventoryNameLabel = new JLabel("Name");
		SearchInventoryNameLabel.setBounds(6, 218, 61, 16);
		SearchInventoryPanel.add(SearchInventoryNameLabel);





		//JLabels of ADDInventory-------------------------------------


		JLabel AddSKULabel = new JLabel("SKU");
		AddSKULabel.setBounds(6, 123, 61, 16);
		AddInventoryPanel.add(AddSKULabel);

		JLabel AddNameLabel = new JLabel("Name");
		AddNameLabel.setBounds(6, 192, 61, 16);
		AddInventoryPanel.add(AddNameLabel);

		JLabel AddQuantityLabel = new JLabel("Quantity");
		AddQuantityLabel.setBounds(6, 260, 61, 16);
		AddInventoryPanel.add(AddQuantityLabel);

		JLabel AddSellPriceLabel = new JLabel("Sell Price");
		AddSellPriceLabel.setBounds(326, 192, 61, 16);
		AddInventoryPanel.add(AddSellPriceLabel);

		JLabel AddBuyPriceLabel = new JLabel("Buy Price");
		AddBuyPriceLabel.setBounds(326, 123, 61, 16);
		AddInventoryPanel.add(AddBuyPriceLabel);

		JLabel AddInventoryTitleLabel = new JLabel("Add New Item");
		AddInventoryTitleLabel.setBounds(254, 6, 102, 16);
		AddInventoryPanel.add(AddInventoryTitleLabel);



		//Jbuttons of Main Menu Panel-------------------------------------

		JButton InventoryButtonMainMenu = new JButton("Inventory");
		InventoryButtonMainMenu.setBackground(new Color(222, 184, 135));
		InventoryButtonMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuPanel.setVisible(false);
				InventoryPanel.setVisible(true);
			}
		});
		InventoryButtonMainMenu.setBounds(54, 238, 125, 62);
		MainMenuPanel.add(InventoryButtonMainMenu);
		InventoryButtonMainMenu.setVisible(true);

		
		JButton MainMenuRecoveryButton = new JButton("Recover");
		MainMenuRecoveryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel.setVisible(false);
				RecoveryPanel.setVisible(true);
			}
		});
		MainMenuRecoveryButton.setBounds(391, 238, 117, 62);
		MainMenuPanel.add(MainMenuRecoveryButton);


		//Jbuttons of Recovery Panel-------------------------------------

		JButton RecoveryMainMenuButton = new JButton("Back to Main Menu");
		RecoveryMainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecoveryPanel.setVisible(false);
				MainMenuPanel.setVisible(true);
			}
		});
		RecoveryMainMenuButton.setBounds(6, 1, 130, 61);
		RecoveryPanel.add(RecoveryMainMenuButton);
		
		//Opens a GUI to input the filename, Searches for the file in the same folder as .exe
		//Takes the code line by line to reconstruct the data

		JButton RecoveryPanelRecoverButton = new JButton("Recover");
		RecoveryPanelRecoverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName= JOptionPane.showInputDialog("Enter recovery file name.");
				recover(fileName);
			}
		});
		RecoveryPanelRecoverButton.setBounds(238, 259, 130, 49);
		RecoveryPanel.add(RecoveryPanelRecoverButton);
		
		
		//Jbuttons of Inventory Panel-------------------------------------

		
		
		JButton InventoryButtonBack = new JButton("Back To Main Menu");
		InventoryButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryPanel.setVisible(false);
				MainMenuPanel.setVisible(true);

			}
		});
		InventoryButtonBack.setBounds(6, 6, 143, 48);
		InventoryPanel.add(InventoryButtonBack);



		JButton InventoryNewInventoryButton = new JButton("Add New Inventory");
		InventoryNewInventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryPanel.setVisible(false);
				AddInventoryPanel.setVisible(true);
			}
		});
		InventoryNewInventoryButton.setBounds(67, 262, 163, 48);
		InventoryPanel.add(InventoryNewInventoryButton);


		
		JButton InventorySearchExisting = new JButton("Search Inventory");
		InventorySearchExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryPanel.setVisible(false);
				SearchInventoryPanel.setVisible(true);

			}
		});
		InventorySearchExisting.setBounds(363, 262, 143, 48);
		InventoryPanel.add(InventorySearchExisting);


		//Jbuttons of AddInventory Panel-------------------------------------

		JButton AddInventoryMainMenuButton = new JButton("Back to Main Menu");
		AddInventoryMainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddInventoryPanel.setVisible(false);
				MainMenuPanel.setVisible(true);
			}
		});
		AddInventoryMainMenuButton.setBounds(6, 6, 130, 61);
		AddInventoryPanel.add(AddInventoryMainMenuButton);

		
		
		JButton AddinventoryBackButton = new JButton("Back");
		AddinventoryBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddInventoryPanel.setVisible(false);
				InventoryPanel.setVisible(true);
			}
		});
		AddinventoryBackButton.setBounds(466, 6, 117, 61);
		AddInventoryPanel.add(AddinventoryBackButton);





		JButton AddinventoryAddButton = new JButton("Add");
		AddinventoryAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command="INSERT INTO inventory VALUES ('" + textFieldSKUAddInventory.getText() + "','" + textFieldNameAddInventory.getText() + "','" +textFieldSellPriceAddInventory.getText() + "','" +textFieldBuyPriceAddInventory.getText() + "','" +  textFieldQuantityAddInventory.getText() + "');";
				
				Statement stmt;
				try {
					stmt = conn.createStatement();	
					stmt.executeUpdate(command);
					
					//Write the command in the files
					try {
						recoveryFile.write(command+ "\r\n");
						viewFile.write("Writing command.	"+ time() +"\r\n"+ command + "\r\n");
						viewFile.write("Command successfully executed.	"+ time() + "\r\n"+ "\r\n");
					} 
					catch (IOException e1) {//catch the exception and return false
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error.");

					}
					
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error.");

				}

				
				
			}
		});
		AddinventoryAddButton.setBounds(340, 288, 117, 29);
		AddInventoryPanel.add(AddinventoryAddButton);

		
		
		//Jbuttons of SearchInventory Panel-------------------------------------
		
		
		
		
		JButton SearchInventorySearchButton = new JButton("Search");
		SearchInventorySearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputSku= "SELECT * FROM inventory WHERE skuNumb = "+textFieldSKUInventory.getText()+ " ;";
				try {
					Statement stmt = null;
					stmt = conn.createStatement();
					
					
					
					//Write the command in the files
					try {
						recoveryFile.write("NCISBCSLUVYBSKCBSCKSBDC \r\n");
						recoveryFile.write(inputSku+ "\r\n");
						viewFile.write("Writing command.	"+ time() +"\r\n"+ inputSku + "\r\n");
						viewFile.write("Command successfully executed.	"+ time() + "\r\n"+ "\r\n");
					} 
					catch (IOException e1) {//catch the exception and return false
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error.");

					}
					
					ResultSet resultSku= stmt.executeQuery(inputSku);
					resultSku.next();
					
					if(resultSku.getString(2)!= null){
						textFieldNameInventory.setText(resultSku.getString(2));
					}
					else{
						textFieldNameInventory.setText("");
					}
					if(resultSku.getString(5)!= null){
						textFieldQuantityInventory.setText(resultSku.getString(5));
					}
					else{
						textFieldQuantityInventory.setText("");
					}					
					if(resultSku.getString(4)!= null){
						textFieldBuyPriceInventory.setText(resultSku.getString(4));
					}
					else{
						textFieldBuyPriceInventory.setText("");
					}					
					if(resultSku.getString(3)!= null){
						textFieldSellPriceInventory.setText(resultSku.getString(3));
					}
					else{
						textFieldSellPriceInventory.setText("");
					}
						
					
					
													
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error.");

				}
			}
		});
		SearchInventorySearchButton.setBounds(306, 103, 117, 29);
		SearchInventoryPanel.add(SearchInventorySearchButton);

		
		JButton SearchInventoryMenuButton = new JButton("Back to Main Menu");
		SearchInventoryMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchInventoryPanel.setVisible(false);
				MainMenuPanel.setVisible(true);
			}
		});
		SearchInventoryMenuButton.setBounds(6, 1, 130, 61);
		SearchInventoryPanel.add(SearchInventoryMenuButton);

		
		JButton SearchInventoryBackButton = new JButton("Back");
		SearchInventoryBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchInventoryPanel.setVisible(false);
				InventoryPanel.setVisible(true);

			}
		});
		SearchInventoryBackButton.setBounds(466, 1, 117, 61);
		SearchInventoryPanel.add(SearchInventoryBackButton);




		JButton SearchInventoryUpdateButton = new JButton("Update");
		SearchInventoryUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String command="DELETE FROM inventory WHERE skuNumb = '"+textFieldSKUInventory.getText()+"';";
					Statement stmt = null;
					
					//Write the command in the files
					try {
						recoveryFile.write(command+ "\r\n");
						viewFile.write("Writing command.	"+ time() +"\r\n"+ command + "\r\n");
						viewFile.write("Command successfully executed.	"+ time() + "\r\n"+ "\r\n");
					} 
					catch (IOException e1) {//catch the exception and return false
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error.");

					}
					
					
					stmt = conn.createStatement();
					stmt.executeUpdate(command);
					command="INSERT INTO inventory VALUES ( '" +  textFieldSKUInventory.getText() + "' , '" + textFieldNameInventory.getText() + "' , '" + textFieldSellPriceInventory.getText() + "' , '" + textFieldBuyPriceInventory.getText() + "' , '" + textFieldQuantityInventory.getText() + "' );";
					
					
					//Write the command in the files
					try {
						recoveryFile.write(command+ "\r\n");
						viewFile.write("Writing command.	"+ time() +"\r\n"+ command + "\r\n");
						viewFile.write("Command successfully executed.	"+ time() + "\r\n"+ "\r\n");
					} 
					catch (IOException e1) {//catch the exception and return false
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error.");

					}
					
					
					stmt = conn.createStatement();
					stmt.executeUpdate(command);
					
					
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error.");

				}	
			}
		});
		SearchInventoryUpdateButton.setBounds(227, 346, 117, 29);
		SearchInventoryPanel.add(SearchInventoryUpdateButton);

		
		
		
		
		JButton SearchInventoryDeleteButton = new JButton("Delete");
		SearchInventoryDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sameSku = textFieldSKUInventory.getText();
				String command="DELETE FROM inventory WHERE skuNumb = '"+sameSku+"';";
				Statement stmt = null;
				try {
					stmt = conn.createStatement();
					stmt.executeUpdate(command);
					
					//Write the command in the files
					try {
						recoveryFile.write(command+ "\r\n");
						viewFile.write("Writing command.	"+ time() +"\r\n"+ command + "\r\n");
						viewFile.write("Command successfully executed.	"+ time() + "\r\n"+ "\r\n");
					} 
					catch (IOException e1) {//catch the exception and return false
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error.");

					}
					
					
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error.");

				}
				
			}
		});
		SearchInventoryDeleteButton.setBounds(466, 346, 117, 29);
		SearchInventoryPanel.add(SearchInventoryDeleteButton);


	}
}
