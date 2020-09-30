package lesson3;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final CountDownLatch	cdl = new CountDownLatch(5);
	
		final Thread			t1 = new Thread(()-> {
										try{cdl.await();
											System.err.println("test 1");
										} catch (InterruptedException e) {
										}
								}); 
		final Thread			t2 = new Thread(()-> {
										try{cdl.await();
											System.err.println("test 2");
										} catch (InterruptedException e) {
										}
								});
		final Thread			t3 = new Thread(()-> {
										try{cdl.await();
											System.err.println("test 3");
										} catch (InterruptedException e) {
										}
								});
		
		t1.start();
		t2.start();
		for (int index = 0; index < 5; index++) {
			Thread.sleep(1000);
			System.err.println("Index="+index);
			cdl.countDown();
		}
		t3.start();
	}

}
