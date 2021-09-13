<<<<<<< HEAD
import java.io.DataInputStream;
import java.net.Socket;

public class Client 
{
	private static Socket socket;
	
	/*
	 * Application client
	 */
	public static void main(String[] args) throws Exception 
	{
		// Adresse et port du serveur
		String serverAdress = "127.0.0.1";
		int port = 5012;
		
		// Création d'une nouvelle connexion avec le serveur
		socket = new Socket(serverAdress, port);
		
		System.out.format("The server is running on %s:%d%n", serverAdress, port);
		
		// Création d'un canal entrant pour recevoir les messages envoyés par le serveur
		DataInputStream in = new DataInputStream(socket.getInputStream());
		
		// Attente de la réception d'un message envoyé par le serveur sur le canal
		String helloMessageFromServer = in.readUTF();
		System.out.println(helloMessageFromServer);
		
		// Fermeture de la connexion avec le serveur
		socket.close();
=======
/**
 * 
 * @author nicol
 *
 */
public class Client {
/**
 * 
 * @param args
 */
	public static void main(String[] args) {
		System.out.print("Client Hello Word! :-)\n");
>>>>>>> 394c01089da490257777070a97db5f2fe7f6fd39
	}
}
