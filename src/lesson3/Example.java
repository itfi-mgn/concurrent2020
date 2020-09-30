package lesson3;

public class Example {
	public static int	x = 10;
	
	public static int[]	content = {10,22,-3,45,64,78,1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. there are three threads:
		// 1.1. - thread calculates x = 2 * x
		// 1.2. - thread calculates x = -x
		// 1.3. - thread calculates x = x + 1
		// 1.4. - master thread
		// 2. Using semaphore(s), calculate 2 * -(x + 1) expression
		
		// 1. There are two threads:
		// 1.1. 'even' thread (calculates sum of even elements of content array)
		// 1.2. 'odd' thread (calculates sum of odd elements of content array)
		// 1.3. Master thread
		// 2. Using countdown, calculate total sum of the content array
		CountDown	cdStart = // 1
		CountDown	cdEnd = // 2 items
		
		// thread: 
		// cdStart.await();
		// todo:  
		// cdEnd.countDown()
		// prepare array
		// cdStart.countDown()
		// todo:
		// cdEnd.await()
		
		
		
	}

}
