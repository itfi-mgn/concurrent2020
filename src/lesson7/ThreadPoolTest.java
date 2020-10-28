package lesson7;

import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public class ThreadPoolTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final ExecutorService 	pool = Executors.newFixedThreadPool(2,new ThreadFactory() {
											@Override
											public Thread newThread(Runnable r) {
												final Thread	t = new Thread(r);
												
												t.setName("sdfsd");
												t.setDaemon(true);
												return t;
											}
										});
		Timer	t;
		
		Future<String> f = pool.submit(new Callable<String>() { // nerw Thread().start()
								@Override
								public String call() throws Exception {
									throw new RuntimeException("dsmklfjdklsfjklsdjfkljsdfkljfd"); 
									//return "Hello world";
								}
							});
		// TODO ... 
		try{
			System.err.println("result="+f.get());
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		pool.isShutdown();
	}

}
