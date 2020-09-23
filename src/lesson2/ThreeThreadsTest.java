package lesson2;

import java.util.concurrent.Exchanger;

public class ThreeThreadsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. There are three threads "emitters"
		// 2. There is one thread "collector"
		// 3. Every emitter produces sequential value and send it to collector
		// 4. Collector gathers all the values and calculates it's sum
		final Object				sync = new Object();
		final Exchanger<Integer>	ex = new Exchanger<>();
		
		for (int index = 0; index < 3; index++) {
			final Thread	t = new Thread(()-> {
								for (int i = 0; i < 5; i++) {
									try{
										synchronized(sync) {	// try {monitorenter sync
											ex.exchange(i);
										}					// } finally {monitorexit sync}
										System.err.println(Thread.currentThread().getName()
															+" sent");
									} catch (InterruptedException e) {
										break;
									}
								}
							});
			t.start();
		}
		
		final Thread	t = new Thread(()-> {
			int	sum = 0;
			
			for (int i = 0; i < 15; i++) {
				try{
					sum += ex.exchange(i);
					System.err.println(Thread.currentThread().getName()
							+" receive");
				} catch (InterruptedException e) {
					break;
				}
			}
			System.err.println("Sum="+sum);
		});
		t.start();
		
	}

}
