import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
		private Socket socket;
		private int clientNumber;
		
		/**
		 * 
		 * @param socket 
		 * @param clientNumber connected to the server
		 */
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
				
				//create the invoker to received and execute commands
				CommandInvoker invoker = new CommandInvoker(socket);
				invoker.executeCommands();
				
				
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