import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;

public class ListDirectoryCommand extends BaseCommand{

	public static final String ID = "ls";
	
	/**
	 * 
	 * @param invoker from which it is call
	 */
	public ListDirectoryCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws IOException {
		File f = invoker.getCurrentDirectory().toFile();
		File[] files = f.listFiles();
		if(files.length == 0)
		{send("Folder is empty");return;}
				
		String response="";
		for(File file:files) {
			if(file.isHidden() && file.isDirectory())
				response+= "[Hidden Folder] ";
			else if(file.isHidden() && file.isFile()) 
				response = "[Hidden File] ";
			else if(!file.isHidden() && file.isDirectory())
				response += "[Folder] ";
			else if(!file.isHidden() && file.isFile())
				response += "[File] ";
			response += file.getName() +"\n";
		}
		send(response);
	}

	@Override
	public String getHelp() {
		return "Show files and folders from current directory - args: None";
	}
	
}
