import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Utils {
	/**
	 * 
	 * @return IP address entered from the user
	 */
public static String getIPAddress() {
		
		
		while(true) {
			
			try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a valid IP address:");
			String iP = scanner.nextLine();
			String[] ipByte = iP.split("\\.");
			
			//check right size
			if(ipByte.length !=4) 
				throw new IllegalArgumentException("Ip address should be of lenght 4");
			
			//check valid Bytes
			for (String string : ipByte) {
				int octet = Integer.parseInt(string);
				if(octet <0 || octet >255)
					throw new IllegalArgumentException("Ip address should have four values between 0 and 255");
			}
			
			System.out.println("IP address: " + iP);
			return iP;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
		}

	}	
	/**
	 * 
	 * @return port entered from the user
	 */
public static int getPort() {
		
		
		while(true) {
			try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a valid Port number: ");
			String portString = scanner.nextLine();
			int port = Integer.parseInt(portString);
			
			//check that port is in range 5002 and 5049
			if(port <5002 || port >5049) 
				throw new IllegalArgumentException("Port must be between 5002 and 5049");

			System.out.println("Port number: " + port);
			return port;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}
			
		}
}

/**
 * Delete recursevly all contents insider folder
 * Source: https://www.baeldung.com/java-delete-directory
 * @param directoryToBeDeleted
 * @return true if success
 */
public static boolean deleteDirectory(File directoryToBeDeleted) throws Exception{
    File[] allContents = directoryToBeDeleted.listFiles();
    if (allContents != null) {
        for (File file : allContents) {
            deleteDirectory(file);
        }
    }
    return directoryToBeDeleted.delete();
}


/**
 * 
 * @param file or directory
 * @return zipped file
 * @throws Exception
 */
public static File fileToZip(File file) throws Exception {
	
	File zippedFile = new File(file.getPath()+".zip");
	
	FileOutputStream fileOutputStream = new FileOutputStream(zippedFile);
	ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
	try {
		addDirToZipArchive(zipOutputStream, file, null);
	}
	finally {
		zipOutputStream.close();	
		fileOutputStream.close();
	}
	return zippedFile;
}


/**
 * add a directory to zip
 * source: https://stackoverflow.com/questions/3961087/zipping-a-folder-which-contains-subfolders
 * @param zos
 * @param fileToZip
 * @param parrentDirectoryName
 * @throws Exception
 */
public static void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws Exception {
    if (fileToZip == null || !fileToZip.exists()) {
        return;
    }

    String zipEntryName = fileToZip.getName();
    if (parrentDirectoryName!=null && !parrentDirectoryName.isEmpty()) {
        zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
    }

    if (fileToZip.isDirectory()) {
        for (File file : fileToZip.listFiles()) {
            addDirToZipArchive(zos, file, zipEntryName);
        }
    } else {
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(fileToZip);
        zos.putNextEntry(new ZipEntry(zipEntryName));
        int length;
        while ((length = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }
        zos.closeEntry();
        fis.close();
    }
}


}
