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
		String[] items = f.list().clone();
		if(items.length == 0)
		{send("Folder is empty");return;}
		
		for(int i =0; i<items.length;i++) {
			if(items[i].startsWith("."))
				items[i] = "[Hidden Folder]" + items[i] ;
			else if(!items[i].contains(".")) 
				items[i] = "[Folder]" + items[i];
			else 
				items[i] = "[File]" + items[i];
		}
		send(items);
	}

	@Override
	public String getHelp() {
		return "Show files and folders from current directory - args: None";
	}
	
}
