package lesson7;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class MyTestLock {

	public static void main(String[] args) throws InterruptedException {
		final ReentrantReadWriteLock	rw = new ReentrantReadWriteLock();
		final double 	bound = 0.9;
		final Thread[]	threads = new Thread[10];
		
		for (int index = 0; index < threads.length; index++) {
			threads[index] = new Thread(()-> {
				for (int count = 0; count < 1000; count++) {
					final double scratch = Math.random();
					
					if (scratch < bound) {
						final ReadLock	r = rw.readLock();
						
						try{r.lock();
							process();
						} finally {
							r.unlock();
						}
					}
					else {
						final WriteLock	w = rw.writeLock();
						
						try{w.lock();
							process();
						} finally {
							w.unlock();
						}
					}
				}
			});
		}
	
		final long	startTime = System.currentTimeMillis();
		
		for (Thread t : threads) {
			t.setDaemon(true);
			t.start();
		}
		for (Thread t : threads) {
			t.join();
		}
		System.err.println("Duration = "+(System.currentTimeMillis() - startTime));
		
		final long	startTime2 = System.currentTimeMillis();
		
		for (int index = 0; index < threads.length; index++) {
			threads[index] = new Thread(()-> {
				for (int count = 0; count < 1000; count++) {
					synchronized(rw) {
						process();
					}
				}
			});
		}		
		for (Thread t : threads) {
			t.setDaemon(true);
			t.start();
		}
		for (Thread t : threads) {
			t.join();
		}
		System.err.println("Duration 2 = "+(System.currentTimeMillis() - startTime2));
	}

	static void process() {
//		try{Thread.sleep(1);
//		} catch (InterruptedException e) {
//		}
	}
}
