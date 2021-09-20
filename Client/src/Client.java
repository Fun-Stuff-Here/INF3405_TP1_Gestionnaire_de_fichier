
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

import javax.swing.text.Utilities;

public class Client 
{
	private static Socket socket;

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
		try {
			while(true) {
				
				String command = scanner.nextLine();
				//stop command
				if(command.equals("stop")) break;
				
				//send the command to the server
				out =new DataOutputStream(socket.getOutputStream());
				out.writeUTF(command);
				
				//separate the command key from the arguments
				String[] arguments = command.split(" ");
				String commandKey = arguments[0];
				arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
	
				//receive the command response
				ObjectInputStream messageIn = new ObjectInputStream(socket.getInputStream()); 
				Message message = (Message) messageIn.readObject();
				message.run();
				if(commandKey.equals("download")) download(arguments,message.getFunction());
				if(commandKey.equals("upload")) upload(arguments);
				
			}
		}
		catch(Exception e) {e.printStackTrace();}
		finally {
		// Fermeture de la connexion avec le serveur
		socket.close();
		}
	}
	
	public static void download(String[] args, Function<Object, Object> function) throws Exception {
		
		File file = (File) function.apply(null); 
		byte[] byteArray = new byte[(int)file.length()];
			InputStream in = socket.getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream(file.getName());
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);  
			int nBytesRead = in.read(byteArray,0,byteArray.length);
		    int current = nBytesRead;

		      do {
		    	  nBytesRead =
		            in.read(byteArray, current, (byteArray.length-current));
		         if(nBytesRead >= 0) current += nBytesRead;
		      } while(nBytesRead > 0);

		      bufferedOutputStream.write(byteArray, 0 , current);
		      bufferedOutputStream.flush();
		     
		   System.out.println("Le fichier "+file.getName()+" à bien été téléchargé");
		   fileOutputStream.close();
		   bufferedOutputStream.close();
	}
	
	public static void upload(String[] args) {
		
	}
	
}

