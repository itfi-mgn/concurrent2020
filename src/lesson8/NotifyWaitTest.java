package lesson8;

public class NotifyWaitTest {
	public static volatile boolean 	filled = false;
	public static volatile int 		x;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Object	obj = new Object();
		
		final Thread	t1 = new Thread(()-> {
							synchronized(obj) {
								for (;;) {
									if (filled) {
										System.err.println("X="+x);
										break;
									}
									else {
										try{/*monitorexit obj */ 
											obj.wait(); 
											/*monitorenter obj*/
										} catch (InterruptedException e) {
											System.err.println("Fail...");
											return;
										}
									}
								}
							}
						});
		final Thread	t2 = new Thread(()-> {
							synchronized(obj) {
								x=100;
								filled = true;
								obj.notifyAll();
							}
						});
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}
}
