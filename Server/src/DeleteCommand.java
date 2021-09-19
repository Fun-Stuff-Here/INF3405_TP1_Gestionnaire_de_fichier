import java.io.File;
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
		File toDelete = invoker.getCurrentDirectory().resolve(args[0]).normalize().toFile();
		String response = "Le ";
		if(toDelete.isDirectory()) response += "dossier ";
		else if(toDelete.isFile()) response += "fichier ";
		response += toDelete.getName() + " a été supprimé.";
		
		if(deleteDirectory(toDelete))
			send(response);
		else
			send(args[0] + "failed to delete");
	}

	@Override
	public String getHelp() {
		return "Remove and delete - args: <File name or Folder name>";
	}
	
	/**
	 * Delete recursevly all contents insider folder
	 * Source: https://www.baeldung.com/java-delete-directory
	 * @param directoryToBeDeleted
	 * @return true if success
	 */
	private boolean deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
}
