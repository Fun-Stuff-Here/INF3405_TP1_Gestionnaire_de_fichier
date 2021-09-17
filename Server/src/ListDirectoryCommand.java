import java.io.IOException;

public class ListDirectoryCommand extends BaseCommand{

	public static final String ID = "ls";
	
	public ListDirectoryCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
	
}
