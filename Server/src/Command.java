import java.io.IOException;

public interface Command {
		
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public void execute(String[] args) throws IOException;
	
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
