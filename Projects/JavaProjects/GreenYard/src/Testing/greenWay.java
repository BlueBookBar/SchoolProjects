package Testing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class greenWay {

	private JFrame MainMenuFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					greenWay window = new greenWay();
					window.MainMenuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public greenWay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainMenuFrame = new JFrame();
		MainMenuFrame.setSize(590,433);
		MainMenuFrame.setBounds(100, 100, 494, 330);

		MainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{117, 0, 0, 0, 0, 0, 0, 166, 0, 117, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{187, 29, 0, 29, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		MainMenuFrame.getContentPane().setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("GreenWay");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 7;
		gbc_titleLabel.gridy = 0;
		MainMenuFrame.getContentPane().add(titleLabel, gbc_titleLabel);
		
		JButton InventoryButton = new JButton("Inventory");
		InventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_InventoryButton = new GridBagConstraints();
		gbc_InventoryButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_InventoryButton.insets = new Insets(0, 0, 5, 5);
		gbc_InventoryButton.gridx = 5;
		gbc_InventoryButton.gridy = 2;
		MainMenuFrame.getContentPane().add(InventoryButton, gbc_InventoryButton);
		
		JButton TransactionButton = new JButton("Transactions");
		GridBagConstraints gbc_TransactionButton = new GridBagConstraints();
		gbc_TransactionButton.insets = new Insets(0, 0, 5, 5);
		gbc_TransactionButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_TransactionButton.gridx = 8;
		gbc_TransactionButton.gridy = 2;
		MainMenuFrame.getContentPane().add(TransactionButton, gbc_TransactionButton);
	}
}
