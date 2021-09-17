import java.io.IOException;

public class DeleteCommand extends BaseCommand{
	public static final String ID = "delete";
	
	/**
	 * 
	 * @param invoker from which it is call
	 */
	public DeleteCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		send(ID +" command not yet implemented");
	}
}
