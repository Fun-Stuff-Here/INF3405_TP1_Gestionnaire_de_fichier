import java.io.IOException;

public class DeleteCommand extends BaseCommand{
	public static final String ID = "delete";
	
	public DeleteCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
}
