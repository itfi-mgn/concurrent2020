package lesson12;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class SharedTask implements Callable<String>, Serializable {
	private static final long serialVersionUID = 1559565692523231841L;

	private final String	mapKey;
	
	public SharedTask(final String mapKey) {
		this.mapKey = mapKey;
	}
	
	@Override
	public String call() throws Exception {
		return "ura!!!!";
	}
}
