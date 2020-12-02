package lesson11;

import java.util.UUID;
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

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
		try {
			IQueue<String>	q = hz.getQueue("testqueue");
			final Semaphore s = new Semaphore(0);	// Modeling queue with blocking options
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
	        
	        
	        ITopic<UUID> topic = hz.getTopic("testtopic");	// Modeling semaphore with local semaphore and topic
			
	        final Semaphore s1 = new Semaphore(0);
			final UUID id = UUID.randomUUID();
	        final UUID msgId = topic.addMessageListener(new MessageListener<UUID>() {
				@Override
				public void onMessage(Message<UUID> arg0) {	// q.take()
					// TODO Auto-generated method stub
					if (arg0.getMessageObject().equals(id)) {
						s1.release();
					}
				}
			});
			// send id to dispatcher
	        // in the dispatcher : topic.publish(id);	// q.put() 
	        
	        s1.acquire();
			topic.removeMessageListener(msgId);
		} finally {
	        hz.shutdown();
	        hzInstance.shutdown();
		}
	}

}
