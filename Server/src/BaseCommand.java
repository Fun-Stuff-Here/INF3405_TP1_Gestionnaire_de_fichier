import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public abstract class BaseCommand implements Command {

	protected final String commandID;
	protected final CommandInvoker invoker;
	protected Socket socket;
	
	/**
	 * 
	 * @param commandID
	 * @param invoker from which it is call
	 */
	public BaseCommand(String commandID, CommandInvoker invoker) {
		this.commandID =commandID;
		this.invoker = invoker;
		this.socket = invoker.getSocket();
	}
	
	
	@Override
	public abstract void execute(String[] args) throws Exception;
	
	/**
	 * 
	 * @param message to send to the client
	 * @throws IOException
	 */
	protected void send(String message) throws IOException {
		invoker.send(message);
}
	/**
	 * 
	 * @param messages to send to client
	 * @throws IOException
	 */
	protected void send(String[] messages) throws IOException{
		invoker.send(messages);
	}
	
	/**
	 * 
	 * @param message to send to client
	 * @throws IOException
	 */
	protected void send(Message message) throws IOException{
		invoker.send(message);
	}
	
	/**
	 * 
	 * @return message received from the client
	 * @throws IOException
	 */
	protected Message getFromClient() throws Exception {
		return invoker.getFromClient();
	}
	
	
	@Override
	public String getCommandID() {
		return commandID;
	}


	@Override
	public abstract String getHelp();
	
	
	
}
