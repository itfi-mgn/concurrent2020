package lesson6;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final BlockingQueue<String>	queue = new ArrayBlockingQueue<>(10);
		
		queue.put("sasasasasas");
		System.err.println("Get: "+queue.take());
		
		// 1. queue: 
		// 1.1 - get resources (list resources)
		// 1.2 - free resources (list resources)
		// Algorithm:
		// a). Get request from queue
		// b). if "get resources" AND (all required resources are free now)
		//     - mark resources as busy
		//     - send response
		//     else - save request into temporary list
		// b). if "free resources"
		//     - mark resources as free
		//     - send response
		//     - test temporary list (for successful requirements)
	}

}
