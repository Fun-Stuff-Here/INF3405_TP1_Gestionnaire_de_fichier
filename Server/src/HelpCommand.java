import java.io.DataOutputStream;
import java.io.IOException;

public class HelpCommand extends BaseCommand{

	public static final String ID = "help";
	
	public HelpCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send("help command not yet implemented");
	}

}
