package lesson5;

public class PipeExercises {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// There is a pipe with 3 steps:
		// 

		for (int index = 0; index < 100; index++) {
			System.err.println("Result="+processPipe(index));
			// < 1 sec
		}
	}
	
	static int processPipe(int source) {
		// TODO:
		return step3(step2(step1(source)));
	}

	static int step1(int source) {
		try{Thread.sleep(1000);
		
			return -source;
		} catch (InterruptedException e) {
			return 0;
		}
	}
	
	static int step2(int source) {
		try{Thread.sleep(2500);
		
			return source*source;
		} catch (InterruptedException e) {
			return 0;
		}
	}

	static int step3(int source) {
		try{Thread.sleep(1500);
		
			return source + 10;
		} catch (InterruptedException e) {
			return 0;
		}
	}
}
