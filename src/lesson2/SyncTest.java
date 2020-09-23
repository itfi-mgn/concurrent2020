package lesson2;

import java.util.concurrent.Exchanger;

public class SyncTest {
	public static int	value = 0;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final Exchanger<Integer>	ex = new Exchanger<>();
		
		Thread	t1 = new Thread(()->{
						value = 10;
						try {
							/* y =*/ ex.exchange(value/* <- x*/);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		Thread	t2 = new Thread(()->{
						try {int val = /*x =*/ ex.exchange(null/* <- y*/);
							System.err.println("Value="+value);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}

}
