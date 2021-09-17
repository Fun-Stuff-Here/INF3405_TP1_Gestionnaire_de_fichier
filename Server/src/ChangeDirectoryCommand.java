import java.io.IOException;

public class ChangeDirectoryCommand extends BaseCommand{

public static final String ID = "cd";
	
	public ChangeDirectoryCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
	
	
	
}
