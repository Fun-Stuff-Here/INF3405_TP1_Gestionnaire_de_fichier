import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Message implements Runnable{

	private final String log;
	private final String data;
	
	/**
	 * A message is use to transfer information between sever and client 
	 * @param log
	 * @param object
	 */
	public Message(String log, Map<String, String> data) {
		this.log =log;
		this.data = new Gson().toJson(data);
	}

	/**
	 * 
	 * @param log
	 */
	public Message(String log) 
	{
		this(log,null);
	}

	@Override
	public void run() {
		System.out.println(log);
	}
	
	/**
	 * 
	 * @return log of the message
	 */
	public String getLog() {
		return log;
	}

	/**
	 * 
	 * @return data containing key-value String pair
	 */
	public Map<String, String> getData() {
		
		return new Gson().fromJson(data, Map.class);
	}

}
