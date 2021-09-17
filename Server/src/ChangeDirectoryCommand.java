import java.io.IOException;

public class ChangeDirectoryCommand extends BaseCommand{

public static final String ID = "cd";
	
/**
 * 
 * @param invoker from which it is call
 */
	public ChangeDirectoryCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
	
	
	
}
