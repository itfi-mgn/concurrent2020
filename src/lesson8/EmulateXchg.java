package lesson8;

public class EmulateXchg {

	private volatile boolean	inside = false;
	private volatile Object[]	swap;
	
	public synchronized Object exchange(Object value) throws InterruptedException {
		Object[]	localswap;
		
		synchronized(this) {
			if (!inside) {
				swap = localswap = new Object[2];
				localswap[0] = value;
				inside = true;
				do {wait();} while (inside);
				return localswap[1];
			}
			else {
				localswap = swap;
				localswap[1] = value;
				inside = false;
				notifyAll();
				return localswap[0];
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Make exchanger implementation by synchronized, Object.wait() and Object.notifyAll();
		final EmulateXchg	ex = new EmulateXchg();

		for (int index = 0; index < 10; index++) {
			final Thread	t = new Thread(()-> {
								try {
									System.err.println("EX: "+Thread.currentThread().getName()
											+" and "+ex.exchange(Thread.currentThread().getName()));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							});
			t.start();
		}
	}

}
