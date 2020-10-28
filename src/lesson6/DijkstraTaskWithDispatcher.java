package lesson6;

public class DijkstraTaskWithDispatcher {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 5 chinese eating philosofists
		final ResourceDispatcher	rd = new ResourceDispatcher(0,1,2,3,4);
		final Thread[]	philosofists = new Thread[5];
		
		for (int index = 0; index < philosofists.length; index++) {
			final int 	stickIndex = index;
			
			philosofists[index] = new Thread(()-> {
							for (;;) {
								final int[]	resources = new int[] {stickIndex, (stickIndex + 1) % philosofists.length};
								
								try {rd.lock(resources);
									System.err.println("Eating "+Thread.currentThread().getName());
									eating();
								} catch (InterruptedException e) {
									return;
								} finally {
									try{rd.unlock(resources);
									} catch (InterruptedException e) {
										return;
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
