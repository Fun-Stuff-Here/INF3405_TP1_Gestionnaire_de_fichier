import java.io.IOException;

public class MakeDirectoryCommand extends BaseCommand{

	public static final String ID = "mkdir";
	
	public MakeDirectoryCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
	
	
}
