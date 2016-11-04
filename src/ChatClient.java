import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.Panel;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.*;

public class ChatClient{

	BufferedReader in;
	PrintWriter out;
	JFrame frame = new JFrame("DimeloL");
	JTextField textField = new JTextField(40);
	JTextArea messageArea = new JTextArea(8, 40);

	/**
	 * Constructs the client by laying out the GUI and registering a
	 * listener with the textfield so that pressing Return in the
	 * listener sends the textfield contents to the server.  Note
	 * however that the textfield is initially NOT editable, and
	 * only becomes editable AFTER the client receives the NAMEACCEPTED
	 * message from the server.
	 */
	public ChatClient() {

		// Layout GUI
		textField.setEditable(false);
		messageArea.setEditable(false);
		messageArea.setForeground(Color.WHITE);
		messageArea.setBackground(new Color(102, 102, 102));
		messageArea.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		frame.getContentPane().add(textField, "South");
		frame.getContentPane().add(new JScrollPane(messageArea), "Center");
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
	}

	/**
	 * Prompt for and return the address of the server.
	 */
	private String getServerAddress() {
		return JOptionPane.showInputDialog(
				frame,
				"Enter IP Address of the Server:",
				"Welcome to the Chatter",
				JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * Prompt for and return the desired screen name.
	 */
	private String getName() {
		return JOptionPane.showInputDialog(
				frame,
				"Choose a screen name:",
				"Screen name selection",
				JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Connects to the server then enters the processing loop.
	 */
	private void run() throws IOException {

		// Make connection and initialize streams
		String serverAddress = getServerAddress();
		Socket socket = new Socket(serverAddress, 9001);
		in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		String name = getName();
		String sessionName = "";

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
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

						System.exit(0);

					}
				}	
			});



		}

	}

	/**
	 * Runs the client as an application with a closeable frame.
	 */
	public static void main(String[] args) throws Exception {
		ChatClient client = new ChatClient();
		client.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		client.frame.setVisible(true);
		client.run();
	}
}
