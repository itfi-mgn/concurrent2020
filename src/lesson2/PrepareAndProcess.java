package lesson2;

import java.util.concurrent.Exchanger;

public class PrepareAndProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. There is "preparator" thread and "processor" thread
		// 2. Preparator prepares data and send it to processor
		// 3. Processor processes data and send processed data back to preparator
		// 4. Preparator uses data processed
		final Exchanger<Integer>	ex = new Exchanger<>();
//		final Exchanger<Integer>	ex2 = new Exchanger<>();
		
		Thread	prep = new Thread(()-> {
					try{for (int index = 0; index < 10; index++) {
							Thread.sleep(1000);	// <2a>
							int result = ex.exchange(index);	// <2b>, <3>
							System.err.println("result="+result); // <4>
						}
					} catch (InterruptedException e) {
					}
				});
		Thread	proc = new Thread(()-> {
					int	x = 0;
					
					try{for (int index = 0; index < 10; index++) {
							x = ex.exchange(x);
							Thread.sleep(2000);
							x = x*x;
						}
					} catch (InterruptedException e) {
					}
				});
		prep.start();
		proc.start();
	}

}
