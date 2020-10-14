package lesson5;

import java.util.concurrent.Exchanger;

public class PipeExercises {
	public static final Exchanger<Integer>	st1 = new Exchanger<>();
	public static final Object				st2s = new Object();
	public static final Exchanger<Integer>	st2 = new Exchanger<>();
	public static final Object				st3s = new Object();
	public static final Exchanger<Integer>	st3 = new Exchanger<>();
	public static final Object				st4s = new Object();
	public static final Exchanger<Integer>	st4 = new Exchanger<>();

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// There is a pipe with 3 steps:
		// 
		for (int index = 1; index <= 2; index++) {
			final Thread	t = new Thread(()->{
								try{for (;;) {
										int	source;
										
										synchronized(st1) {
											// stop<-
											source = st1.exchange(null);
										}
										source = step1(source);
										synchronized(st2s) {
											// stop->
											st2.exchange(source);
										}
									}
								} catch (InterruptedException e) {
								}
							});
			t.setDaemon(true);
			t.start();
		}
		for (int index = 1; index <= 5; index++) {
			final Thread	t = new Thread(()->{
								try{for (;;) {
										int	source;
										
										synchronized(st2) {
											source = st2.exchange(null);
										}
										source = step2(source);
										synchronized(st3s) {
											st3.exchange(source);
										}
									}
								} catch (InterruptedException e) {
								}
							});
			t.setDaemon(true);
			t.start();
		}
		for (int index = 1; index <= 3; index++) {
			final Thread	t = new Thread(()->{
								try{for (;;) {
										int	source;
										
										synchronized(st3) {
											source = st3.exchange(null);
										}
										source = step3(source);
										synchronized(st4s) {
											st4.exchange(source);
										}
									}
								} catch (InterruptedException e) {
								}
							});
			t.setDaemon(true);
			t.start();
		}
		
		final Thread	t = new Thread(()-> {
							for (int index = 0; index < 100; index++) {
								try{st1.exchange(index);
								} catch (InterruptedException e) {
								}
							}
						});
		t.start();
		
		for (int index = 0; index < 100; index++) {
			System.err.println("Result="+st4.exchange(null));
		}
	}

	static int step1(int source) {
		try{Thread.sleep(1000);		// 500 * 2
		
			return -source;
		} catch (InterruptedException e) {
			return 0;
		}
	}
	
	static int step2(int source) {
		try{Thread.sleep(2500);		// 500 * 5
		
			return source*source;
		} catch (InterruptedException e) {
			return 0;
		}
	}

	static int step3(int source) {
		try{Thread.sleep(1500);		// 500 * 3
		
			return source + 10;
		} catch (InterruptedException e) {
			return 0;
		}
	}
}
