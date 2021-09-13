import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.WebSocket.Listener;
/**
 * @author nicol
 *
 */
public class Server {

	/**
	 * @param args
	 */
	private static ServerSocket listener;

	public static void main(String[] args) throws Exception {

		int nClient =0;

		String serverAddress ="127.0.0.1";
		int serverPort = 5012;	
		listener = new ServerSocket();
		listener.setReuseAddress(true);
		InetAddress serverIP = InetAddress.getByName(serverAddress);

		listener.bind(new InetSocketAddress(serverIP,serverPort));

		System.out.format("The server is running on %s:%d%n",serverAddress, serverPort);

		try {
			while(true) {
				new ClientHandler(listener.accept(),nClient++).start();		
				}

		} finally {
			listener.close();
		}


	}
}


	
	



