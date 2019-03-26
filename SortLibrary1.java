import java.util.Arrays;
import java.util.Random;
public class SortLibrary1 {
	
	public static void main(String[] args) {
		int[] random = new int[]{33,94,9,40,77,82,47,15,51,64,76,28,2,85,11};
		int[] alreadySorted = new int[]{2,9,11,15,28,33,40,47,51,64,76,77,82,85,94};
		int[] reversed = new int[]{94,85,82,77,76,64,51,47,40,33,28,15,11,9,2};
		int[] mostlySorted = new int[]{2,85,11,15,28,33,47,40,51,64,76,77,82,9,94};
		int[] longerArray = ArrayImporter.readArrayFile("smallArray.txt");   
		int[] myCustomTest = new int[]{5,3,69,73,11,17,1,74,34,86}; 
		int[] repeatingTest = new int[]{1, 3, 56, 2, 3, 17, 4, 29, 3, 33, 24, 16, 10, 3, 2, 6, 9};
		
		// ***Enter your array to sort here
		int[] arrayToSort = repeatingTest; 
		int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
		
		long startTime1 = System.currentTimeMillis();
		quickSort(arrayToSort);		
		long stopTime1 = System.currentTimeMillis();
		
		long startTime2 = System.currentTimeMillis();
		Arrays.sort(copyOfArrayToSort);	
		long stopTime2 = System.currentTimeMillis();
		
		if(arrayToSort.length < 50) {
			System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
			System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
		}
		
		System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));
		System.out.println("Time 1: " + (stopTime1 - startTime1) + " ms");
		System.out.println("Time 2: " + (stopTime2 - startTime2) + " ms");
	}
	
	public static void quickSort(int[] nums) {
		sort(nums, 0, nums.length - 1);
	}
	
	private static void sort(int[] nums, int loIndex, int hiIndex) {
		if(hiIndex <= loIndex) return;
		Random r = new Random();
		int p = partition(nums, loIndex, hiIndex, r.nextInt(hiIndex - loIndex) + loIndex); 
		sort(nums, 0, p-1);
		sort(nums, p+1, hiIndex); 
	}
	
	private static int partition(int[] nums, int loIndex, int hiIndex, int pivotIndex) {
		int pivot = nums[pivotIndex];
		int i = loIndex - 1;
		int k = hiIndex + 1;
		while(true) {
			do { 
				if(i > 0 && nums[i] == pivot && i == pivotIndex) break; 
				i++;
			} while(nums[i] < pivot);

			do {
				if(k <= hiIndex && nums[k] == pivot && k == pivotIndex) break; //same deal here
				k--; 
			} while(nums[k] > pivot);
			
			if(i >= k) break; 
			if(nums[i] == pivot && i == pivotIndex) pivotIndex = k; 
			else if(nums[k] == pivot && k == pivotIndex) pivotIndex = i;

			swap(nums, i, k);

		}
		return pivotIndex; 
	}

	public static void swap(int[] nums, int p, int q) {
		int t = nums[p];
		nums[p] = nums[q];
		nums[q] = t;
	}


    

	

    

	

	
	
	
	
	
	
}
