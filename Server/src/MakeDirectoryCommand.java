import java.io.IOException;

public class MakeDirectoryCommand extends BaseCommand{

	public static final String ID = "mkdir";
	
	/**
	 * 
	 * @param invoker from which it is call
	 */
	public MakeDirectoryCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
	
	
}
