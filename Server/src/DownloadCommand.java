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
	
}
