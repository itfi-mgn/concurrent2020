package lesson4;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Exercises {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// 1. Calculate number of bytes in all files for directory c:/windows
		// 2. Make the same with 4 threads
		final long	startTimeSeq = System.currentTimeMillis();
		
		System.err.println("size="+calc(new File("c:/windows"))+", duration="+(System.currentTimeMillis()-startTimeSeq));

		final long		startTimeConcur = System.currentTimeMillis();
		final File[]	content = new File("c:/windows").listFiles();
		final File[][]	pieces = splitContent(content,4);
		final CountDownLatch	start = new CountDownLatch(1);
		final CountDownLatch	finish = new CountDownLatch(4);
		
		for (int index = 0; index < pieces.length; index++) {
			final File[]	toDo = pieces[index]; 
			final Thread	t = new Thread(()->{
				
								try{start.await();
									long	total = 0;
									
									for (File f : toDo) {
										total += calc(f);
									}
									System.err.println("size="+total);
								} catch (InterruptedException e) {
								} finally {
									finish.countDown();
								}
							});
			t.start();
		}
		start.countDown();
		finish.await();
		System.err.println("Concurrent duration="+(System.currentTimeMillis()-startTimeConcur));
	}

	public static long calc(final File f) {
		if (f.isFile()) {
			return f.length();
		}
		else {
			long[]			total = new long[] {0};
			
			f.listFiles((File n)-> {
				total[0] += calc(n);
				return false;
			});
			return total[0];
		}
	}

	private static File[][] splitContent(final File[] content, final int pieces) {
		final File[][]	result = new File[pieces][];
		final int		pieceSize = content.length / pieces;
		
		for (int index = 0; index < pieces; index++) {
			result[index] = Arrays.copyOfRange(content,index*pieceSize,(index+1)*pieceSize);
		}
		return result;
	}
}
