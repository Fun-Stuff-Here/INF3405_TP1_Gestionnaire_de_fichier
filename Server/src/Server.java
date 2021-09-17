import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.WebSocket.Listener;
import java.text.ParseException;
import java.util.Scanner;
/**
 * @author nicol
 *
 */
public class Server {

	/**
	 * @param args
	 */
	private static ServerSocket listener;

	
	
	private static String getIPAddress() {
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
			System.out.println("Enter a valid IP address:");
			String iP = scanner.nextLine();
			
			String[] ipByte = iP.split("\\.");
			if(ipByte.length !=4) throw new IllegalArgumentException("Ip address should be of lenght 4");
			for (String string : ipByte) {
				int octet = Integer.parseInt(string);
				if(octet <0 || octet >255) throw new IllegalArgumentException("Ip address should have four values between 0 and 255");
			}
			
			System.out.println("IP address: " + iP);
			return iP;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}


		
	}	
	
private static int getPort() {
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
			System.out.println("Enter a valid Port number: ");
			String portString = scanner.nextLine();
			
		
			int port = Integer.parseInt(portString);
			if(port <5002 || port >5049) throw new IllegalArgumentException("Port must be between 5002 and 5049");

			System.out.println("Port number: " + port);
			return port;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}


		
	}	
	
	
	public static void main(String[] args) throws Exception {

		int nClient =0;

		
		
		String serverAddress = "127.0.0.1";//getIPAddress();
		int serverPort = 5012;//getPort();	
		
		
		
		listener = new ServerSocket();
		listener.setReuseAddress(true);
		InetAddress serverIP = InetAddress.getByName(serverAddress);

		listener.bind(new InetSocketAddress(serverIP,serverPort));

		System.out.format("The server is running on %s:%d%n",serverAddress, serverPort);

		try {
			while(true) {
				new ClientHandler(listener.accept(),nClient++).start();		
				}

		} finally {
			listener.close();
		}


	}
	

	
}


	
	



