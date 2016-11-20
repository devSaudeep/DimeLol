import java.io.IOException;
import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter the number of clients for DimeLol...");
		
		int n = kb.nextInt();
		for (int i = 1; i <= n; i++) {
			try {
				new ChatServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}