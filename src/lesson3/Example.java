package lesson3;

public class Example {
	public static int	x = 10;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. there are three threads:
		// 1.1. - thread calculates x = 2 * x
		// 1.2. - thread calculates x = -x
		// 1.3. - thread calculates x = x + 1
		// 1.4. - master thread
		// 2. Using semaphore(s), calculate 2 * -(x + 1) expression
	}

}
