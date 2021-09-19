
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.text.Utilities;


public class Client 
{
	private static Socket socket;

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
	
	
	/**
	 * Main client side
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception 
	{
		// Adresse and port for serveur
		String serverAdress = "127.0.0.1";//getIPAddress();
		int port = 5012;//getPort();
		
		//Establish connexion with server
		socket = new Socket(serverAdress, port);
		
		System.out.format("The server is running on %s:%d%n", serverAdress, port);
		
		//create reception for what the server send
		DataInputStream in = new DataInputStream(socket.getInputStream());
		
		//wait to receive from server
		String helloMessageFromServer = in.readUTF();
		System.out.println(helloMessageFromServer);
		
		//create scanner to capture user input
		Scanner scanner = new Scanner(System.in);
		DataOutputStream out =new DataOutputStream(socket.getOutputStream());
		while(true) {
			
			String command = scanner.nextLine();
			//stop command
			if(command.equals("stop")) break;
			
			//send the command to the server
			out =new DataOutputStream(socket.getOutputStream());
			out.writeUTF(command);
			
			
			//receive the command response
			in = new DataInputStream(socket.getInputStream());
			String received = in.readUTF();
			System.out.println(received);
		}
		// Fermeture de la connexion avec le serveur
		socket.close();
	}
	
}

