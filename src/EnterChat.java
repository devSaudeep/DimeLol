import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class EnterChat extends JFrame {

	private JPanel contentPane;
//	private JTextField ip_textField;
//	private JTextField username_textField;
	static String ip;
	static String username;
	
	static JButton btnEnter = new JButton("ENTER");
	static JTextField username_textField = new JTextField();
	static JTextField ip_textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterChat frame = new EnterChat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EnterChat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblServerAddress = new JLabel("Enter the IP Address of the server");
		GridBagConstraints gbc_lblServerAddress = new GridBagConstraints();
		gbc_lblServerAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerAddress.gridx = 4;
		gbc_lblServerAddress.gridy = 3;
		contentPane.add(lblServerAddress, gbc_lblServerAddress);
		
		GridBagConstraints gbc_ip_textField = new GridBagConstraints();
		gbc_ip_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ip_textField.insets = new Insets(0, 0, 5, 5);
		gbc_ip_textField.gridx = 4;
		gbc_ip_textField.gridy = 4;
		contentPane.add(ip_textField, gbc_ip_textField);
		ip_textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Enter your Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 4;
		gbc_lblUsername.gridy = 5;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		
		GridBagConstraints gbc_username_textField = new GridBagConstraints();
		gbc_username_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_username_textField.insets = new Insets(0, 0, 5, 5);
		gbc_username_textField.gridx = 4;
		gbc_username_textField.gridy = 6;
		contentPane.add(username_textField, gbc_username_textField);
		username_textField.setColumns(10);
		
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnter.gridx = 4;
		gbc_btnEnter.gridy = 8;
		contentPane.add(btnEnter, gbc_btnEnter);
	}

	public String getUserName() {
		
		return username;
		
	}

	public String getIP() {
		return ip;
		
	}
}
