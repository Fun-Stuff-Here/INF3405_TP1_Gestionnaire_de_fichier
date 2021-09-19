import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.WebSocket.Listener;
import java.text.ParseException;
import java.util.Scanner;

public class Server {


	private static ServerSocket listener;

	
	/**
	 * 
	 * @return IP address entered from the user
	 */
public static String getIPAddress() {
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			
			try {
			System.out.println("Enter a valid IP address:");
			String iP = scanner.nextLine();
			String[] ipByte = iP.split("\\.");
			
			//check right size
			if(ipByte.length !=4) 
				throw new IllegalArgumentException("Ip address should be of lenght 4");
			
			//check valid Bytes
			for (String string : ipByte) {
				int octet = Integer.parseInt(string);
				if(octet <0 || octet >255)
					throw new IllegalArgumentException("Ip address should have four values between 0 and 255");
			}
			
			System.out.println("IP address: " + iP);
			return iP;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			}
		}
	}	
	/**
	 * 
	 * @return port entered from the user
	 */
public static int getPort() {
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
			System.out.println("Enter a valid Port number: ");
			String portString = scanner.nextLine();
			int port = Integer.parseInt(portString);
			
			//check that port is in range 5002 and 5049
			if(port <5002 || port >5049) 
				throw new IllegalArgumentException("Port must be between 5002 and 5049");

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
		
		
		//create and configure listener
		listener = new ServerSocket();
		listener.setReuseAddress(true);
		InetAddress serverIP = InetAddress.getByName(serverAddress);
		listener.bind(new InetSocketAddress(serverIP,serverPort));

		System.out.format("The server is running on %s:%d%n",serverAddress, serverPort);

		try {
			while(true) {
				//accept client and create their handler, methode accept block execution 
				new ClientHandler(listener.accept(),nClient++).start();		
				}

		} finally {
			listener.close();
		}


	}
	

	
}


	
	



