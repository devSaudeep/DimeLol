import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChatClient {

	static BufferedReader in;
	static PrintWriter out;
	public static JFrame frame = new JFrame("DimeloL");
	static JTextField textField = new JTextField(40);
	static JTextArea messageArea = new JTextArea(8, 40);
	static EnterChat enterChat = new EnterChat();
	private JPanel contentPane;
	private ChatServer serv;
	private JButton btnEnter;
	private Socket socket;

	/**
	 * Constructs the client by laying out the GUI and registering a
	 * listener with the textfield so that pressing Return in the
	 * listener sends the textfield contents to the server.  Note
	 * however that the textfield is initially NOT editable, and
	 * only becomes editable AFTER the client receives the NAMEACCEPTED
	 * message from the server.
	 * @param caller 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public ChatClient() throws IOException{
		// Layout GUI

		textField.setEditable(false);
		messageArea.setEditable(false);
		messageArea.setForeground(Color.WHITE);
		messageArea.setBackground(new Color(37,211,102));
		messageArea.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));

		//create a JPanel for all the components
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(textField, "South");
		contentPane.add(new JScrollPane(messageArea), "Center");
		//		frame.getContentPane().add(textField, "South");
		//		frame.getContentPane().add(new JScrollPane(messageArea), "Center");
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.pack();

		// Add Listeners
		textField.addActionListener(new ActionListener() {
			/**
			 * Responds to pressing the enter key in the textfield by sending
			 * the contents of the text field to the server.    Then clear
			 * the text area in preparation for the next message.
			 */
			public void actionPerformed(ActionEvent e) {
				out.println(textField.getText());
				textField.setText("");
			}
		});
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


		
		enterChat.setVisible(true);
		enterChat.btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enterChat.username = enterChat.username_textField.getText();
				enterChat.ip = enterChat.ip_textField.getText();

				enterChat.setVisible(false);
				enterChat.dispose();
				frame.setVisible(true);

			}
		});
		
		run();
	}


	/**
	 * Prompt for and return the address of the server.
	 */
	public static String getServerAddress() {
		//		return JOptionPane.showInputDialog(
		//				frame,
		//				"Enter IP Address of the Server:",
		//				"Welcome to the Chatter",
		//				JOptionPane.QUESTION_MESSAGE);
		//	System.out.println(enterChat.ip);
		return enterChat.ip;
	}

	/**
	 * Prompt for and return the desired screen name.
	 */
	private String getName() {
		//		return JOptionPane.showInputDialog(
		//				frame,
		//				"Choose a screen name:",
		//				"Screen name selection",
		//				JOptionPane.PLAIN_MESSAGE);
		//	System.out.println(enterChat.username);
		return enterChat.username;
	}

	/**
	 * Connects to the server then enters the processing loop.
	 */
	public void run() throws IOException {
		// Make connection and initialize streams
		String serverAddress, name, sessionName;
		

		//keeps checking until serverAddress and username are not null
		do{
			serverAddress = getServerAddress();
			socket = new Socket(serverAddress, 9001);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			name = getName();
			sessionName = "";
		}while(getServerAddress() == null || getName() == null);

		// Process all messages from server, according to the protocol.
		while (true) {
			String line = in.readLine();

			if (line.startsWith("SUBMITNAME")) {
				out.println(name);
			} else if (line.startsWith("NAMEACCEPTED")) {
				textField.setEditable(true);
			} else if(line.startsWith("NAME")){
				sessionName = line.substring(4);
				messageArea.append(sessionName + "\n");
			} else if (line.startsWith("MESSAGE")) {
				messageArea.append(line.substring(8) + "\n");
			}

			frame.addWindowListener(new java.awt.event.WindowAdapter() {

				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					if (JOptionPane.showConfirmDialog(frame, 
							"Are you sure to close this window?", "Really Closing?", 
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						try {
							socket.close();
							in.close();
							out.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.exit(0);
						
					}
					serv.updateLog("\n" + getName() + " left the chat");
				}
			});
		}
	}

	public static void main(String[] args) throws IOException {

		new ChatClient();

	}
}