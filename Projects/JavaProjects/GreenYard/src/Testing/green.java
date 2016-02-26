package Testing;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class green {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					green window = new green();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public green() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {







		//Get the dimensions of the PC using this program
		//Put the dimensions in two varibales for the width and height
		Dimension temp= Toolkit.getDefaultToolkit().getScreenSize();
		int FrameWidth = (int)(temp.width/1.5);
		int FrameHeight = (int)(temp.height/1.5);


		//Initialize a new frame
		//Visible, not resizable, with a card layout

		frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 589, 403);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		//	frame.setBounds(temp.width/6, temp.height/6,FrameWidth, FrameHeight);



		//It will not exit on close, but will prompt a save message and then close
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// PUT SAVE METHOD HERE
				System.exit(0);
			}
		});



		//Initialize panels for use
		//Null layout
		//Different colors


		//Main Menu Panel
		//Panel is colored, layout is set to null and then added to frame
		final JPanel MainMenuPanel = new JPanel();
		MainMenuPanel.setBackground(new Color(152, 251, 152));
		frame.getContentPane().add(MainMenuPanel, "name_1438519426436656000");
		MainMenuPanel.setLayout(null);
		MainMenuPanel.setVisible(true);


		// Inventory panel is accessed by Main Menu Panel
		//Panel is colored, layout is set to null and then added to frame
		final JPanel InventoryPanel = new JPanel();
		InventoryPanel.setBackground(new Color(250, 235, 215));
		frame.getContentPane().add(InventoryPanel, "name_1438519429184646000");
		InventoryPanel.setLayout(null);


		//SearchInventory Panel is accessed by Inventory Panel
		//Panel is colored, layout is set to null and then added to frame
		final JPanel SearchInventoryPanel = new JPanel();
		SearchInventoryPanel.setBackground(new Color(255, 250, 205));
		frame.getContentPane().add(SearchInventoryPanel, "name_1438528106488888000");
		SearchInventoryPanel.setLayout(null);


		// AddInventory Panel is accessed by Inventory Panel
		//Panel is colored, layout is set to null and then added to frame
		final JPanel AddInventoryPanel = new JPanel();
		AddInventoryPanel.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(AddInventoryPanel, "name_1438528113758920000");
		AddInventoryPanel.setLayout(null);


		//Initialize Textfields of SearchInventory Panel
		JTextField textFieldSKUInventory;
		JTextField textFieldNameInventory;
		JTextField textFieldQuantityInventory;
		JTextField textFieldBuyPriceInventory;
		JTextField textFieldSellPriceInventory;

		//Instances of TextFields in SearchInventory Panel
		//set sizes and add them to the panel
		textFieldSKUInventory = new JTextField();
		textFieldSKUInventory.setBounds(6, 102, 288, 28);
		SearchInventoryPanel.add(textFieldSKUInventory);
		textFieldSKUInventory.setColumns(10);

		textFieldNameInventory = new JTextField();
		textFieldNameInventory.setBounds(6, 246, 257, 28);
		SearchInventoryPanel.add(textFieldNameInventory);
		textFieldNameInventory.setColumns(10);
		//textField_1.getText()

		textFieldQuantityInventory = new JTextField();
		textFieldQuantityInventory.setBounds(6, 314, 257, 28);
		SearchInventoryPanel.add(textFieldQuantityInventory);
		textFieldQuantityInventory.setColumns(10);

		textFieldBuyPriceInventory = new JTextField();
		textFieldBuyPriceInventory.setBounds(308, 246, 245, 28);
		SearchInventoryPanel.add(textFieldBuyPriceInventory);
		textFieldBuyPriceInventory.setColumns(10);

		textFieldSellPriceInventory = new JTextField();
		textFieldSellPriceInventory.setBounds(308, 314, 245, 28);
		SearchInventoryPanel.add(textFieldSellPriceInventory);
		textFieldSellPriceInventory.setColumns(10);


		//Initialize Textfields of ADDInventory Panel
		//set sizes and add them to the panel

		JTextField textFieldSKUAddInventory;
		JTextField textFieldNameAddInventory;
		JTextField textFieldBuyPriceAddInventory;
		JTextField textFieldSellPriceAddInventory;
		JTextField textFieldQuantityAddInventory;

		//Instances of TextFields in AddInventory Panel


		textFieldSKUAddInventory = new JTextField();
		textFieldSKUAddInventory.setColumns(10);
		textFieldSKUAddInventory.setBounds(37, 151, 257, 28);
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



		//JLabel of Main Menu Panel
		JLabel TitleLabel = new JLabel("GreenWay");
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setBounds(155, 45, 264, 115);
		//			TitleLabel.setBounds((int)(FrameWidth/2.6), (int)(FrameHeight/20), (int)(FrameWidth/1.5), (int)(FrameHeight/1.5));
		TitleLabel.setFont(new Font("PT Sans Caption", Font.PLAIN, 32));
		MainMenuPanel.add(TitleLabel);


		//JLabel of Inventory Panel
		JLabel InventoryTitleLabel = new JLabel("Inventory");
		InventoryTitleLabel.setBounds(261, 6, 61, 16);
		InventoryPanel.add(InventoryTitleLabel);


		//JLabels of SearchInventory Panel
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



		//Transaction Stuff
		//------------------------------------------------------------------------------------------
