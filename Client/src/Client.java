
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
	private static Socket socket;
	
	
public static String getIPAddress() {
		
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
	
public static int getPort() {
		
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
	
	
	/*
	 * Application client
	 */
	public static void main(String[] args) throws Exception 
	{
		// Adresse et port du serveur
		String serverAdress ="127.0.0.1";// getIPAddress();
		int port = 5012;//getPort();
		
		// Création d'une nouvelle connexion avec le serveur
		socket = new Socket(serverAdress, port);
		
		System.out.format("The server is running on %s:%d%n", serverAdress, port);
		
		// Création d'un canal entrant pour recevoir les messages envoyés par le serveur
		DataInputStream in = new DataInputStream(socket.getInputStream());
		
		// Attente de la réception d'un message envoyé par le serveur sur le canal
		String helloMessageFromServer = in.readUTF();
		System.out.println(helloMessageFromServer);
		
		
		Scanner scanner = new Scanner(System.in);
		DataOutputStream out =new DataOutputStream(socket.getOutputStream());
		while(true) {
			
			String command = scanner.nextLine();
			if(command.equals("stop")) break;
			
			out =new DataOutputStream(socket.getOutputStream());
			out.writeUTF(command);
			
			in = new DataInputStream(socket.getInputStream());
			String received = in.readUTF();
			System.out.println(received);
		}
		
		
		
		// Fermeture de la connexion avec le serveur
		socket.close();
	}
	
}

