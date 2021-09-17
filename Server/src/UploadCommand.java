import java.io.IOException;

public class UploadCommand extends BaseCommand{

	public static final String ID = "upload";
	
	public UploadCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
	
	
}
