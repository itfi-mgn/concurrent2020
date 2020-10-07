package lesson4;

import java.util.Arrays;

public class ConcurrentSortMerge {

	public static void main(String[] args) {
		final int[]		ra = createRandomArray(), raCopy = ra.clone();
		final long		startTimeSeq = System.currentTimeMillis();
		
		sort(ra);
		System.err.println("Sequential sort: "+(System.currentTimeMillis()-startTimeSeq));
		
		final long		startTimeConcur = System.currentTimeMillis();
		final int[][]	splitArray = splitContent(raCopy,2);

		for (int[] piece : splitArray) {
			sort(piece);
		}
		merge(splitArray);
		System.err.println("Concurrent sort: "+(System.currentTimeMillis()-startTimeConcur));
	}

	private static int[] merge(final int[][] content) {
		if (content.length == 1) {
			return content[0];
		}
		else {
			final int[][]	temp = new int[content.length/2][];
			
			for (int index = 0; index < temp.length; index++) {
				temp[index] = merge(content[2*index],content[2*index+1]);
			}
			return merge(temp);
		}
	}

	private static int[] createRandomArray() {
		final int[]	result = new int[4*1024*1024*4];
		
		for (int index = 0; index < result.length; index++) {
			result[index] = (int) (1000000000 * Math.random());
		}
		return result;
	}
	
	private static void sort(final int[] content) {
		// TODO: make sort of the array
		Arrays.parallelSort(content);
	}

	private static int[][] splitContent(final int[] content, final int pieces) {
		final int[][]	result = new int[pieces][];
		final int		pieceSize = content.length / pieces;
		
		for (int index = 0; index < pieces; index++) {
			result[index] = Arrays.copyOfRange(content,index*pieceSize,(index+1)*pieceSize);
		}
		return result;
	}
	
	private static int[] merge(final int[] left, final int[] right) {
		final int[]	result = new int[left.length+right.length];
		int			leftIndex = 0, rightIndex = 0, resultIndex = 0;
		
		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex] <= right[rightIndex]) {
				result[resultIndex++] = left[leftIndex++]; 
			}
			else {
				result[resultIndex++] = right[rightIndex++]; 
			}
		}
		while (leftIndex < left.length) {
			result[resultIndex++] = left[leftIndex++]; 
		}
		while (rightIndex < right.length) {
			result[resultIndex++] = right[rightIndex++]; 
		}
		return result;
	}
}
