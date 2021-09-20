import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandInvoker implements Serializable{

	private static final long serialVersionUID = 1L;
	private Socket socket;
	private Boolean active = true;
	private HashMap<String, Command> commands;
	private Path currentDirectory;
	
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
	
	/**
	 * 
	 * @param socket connexion to client
	 */
	public CommandInvoker(Socket socket)
	{
		this.socket = socket;
		commands = new HashMap<String, Command>();
		
		//create and insert all available commands
		commands.put(HelpCommand.ID, new HelpCommand(this));
		commands.put(ChangeDirectoryCommand.ID, new ChangeDirectoryCommand(this));
		commands.put(ListDirectoryCommand.ID, new ListDirectoryCommand(this));
		commands.put(MakeDirectoryCommand.ID, new MakeDirectoryCommand(this));
		commands.put(DeleteCommand.ID, new DeleteCommand(this));
		commands.put(DownloadCommand.ID, new DownloadCommand(this));
		commands.put(UploadCommand.ID, new UploadCommand(this));
		
		currentDirectory = FileSystems.getDefault().getPath(System.getProperty("user.dir"));
		
	}
	
	/**
	 * routine that interact with the client and execute commands received
	 * @throws IOException
	 */
	public void executeCommands() throws Exception {
		while(active) {
			
			//listen for client input
			DataInputStream in = new DataInputStream(socket.getInputStream());
			String command = in.readUTF();
			
			//log the incomming request
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println("["+socket.getInetAddress().toString().substring(1)+":"+ socket.getPort()+"//"+timeFormat.format(timestamp) +"]: "+command);
			
			//separate the command key from the arguments
			String[] args = command.split(" ");
			String commandKey = args[0];
			args = Arrays.copyOfRange(args, 1, args.length);

			//execute the command if available
			if(commands.containsKey(commandKey))
				commands.get(commandKey).execute(args);
			else
			{
				send("Unknown commands :" + commandKey);
			}
		}
	}
	
	/**
	 * 
	 * @param message
	 * @throws IOException 
	 */
	public void send(Message message) throws IOException {
		//Cr�ation d'un canal sortant pour envoyer des messages au client
		ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream());
		//Envoie un message au client
		out.writeObject(message);
	}
	
	/**
	 * 
	 * @param message to send to the client
	 * @throws IOException
	 */
	public void send(String message) throws IOException {
		send(new Message(message));
	}
	
	/**
	 * 
	 * @param messages
	 * @throws IOException
	 */
	public void send(String[] messages) throws IOException{
		String reponses ="";
		for(String reponse:messages) {
			reponses += reponse + "\n";
		}
		send(reponses);
	}
	
	
	
	/**
	 * 
	 * @return if invoker is active
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * 
	 * @param active
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * 
	 * @return connexion to the client
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * 
	 * @return all commands
	 */
	public HashMap<String, Command> getCommands(){
		return commands;
	}

	/**
	 * 
	 * @return path to current directory
	 */
	public Path getCurrentDirectory() {
		return currentDirectory;
	}

	/**
	 * 
	 * @param currentDirectory
	 */
	public void setCurrentDirectory(Path currentDirectory) {
		this.currentDirectory = currentDirectory;
	}
}
