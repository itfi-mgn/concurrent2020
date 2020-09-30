package lesson3;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final Semaphore		sema = new Semaphore(2);
		Thread				t1 = new Thread(()-> {
								try{sema.acquire();
									System.err.println("Thread: "+Thread.currentThread().getName());
									Thread.sleep(5000);
									sema.release();
								} catch (InterruptedException e) {
								}
							});
		Thread				t2 = new Thread(()-> {
								try{sema.acquire();
									System.err.println("Thread: "+Thread.currentThread().getName());
									Thread.sleep(5000);
									sema.release();
								} catch (InterruptedException e) {
								}
							});
		t1.start();
		t2.start();
		
		final Semaphore		startstop = new Semaphore(0);
		
		final Thread		tFirst = new Thread(()-> {
									try{startstop.acquire();
									
										System.err.println("Continue");
									} catch (InterruptedException e) {
									}
							});
		final Thread		tSecond = new Thread(()-> {
									System.err.println("Enable");
									startstop.release();
							});
		tSecond.start();
		Thread.sleep(2000);
		tFirst.start();
		
		
		
		
	}
}
