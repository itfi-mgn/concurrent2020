package lesson6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class ResourceDispatcher {
	private final BlockingQueue<Request>	queue = new ArrayBlockingQueue<>(10);
	private final Set<Integer>				resources = new HashSet<>();
	private final List<Request>				postponed = new ArrayList<>();
	
	public ResourceDispatcher(final int... initialResources) {
		for (int item : initialResources) {
			resources.add(item);
		}
		
		Thread	t = new Thread(()-> {
			for (;;) {
				try{final Request	rq = queue.take();
					process(rq);
				} catch (InterruptedException e) {
					break;
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}

	public void lock(final int... resources) throws InterruptedException {
		final Request	rq = new Request(RequestType.ALLOCATE,resources);
		queue.put(rq);
		rq.wait.acquire();
	}

	public void unlock(final int... resources) throws InterruptedException {
		final Request	rq = new Request(RequestType.FREE,resources);
		queue.put(rq);
	}

	private void process(final Request rq) {
		switch (rq.type) {
			case ALLOCATE	:
				if (allPresents(rq.resources)) {
					allocateAndNotify(rq);
				}
				else {
					postponed.add(rq);
				}
				break;
			case FREE	:
				for (int item : rq.resources) {
					resources.add(item);
				}
				for (int index = postponed.size()-1; index >= 0; index--) {
					if (allPresents(postponed.get(index).resources)) {
						allocateAndNotify(postponed.remove(index));
					}
				}
				break;
			default:
				break;
		}
	}
	
	private boolean allPresents(final int... list) {
		boolean	allPresents = true;
		
		for (int item : list) {
			allPresents &= resources.contains(item);
		}
		return allPresents;
	}
	
	private void allocateAndNotify(Request rq) {
		for (int item : rq.resources) {
			resources.remove(item);
		}
		rq.wait.release();
	}
	
	private enum RequestType {
		ALLOCATE,
		FREE
	}
	
	private static class Request {
		final RequestType	type;
		final int[]			resources;
		final Semaphore		wait = new Semaphore(0);
		
		Request(final RequestType type, final int... resources) {
			this.type = type;
			this.resources = resources;
		}
	}
}
