import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.WebSocket.Listener;
/**
 * @author nicol
 *
 */
public class Server {

	private static ServerSocket listener;
	

	
	public static void main(String[] args) throws Exception {
		
		int nClient =0;
		
		String serverAddress ="127.0.0.1";
		int serverPort = 5012;	
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

	public static class ClientHandler extends Thread {
		private Socket socket;
		private int clientNumber;
		
		public ClientHandler(Socket socket, int clientNumber)
		{
			this.socket=socket;
			this.clientNumber=clientNumber;
			System.out.println("New connection with client#"+clientNumber + " at "+socket);
		}
		/*
		 * Le thread envoie au client un message de bienvenue
		 */
		public void run() 
		{
			try 
			{
				//Création d'un canal sortant pour envoyer des messages au client
				DataOutputStream out =new DataOutputStream(socket.getOutputStream());
				
				//Envoie un message au client
				out.writeUTF("Hello from server - you are client#"+ clientNumber);
			} catch (IOException e) 
			{
				System.out.println("Error handling client#"+clientNumber+": "+e);
			}
			finally 
			{
				try 
				{
					//fermeture de la connexion avec le client
					socket.close();
				} catch (IOException e) 
				{
					System.out.println("Couldn't close a socket, what's going on?");
				}
				System.out.println("Connection with client# "+clientNumber+"closed");
			}
		}
	}
	

}


