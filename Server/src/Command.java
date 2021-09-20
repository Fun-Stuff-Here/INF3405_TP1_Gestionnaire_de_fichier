import java.io.IOException;

public interface Command {
		
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public void execute(String[] args) throws Exception;
	
	/**
	 * 
	 * @return help string on how to use the command
	 */
	public String getHelp();

	/**
	 * 
	 * @return command ID
	 */
	public String getCommandID();
	
}
