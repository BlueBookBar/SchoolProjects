package Testing;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class dummy2 {


	//A method to return the date_time
	static String date(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy_HH:mm:ss");
		//SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");

		String formattedDate = sdf.format(date);
		return (formattedDate);
	}// end date

	
	public static FileWriter viewFile;//for logs to show when each command was made
	public static FileWriter recoveryFile;//for recovery
	

	static String time(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy_HH:mm:ss");
		String formattedDate = sdf.format(date);
		return ("Time: "+formattedDate);
	}

	public static void main(String[] args) {
		
		JFrame frame1 = new JFrame("test");
		
		
		
		//DO THIS WITH THE SAVE METHOD
		frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame1.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        // PUT SAVE METHOD HERE
		    	System.exit(0);
		    }
		});
		Dimension temp= Toolkit.getDefaultToolkit().getScreenSize();
		int tempwidth=(int)(temp.width/1.5), temphieght= (int)(temp.height/1.5);
		frame1.setSize(tempwidth, temphieght);
		
		JLabel tsd = new JLabel("Inventory");
		tsd.setFont(new Font("Verdana",1,20));
		JLabel tad = new JLabel("Inventory");
		tsd.setFont(new Font("Verdana",1,20));
		
		frame1.setVisible(true);
		frame1.setResizable(true);
		JPanel panel1= new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, temphieght/2, tempwidth/2, temphieght/2);
		
		panel1.add(tsd);
		JPanel panel2= new JPanel();
		panel2.setLayout(null);
		panel2.setBounds( (tempwidth/2)+1, temphieght/2, tempwidth/2, temphieght/2);
		
		
		panel1.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
//		JPanel panel2= new JPanel();
//		JPanel panel3= new JPanel();
//		panel1.setVisible(true);
		
		frame1.add(panel1);
//		frame1.add(panel2);
//		frame1.add(panel3);

		//frame1.setBackground(Color.LIGHT_GRAY);
		
		
		
		
		
		JButton button2 = new JButton("here");
//		panel1.add(button2);
		button2.add(tad);
		panel2.add(button2);
		button2.addActionListener(new Action());
		

		JButton button1 = new JButton();
		button1.add(tsd);
		panel1.add(button1);
		button1.addActionListener(new Action());
		
		JButton button3 = new JButton("Clicke me");
//		panel1.add(button3);
		
		JButton button4 = new JButton("Clicke me");
	//	panel1.add(button4);
		
		JButton button5 = new JButton("Clicke me");
	//	panel1.add(button5);
		button1.setSize(500, 800);
		
//		int a = JOptionPane.showConfirmDialog(null, "Would you like to save your changes?");
//		System.out.println(time());

		
	}

	
	
	
	
	

	
	
	
	
		//ACTION CLASS
	
	static class Action implements ActionListener{
	JFrame frame1 = new JFrame("test2");

	
	
	//
	
	Action(){
		//use this constructor to import connection variable
		//import both filewriters aswell
	}
		public void actionPerformed(ActionEvent e) {
			
			frame1.setVisible(true);
			frame1.setResizable(true);
			Dimension temp= Toolkit.getDefaultToolkit().getScreenSize();
			
			
			frame1.setSize((int)(temp.width/2), (int)(temp.height/2));
		}
		
	}
}