/*
		final JPanel TransactionPanel = new JPanel();
		frame.getContentPane().add(TransactionPanel, "name_1438519435354618000");
		TransactionPanel.setLayout(null);

		JButton button_4 = new JButton("Back");
		button_4.setBounds(6, 6, 117, 61);
		TransactionPanel.add(button_4);

		JLabel TransactionTitleLabel = new JLabel("Transactions");
		TransactionTitleLabel.setBounds(272, 6, 93, 16);
		TransactionPanel.add(TransactionTitleLabel);
		
		//Jbuttons of 
		
		JPanel TransactionTotalPanel = new JPanel();
		frame.getContentPane().add(TransactionTotalPanel, "name_1438528119289009000");
		TransactionTotalPanel.setLayout(null);

		JButton button_2 = new JButton("Back to Main Menu");
		button_2.setBounds(6, 6, 130, 61);
		TransactionTotalPanel.add(button_2);

		JButton button_3 = new JButton("Back");
		button_3.setBounds(466, 6, 117, 61);
		TransactionTotalPanel.add(button_3);
		
		*/
		//------------------------------------------------------------------------------------------

		//JLabels of ADDInventory


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



		//Jbuttons of Main Menu Panel

		JButton InventoryButtonMainMenu = new JButton("Inventory");
		InventoryButtonMainMenu.setBackground(new Color(222, 184, 135));
		InventoryButtonMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuPanel.setVisible(false);
				InventoryPanel.setVisible(true);
			}
		});
		InventoryButtonMainMenu.setBounds(54, 238, 125, 62);
		//		InventoryButtonMainMenu.setBounds((int) (FrameWidth/1.5), (int)(FrameHeight/1.5), FrameWidth/6, FrameHeight/6);
		MainMenuPanel.add(InventoryButtonMainMenu);
		


		/*

		JButton TransactionButtonMainMenu = new JButton("Transactions");
		TransactionButtonMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel.setVisible(false);
				TransactionPanel.setVisible(true);
			}
		});
		
		
		TransactionButtonMainMenu.setBounds(425, 222, 125, 63);
		//		TransactionButtonMainMenu.setBounds((int) (FrameWidth/6), (int)(FrameHeight/1.5), FrameWidth/6, FrameHeight/6);
		MainMenuPanel.add(TransactionButtonMainMenu);

		 */
		
		
		//Jbuttons of Inventory Panel

		//Done
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


		//Jbuttons of AddInventory Panel

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
			}
		});
		AddinventoryAddButton.setBounds(340, 288, 117, 29);
		AddInventoryPanel.add(AddinventoryAddButton);

		//Jbuttons of SearchInventory Panel

		JButton SearchInventorySearchButton = new JButton("Search");
		SearchInventorySearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SEARCH METHOD HERE
				//MYSQL SEARCH BY SKU
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
				//TAKE THE INFO FROM THE TEXT SAPCES AND PUT THEM INTO MYSQL
			}
		});
		SearchInventoryUpdateButton.setBounds(227, 346, 117, 29);
		SearchInventoryPanel.add(SearchInventoryUpdateButton);

		JButton SearchInventoryDeleteButton = new JButton("Delete");
		SearchInventoryDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//HERE WE WILL DROP THE SEARCHED ROW
			}
		});
		SearchInventoryDeleteButton.setBounds(466, 346, 117, 29);
		SearchInventoryPanel.add(SearchInventoryDeleteButton);
		
		//--------------------------------------------------------------------------------
		
		final JPanel RecoveryPanel = new JPanel();
		frame.getContentPane().add(RecoveryPanel, "name_1438565506229809000");
		RecoveryPanel.setLayout(null);
		
		
		JButton MainMenuRecoveryButton = new JButton("Recover");
		MainMenuRecoveryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel.setVisible(false);
				RecoveryPanel.setVisible(true);
			}
		});
		MainMenuRecoveryButton.setBounds(391, 238, 117, 62);
		MainMenuPanel.add(MainMenuRecoveryButton);
		
		

		
		JLabel RecoveryTitleLabel = new JLabel("Recovery");
		RecoveryTitleLabel.setBounds(267, 6, 61, 16);
		RecoveryPanel.add(RecoveryTitleLabel);
		
		JTextArea txtrlocateTheFile = new JTextArea();
		txtrlocateTheFile.setText("-Locate the file that begins with \"Greenlogbackup\" the date will attached of \nthe .txt file \n-Put the File in the same folder as this .exe file\n-Hit the button\n-Enter the name of the txtfile \n-For example, enter \"GreenLogBackUp08/31/2015.txt\"\n-It will read and reconstruct the database for the date of 8/31/2015 at 15:32:30 \n-If no error occurred, the recovery was successful");
		txtrlocateTheFile.setBounds(34, 76, 509, 141);
		RecoveryPanel.add(txtrlocateTheFile);
		
		JButton RecoveryMainMenuButton = new JButton("Back to Main Menu");
		RecoveryMainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecoveryPanel.setVisible(false);
				MainMenuPanel.setVisible(true);
			}
		});
		RecoveryMainMenuButton.setBounds(6, 1, 130, 61);
		RecoveryPanel.add(RecoveryMainMenuButton);
		
		JButton RecoveryPanelRecoverButton = new JButton("Recover");
		RecoveryPanelRecoverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PUT IT HERE
			}
		});
		RecoveryPanelRecoverButton.setBounds(238, 259, 130, 49);
		RecoveryPanel.add(RecoveryPanelRecoverButton);


	}
}
