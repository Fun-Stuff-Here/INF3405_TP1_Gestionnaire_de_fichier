import java.io.IOException;

public interface Command {
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public void execute(String[] args) throws IOException;
	
	public String getHelp();
	
}
