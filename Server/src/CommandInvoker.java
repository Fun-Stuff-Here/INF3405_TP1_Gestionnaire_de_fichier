import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandInvoker {

	private Socket socket;
	private Boolean active = true;
	private HashMap<String, Command> commands;
	
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
		commands.put("Hi", new BaseCommand("Hi",this));
		commands.put(HelpCommand.ID, new HelpCommand(this));
		commands.put(ChangeDirectoryCommand.ID, new ChangeDirectoryCommand(this));
		commands.put(ListDirectoryCommand.ID, new ListDirectoryCommand(this));
		commands.put(MakeDirectoryCommand.ID, new MakeDirectoryCommand(this));
		commands.put(DeleteCommand.ID, new DeleteCommand(this));
		commands.put(DownloadCommand.ID, new DownloadCommand(this));
		commands.put(UploadCommand.ID, new UploadCommand(this));
	}
	
	/**
	 * routine that interact with the client and execute commands received
	 * @throws IOException
	 */
	public void executeCommands() throws IOException {
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
				DataOutputStream out =new DataOutputStream(socket.getOutputStream());
				out.writeUTF("Unknown commands :" + commandKey);
			}
		}
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
}
