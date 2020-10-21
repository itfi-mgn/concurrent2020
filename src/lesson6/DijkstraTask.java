package lesson6;

public class DijkstraTask {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 5 chinese eating philosofists
		final Object[]	sticks = {new Object(),new Object(),new Object(),new Object(),new Object()};
		final Thread[]	philosofists = new Thread[5];
		
		for (int index = 0; index < philosofists.length; index++) {
			final int 	stickIndex = index;
			
			philosofists[index] = new Thread(()-> {
							for (;;) {
								if (stickIndex == 4) {
									synchronized(sticks[(stickIndex + 1) % sticks.length]) {
										synchronized(sticks[stickIndex]) {
											System.err.println("Eating "+Thread.currentThread().getName());
											eating();
										}
									}
								}
								else {
									synchronized(sticks[stickIndex]) {
										synchronized(sticks[(stickIndex + 1) % sticks.length]) {
											System.err.println("Eating "+Thread.currentThread().getName());
											eating();
										}
									}
								}
								System.err.println("Thinking "+Thread.currentThread().getName());
								thinking();
							}
						});
			philosofists[index].start();
		}
	}
	
	static void eating() {
//		try{Thread.sleep(1000);
//		} catch (InterruptedException e) {
//		}
	}

	static void thinking() {
//		try{Thread.sleep(5000);
//		} catch (InterruptedException e) {
//		}
	}
}
