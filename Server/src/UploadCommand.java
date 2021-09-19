import java.io.IOException;

public class UploadCommand extends BaseCommand{

	public static final String ID = "upload";
	
	/**
	 * 
	 * @param invoker from which it is call
	 */
	public UploadCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}

	@Override
	public String getHelp() {
		return "Upload to server specified file - Args: <File name>";
	}
	
	
}
