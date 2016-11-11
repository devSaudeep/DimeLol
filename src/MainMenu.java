import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends JFrame {

	private final int WIDTH = 400, HEIGHT = 400;

	private JPanel mainMenuPanel;
	private JLabel dimeLolLabel, serverIPLabel, usernameLabel;
	private JTextField serverIPTextField, usernameTextField;
	private JButton enterChatButton;

	public MainMenu() {
		setTitle("DimeLol");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildMainMenuPanel();
		getContentPane().add(mainMenuPanel);
		setVisible(true);
	}

	private void buildMainMenuPanel() {
		dimeLolLabel = new JLabel("Welcome to DimeLol!");
		dimeLolLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		dimeLolLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dimeLolLabel.setBounds(10, 8, 364, 27);
		dimeLolLabel.setForeground(Color.GREEN);
		serverIPLabel = new JLabel("Server IP:");
		serverIPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		serverIPLabel.setBounds(10, 46, 364, 14);
		serverIPLabel.setForeground(Color.BLUE);
		usernameLabel = new JLabel("Username:");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(10, 115, 364, 14);
		usernameLabel.setForeground(Color.BLUE);

		serverIPTextField = new JTextField(10);
		serverIPTextField.setBounds(93, 71, 198, 20);
		usernameTextField = new JTextField(10);
		usernameTextField.setBounds(93, 140, 198, 20);

		enterChatButton = new JButton("Enter Chat!");
		enterChatButton.setBounds(93, 171, 198, 23);
		enterChatButton.setBackground(Color.DARK_GRAY);
		enterChatButton.setForeground(Color.GREEN);
		enterChatButton.addActionListener(new EnterChatListener());

		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(null);
		mainMenuPanel.setBackground(Color.black);
		mainMenuPanel.add(dimeLolLabel);
		mainMenuPanel.add(serverIPLabel);
		mainMenuPanel.add(serverIPTextField);
		mainMenuPanel.add(usernameLabel);
		mainMenuPanel.add(usernameTextField);
		mainMenuPanel.add(enterChatButton);
	}

	private class EnterChatListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			setVisible(false);
			String[] args = {"123"};
			String address = serverIPTextField.getText();
			String name = usernameTextField.getText();

			try {
				ChatClient.main(args);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new MainMenu();
	}
}