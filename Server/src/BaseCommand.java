import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BaseCommand implements Command {

	protected final String commandID;
	protected final CommandInvoker invoker;
	protected Socket socket;
	
	
	public BaseCommand(String commandID, CommandInvoker invoker) {
		this.commandID =commandID;
		this.invoker = invoker;
		this.socket = invoker.getSocket();
	}
	
	@Override
	public void execute(String[] args) throws IOException {
		send("Hello from server");
	}
	
	
	
	
	protected void send(String message) throws IOException {
		//Création d'un canal sortant pour envoyer des messages au client
		DataOutputStream out =new DataOutputStream(socket.getOutputStream());
		//Envoie un message au client
		out.writeUTF(message);
}
	
	protected String getFromClient() throws IOException {
		DataInputStream in = new DataInputStream(socket.getInputStream());
		return in.readUTF();
	}
	
	
	public String getCommandID() {
		return commandID;
	}
	
}
