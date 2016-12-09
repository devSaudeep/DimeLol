import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author LuisJonuel
 * @author Saulo
 */
public class ChatServer extends Thread {

	/**
	 * The port that the server listens on.
	 */
	private static final int PORT = 9001;
	
	public static JFrame frame = new JFrame("Server");
	public static JTextArea statusArea = new JTextArea(8, 40);

	/**
	 * The application main method, which just listens on a port and
	 * spawns handler threads.
	 */
	//Create a sever status window
	public ChatServer() throws IOException {
		statusArea.setBackground(Color.black);
		statusArea.setForeground(Color.GREEN);
		statusArea.setEditable(false);
		statusArea.append("The chat server is running.");
		frame.getContentPane().add(new JScrollPane(statusArea), "Center");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		System.out.println("The chat server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while (true) {
				new ServerHandler(listener.accept(), this).start();
			}
		} finally {
			listener.close();
		}
		
	}
	
	public void updateLog(String str) {
		statusArea.append(str);
	}
	
	public static void main(String[] args) throws IOException {
		new ChatServer();
	}
}