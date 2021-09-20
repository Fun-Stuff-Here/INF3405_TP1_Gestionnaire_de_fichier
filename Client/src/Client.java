
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
				
				//separate the command key from the arguments
				String[] arguments = command.split(" ");
				String commandKey = arguments[0];
				arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
	
				//stop command
				if(commandKey.equals("stop")) break;
				
				//send the command to the server
				if(commandKey.equals("upload")) upload(arguments);
				else send(new Message(command));
				

				//receive the command response
				Message message = getFromServer();
				message.run();
				if(commandKey.equals("download")) download(arguments,message.getFunction());
				
			}
		}
		catch(Exception e) {e.printStackTrace();}
		finally {
		// Fermeture de la connexion avec le serveur
		socket.close();
		}
	}
	
	/**
	 * Execution from client for download
	 * @param args
	 * @param function
	 * @throws Exception
	 */
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
	     
	   System.out.println("Le fichier "+file.getName()+" a bien été téléchargé");
	   fileOutputStream.close();
	   bufferedOutputStream.close();
	}
	
	@SuppressWarnings({ "resource", "deprecation" })
	/**
	 * Execution from client for upload
	 * @param args
	 * @throws Exception
	 */
	public static void upload(String[] args) throws Exception {
		
		Path currentDir = FileSystems.getDefault().getPath(System.getProperty("user.dir"));
		File file = currentDir.resolve(args[0]).toFile();
		byte[] byteArray = new byte[(int)file.length()];
		Integer size = (int) file.length();
		send(new Message("upload " + file.getName() +" "+ size.toString()));
		FileInputStream fileinputStream = new FileInputStream(file);
		BufferedInputStream bufferedinputStream = new BufferedInputStream(fileinputStream);
		bufferedinputStream.read(byteArray,0,byteArray.length);
		
		OutputStream out = socket.getOutputStream();
		out.write(byteArray,0,byteArray.length);
		out.flush();
	}
	
	public static Message getFromServer() throws Exception{
		ObjectInputStream messageIn = new ObjectInputStream(socket.getInputStream()); 
		Message message = (Message) messageIn.readObject();
		return message;
	}
	
	/**
	 * 
	 * @param message
	 * @throws IOException 
	 */
	public static void send(Message message) throws Exception {
		//Création d'un canal sortant pour envoyer des messages au server
		ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream());
		//Envoie un message au server
		out.writeObject(message);
		out.flush();
	}
}

