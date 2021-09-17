import java.io.IOException;

public class DownloadCommand  extends BaseCommand{

	public static final String ID = "download";
	
	public DownloadCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
	
}
