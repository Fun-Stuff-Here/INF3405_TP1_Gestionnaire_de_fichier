import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends BaseCommand{

	public static final String ID = "help";
	
	/**
	 * 
	 * @param invoker from which it is call
	 */
	public HelpCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
	
		if(args.length !=0) sendCommandsHelpByKeys(args);
		else sendCommandsHelpByKeys(invoker.getCommands().keySet().toArray(new String[0]));
		
	}
	
	@Override
	public String getHelp() {
		return "Show all available commands or the one specifie in the arguments - args: <CommandID> <CommandID> ...";
	}
	
	
	/**
	 * sends all help messages from commandKeys
	 * @param commandKeys
	 * @throws IOException
	 */
	protected void sendCommandsHelpByKeys(String[] commandKeys) throws IOException {
		String response = "";
		for(String commandKey:commandKeys) {
			String helpString = "Invalid Command Key for: "+ commandKey;
			if(invoker.getCommands().containsKey(commandKey))
				helpString= commandKey +" - " + invoker.getCommands().get(commandKey).getHelp();
			response += helpString+"\n";
		}
		send(response);
	}
	

}
