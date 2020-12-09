package lesson12;

import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.ItemEvent;
import com.hazelcast.collection.ItemListener;
import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.executor.impl.ExecutionCallbackAdapter;
import com.hazelcast.map.IMap;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;

public class PoolTest {

	public static void main(String[] args) {
		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
		try{
			IExecutorService 		service = hz.getExecutorService("service");
			IMap<String, Object>	parameters = hz.getMap("WellKnownMap");
			
			parameters.put("k1", "sdjkfjksdhjksdh");
			parameters.put("k2", "sdjkfjksdhjksdh");
			parameters.put("k3", "sdjkfjksdhjksdh");
			parameters.put("k4", "sdjkfjksdhjksdh");
			
			service.submit(new SharedTask("k1"));
			service.submit(new SharedTask("k2"));
			service.submit(new SharedTask("k3"));
			service.submit(new SharedTask("k4"));
		} finally {
	        hz.shutdown();
	        hzInstance.shutdown();
		}
	}
}


