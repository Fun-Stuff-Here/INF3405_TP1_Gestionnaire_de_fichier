import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Message implements Serializable, Runnable {


	//required long for serialization
	private static final long serialVersionUID = 1L;
	private String log;
	private SerializableLambda function;
	
	/**
	 * A message is use to transfer information between sever and client
	 * @param log
	 * @param object
	 * @param method
	 * @param args
	 */
	public Message(String log, SerializableLambda function) {
		this.setLog(log);
		this.function = function;

	}
	
	/**
	 * 
	 * @param log
	 */
	public Message(String log) 
	{
		this(log,null);
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
	 * @param log
	 */
	public void setLog(String log) {
		this.log = log;
	}

	@Override
	public void run() {
		System.out.print(log);
		if(function !=null)
			try {
				function.apply(null);
			} catch (Exception e) {

				e.printStackTrace();
			}
		
	}

	public Function<Object,Object> getFunction() {
		return function;
	}
	
	
	
	
}
