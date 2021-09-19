import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
	public abstract void execute(String[] args) throws IOException;
	
	/**
	 * 
	 * @param message to send to the client
	 * @throws IOException
	 */
	protected void send(String message) throws IOException {
		//Cr�ation d'un canal sortant pour envoyer des messages au client
		DataOutputStream out =new DataOutputStream(socket.getOutputStream());
		//Envoie un message au client
		out.writeUTF(message);
}
	
	protected void send(String[] messages) throws IOException{
		String reponses ="";
		for(String reponse:messages) {
			reponses += reponse + "\n";
		}
		send(reponses);
	}
	
	/**
	 * 
	 * @return message received from the client
	 * @throws IOException
	 */
	protected String getFromClient() throws IOException {
		DataInputStream in = new DataInputStream(socket.getInputStream());
		return in.readUTF();
	}
	
	
	@Override
	public String getCommandID() {
		return commandID;
	}


	@Override
	public abstract String getHelp();
	
	
	
}
