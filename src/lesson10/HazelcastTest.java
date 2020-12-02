package lesson10;

import java.util.EventListener;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.map.MapEvent;
import com.hazelcast.map.impl.MapListenerAdapter;
import com.hazelcast.map.listener.MapListener;

public class HazelcastTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HazelcastInstance hzInstance1 = Hazelcast.newHazelcastInstance();
		HazelcastInstance hzInstance2 = Hazelcast.newHazelcastInstance();
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();
		try {
	        // Get the Distributed Map from Cluster.
	        System.out.println("Getting map...");
	        IMap map = hz.getMap("my-distributed-map");
	        map.addEntryListener(new MapListenerAdapter(){
	        	@Override
		        public void onEntryEvent(EntryEvent event) {
		        	super.onEntryEvent(event);
		        	System.out.println("Entry="+event);
		        }
	        	
	        	@Override
        		public void onMapEvent(MapEvent event) {
        			super.onMapEvent(event);
		        	System.out.println("Map="+event);
        		}
	        }, true);
	        //Standard Put and Get.
	        System.out.println("Updating map...");
	        map.put("key", "value");
	        map.get("key");
	        //Concurrent Map methods, optimistic updating
	        map.putIfAbsent("somekey", "somevalue");
	        map.replace("key", "value", "newvalue");
	        // Shutdown this Hazelcast client
		} finally {
	        hz.shutdown();
	        hzInstance1.shutdown();
	        hzInstance2.shutdown();
		}
		
		// c:/x.txt
		// ivanov
		// petrov
		// sidorov
		// ivanov
		
	}

}
