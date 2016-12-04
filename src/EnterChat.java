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
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
		
		//JFrame and JPanel
		setFont(new Font("Arial", Font.PLAIN, 12));
		setBackground(Color.WHITE);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 401);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//IP Address
		JLabel lblServerAddress = new JLabel("Enter the IP Address of the server");
		lblServerAddress.setFont(new Font("Arial", Font.PLAIN, 11));
		lblServerAddress.setBounds(109, 180, 166, 14);
		contentPane.add(lblServerAddress);
		ip_textField.setBounds(109, 205, 166, 20);
		contentPane.add(ip_textField);
		ip_textField.setColumns(10);
		
		//Username
		JLabel lblUsername = new JLabel("Enter your Username");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 11));
		lblUsername.setBounds(141, 236, 102, 14);
		contentPane.add(lblUsername);
		username_textField.setBounds(109, 261, 166, 20);
		contentPane.add(username_textField);
		username_textField.setColumns(10);
		btnEnter.setFont(new Font("Arial", Font.PLAIN, 11));
		btnEnter.setBounds(150, 292, 83, 23);
		contentPane.add(btnEnter);
		
		//Logo
		JLabel lblDimelolLogo = new JLabel("");
		lblDimelolLogo.setIcon(new ImageIcon("C:\\Users\\LuisJonuel\\git\\DimeLol\\Images\\dimelol_logo.jpg"));
		lblDimelolLogo.setBounds(0, 0, 384, 150);
		contentPane.add(lblDimelolLogo);
	}

	public String getUserName() {
		return username;
	}

	public String getIP() {
		return ip;
	}
}
