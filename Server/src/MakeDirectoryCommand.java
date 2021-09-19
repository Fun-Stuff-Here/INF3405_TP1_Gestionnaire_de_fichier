import java.io.File;
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
		if (args.length ==0) {
			send("Require folder name, please try again\n");
			return;
		}
	    File directory = new File(invoker.getCurrentDirectory()+"/"+args[0]);
	    if (! directory.exists()) directory.mkdir();
	    
		send("Le dossier "+args[0]+" a été créé.");
	}

	@Override
	public String getHelp() {
		return "Create folder in current directory - args: <Folder name>";
	}
	
	
}
