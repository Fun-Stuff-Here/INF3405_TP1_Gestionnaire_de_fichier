import java.util.Scanner;

public class Utils {
	/**
	 * 
	 * @return IP address entered from the user
	 */
public static String getIPAddress() {
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			
			try {
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
			finally {
				scanner.close();
			}
		}

	}	
	/**
	 * 
	 * @return port entered from the user
	 */
public static int getPort() {
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			try {
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
			finally {
				scanner.close();
			}
		}
}
}
