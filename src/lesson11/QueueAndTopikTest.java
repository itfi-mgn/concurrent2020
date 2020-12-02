package lesson11;

import java.util.concurrent.Semaphore;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.ItemEvent;
import com.hazelcast.collection.ItemListener;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.MapEvent;
import com.hazelcast.map.impl.MapListenerAdapter;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;

public class QueueAndTopikTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
		try {
			IQueue<String>	q = hz.getQueue("testqueue");
			final Semaphore s = new Semaphore(0);
	        q.addItemListener(new ItemListener<String>() {
				@Override
				public void itemRemoved(ItemEvent<String> arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void itemAdded(ItemEvent<String> arg0) {
					// TODO Auto-generated method stub
					s.release();
				}
			}, true);
	        
	        s.acquire();
	        q.take();
	        
	        
	        ITopic<String> topic = hz.getTopic("testtopic");
	        topic.addMessageListener(new MessageListener<String>() {
				@Override
				public void onMessage(Message<String> arg0) {	// q.take()
					// TODO Auto-generated method stub
					
				}
			});
	        topic.publish("sdsdsd");	// q.put()
		} finally {
	        hz.shutdown();
	        hzInstance.shutdown();
		}
	}

}
