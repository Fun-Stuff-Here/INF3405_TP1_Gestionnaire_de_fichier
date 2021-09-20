import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadCommand extends BaseCommand{

	public static final String ID = "upload";
	
	/**
	 * 
	 * @param invoker from which it is call
	 */
	public UploadCommand(CommandInvoker invoker) {super(ID, invoker);}
	
	@Override
	public void execute(String[] args) throws Exception {
		
		
		int size = Integer.parseInt(args[1]);
		byte[] byteArray = new byte[size];
		InputStream in = socket.getInputStream();
		FileOutputStream fileOutputStream = new FileOutputStream(args[0]);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);  
		int nBytesRead = in.read(byteArray,0,byteArray.length);
	    int current = nBytesRead;

	      do {
	    	  nBytesRead =
	            in.read(byteArray, current, (byteArray.length-current));
	         if(nBytesRead >= 0) current += nBytesRead;
	      } while(nBytesRead > 0);

	      bufferedOutputStream.write(byteArray, 0 , current);
	      bufferedOutputStream.flush();
	     
	   
	   fileOutputStream.close();
	   bufferedOutputStream.close();
		send("Le fichier "+args[0]+" a bien été envoyé");
	   
	}

	@Override
	public String getHelp() {
		return "Upload to server specified file - Args: <File name>";
	}
	
	
}
