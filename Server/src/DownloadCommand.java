import java.io.IOException;

public class DownloadCommand  extends BaseCommand{

	public static final String ID = "download";
	
	/**
	 * 
	 * @param invoker from which it is call
	 */
	public DownloadCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}

	@Override
	public String getHelp() {
		return "Download from the server the file - args: <File name> <-z> - Optional argument <-z>: if passed in, the file will be compressed as a .zip file.";
	}
	
	
}
