import java.io.IOException;
import java.nio.file.Path;

public class ChangeDirectoryCommand extends BaseCommand{

public static final String ID = "cd";
	
/**
 * 
 * @param invoker from which it is call
 */
	public ChangeDirectoryCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws Exception {
		if(args.length == 0) {
			invoker.getCommands().get("help").execute(new String[] {ID});
			return;
		}
		Path newPath = invoker.getCurrentDirectory().resolve(args[0]).normalize();
		invoker.setCurrentDirectory(newPath);
		send("Vous êtes dans le dossier "+invoker.getCurrentDirectory().getFileName().toString()+".");
	}

	@Override
	public String getHelp() {
		return "Change directory to specified path (path = '..' for parent directory) - args: <path>";
	}
	
	
	
}
