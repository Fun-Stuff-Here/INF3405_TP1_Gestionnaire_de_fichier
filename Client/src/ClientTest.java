import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

class ClientTest {

	@Test
	void testgetIP() {
		String goodIP = "127.0.0.1";
		ByteArrayInputStream toolongIP = new ByteArrayInputStream("127.0.0.1.55".getBytes());
		ByteArrayInputStream impossibleIP = new ByteArrayInputStream("256.255.1.0".getBytes());
		
		InputStream sysInBackup = System.in; // backup System.in to restore it later
		ByteArrayInputStream goodIn = new ByteArrayInputStream(goodIP.getBytes());
			
		System.setIn(goodIn);
		
		String ip = Client.getIPAddress();
		
		assertEquals(goodIP, ip);
		
		//reset System.in to its original
		System.setIn(sysInBackup);
		
	}
	
	
	

}
