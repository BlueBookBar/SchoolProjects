import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class TTTGUI {

	private int c = 0;
	private JFrame bb = new JFrame("Tic Tac Toe");
	private JFrame ee = new JFrame();
	private JPanel cc = new JPanel();
	private JPanel ff = new JPanel();
	private JLabel gg = new JLabel("Do you want to play Tic-Tac-Toe?");
	private JLabel hh = new JLabel("You aren't supposed to see this yet...");
	private JButton button11 = new JButton("Yes");
	private JButton button10 = new JButton("No");
	private JButton button1 = new JButton("1");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("3");
	private JButton button4 = new JButton("4");
	private JButton button5 = new JButton("5");
	private JButton button6 = new JButton("6");
	private JButton button7 = new JButton("7");
	private JButton button8 = new JButton("8");
	private JButton button9 = new JButton("9");
	


	
	public TTTGUI(){//constructor
		tictac();// introduces frames to be used
		
		
	}
	
	
	
	
	
	
	
	
	
	public void add(JButton a){//checks and adds the Character to the button NOT DONE
		if(c%2 == 1){
			
			a.setText("X");
			hh.setText("Player O's turn");
			}
			else{
				a.setText("O");
				hh.setText("Player X's turn");
			}
			c++;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void tictac(){// method(function) that created GUI
		bb.setVisible(false);
		bb.setSize(300, 300);
		bb.add(cc);
		bb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bb.add(hh, BorderLayout.NORTH);
		ee.setVisible(true);
		ee.add(gg, BorderLayout.NORTH);
		ee.add(ff);
		ee.setSize(300, 75);
		ee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ff.add(button11);
		ff.add(button10);
		cc.setLayout(new GridLayout(3,3));
		cc.add(button1);
		cc.add(button2);
		cc.add(button3);
		cc.add(button4);
		cc.add(button5);
		cc.add(button6);
		cc.add(button7);
		cc.add(button8);
		cc.add(button9);
		
		Listener listen = new Listener();
		button11.addActionListener(listen);
		button1.addActionListener(listen);
		button2.addActionListener(listen);
		button3.addActionListener(listen);
		button4.addActionListener(listen);
		button5.addActionListener(listen);
		button6.addActionListener(listen);
		button7.addActionListener(listen);
		button8.addActionListener(listen);
		button9.addActionListener(listen);
		
		
	}
	
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == button11){
				ee.setVisible(false);
				bb.setVisible(true);
				hh.setText("Player X goes first!");
				c = 1;
			}
			else{
			add((JButton) e.getSource());
			}
//Check function missing!!!!
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args){
		
		new TTTGUI();
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}