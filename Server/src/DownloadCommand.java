import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class DownloadCommand  extends BaseCommand{

	public static final String ID = "download";
	private String[] args;

	
	/**
	 * 
	 * @param invoker from which it is call
	 */
	public DownloadCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@SuppressWarnings("resource")
	@Override
	public void execute(String[] args) throws Exception {
		this.args = args;

		File file = invoker.getCurrentDirectory().resolve(args[0]).toFile();
		Map<String, String> fileData = new HashMap<String, String>();
		if(zipOptionActivated()) {
			fileData.put("fileName", file.getName());
			file = Utils.fileToZip(file);
		}else fileData.put("fileName", file.getName());
		
		byte[] byteArray = new byte[(int)file.length()];
		fileData.put("size", Integer.toString((int)file.length()));
		send(new Message("Download start",fileData));
		FileInputStream fileinputStream = new FileInputStream(file);
		BufferedInputStream bufferedinputStream = new BufferedInputStream(fileinputStream);
		bufferedinputStream.read(byteArray,0,byteArray.length);
		
		OutputStream out = socket.getOutputStream();
		out.write(byteArray,0,byteArray.length);
		out.flush();
		fileinputStream.close();
		if(zipOptionActivated()) Utils.deleteDirectory(file);
	}

	/**
	 * 
	 * @return true if the option -z was passed in
	 */
	private boolean zipOptionActivated() {
		return (args.length >1 && args[1].equals("-z"));
	}
	
	
	@Override
	public String getHelp() {
		return "Download from the server the file - args: <File name> <-z> - Optional argument <-z>: if passed in, the file will be compressed as a .zip file.";
	}
	
	
	
}


