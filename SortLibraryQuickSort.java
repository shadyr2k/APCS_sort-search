import java.util.Arrays;
import java.util.Random;
public class SortLibraryQuickSort {
	
	public static void main(String[] args) {
		int[] random = new int[]{33,94,9,40,77,82,47,15,51,64,76,28,2,85,11};
		int[] alreadySorted = new int[]{2,9,11,15,28,33,40,47,51,64,76,77,82,85,94};
		int[] reversed = new int[]{94,85,82,77,76,64,51,47,40,33,28,15,11,9,2};
		int[] mostlySorted = new int[]{2,85,11,15,28,33,47,40,51,64,76,77,82,9,94};
		int[] longerArray = ArrayImporter.readArrayFile("smallArray.txt");  //***beware repeating elements! 
		int[] myCustomTest = new int[]{5,3,69,73,11,17,1,74,34,86}; //***use this one
		int[] repeatingTest = new int[]{5,3,8,1,7,7,8,5,10};
		
		// ***Enter your array to sort here
		int[] arrayToSort = myCustomTest; // arrayToSort will point to the array you choose
		int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
		
		long startTime1 = System.currentTimeMillis();
		quickSort(arrayToSort);		// Call your sort method -- Remember array is modified in the method, not returned!
		long stopTime1 = System.currentTimeMillis();
		
		long startTime2 = System.currentTimeMillis();
		Arrays.sort(copyOfArrayToSort);	// call java.util.Array's sort method for comparison (Dual Pivot Quicksort)
		long stopTime2 = System.currentTimeMillis();
		
		if(arrayToSort.length < 50) {
			System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
			System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
		}
		
		System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));
		System.out.println("Time 1: " + (stopTime1 - startTime1) + " ms");
		System.out.println("Time 2: " + (stopTime2 - startTime2) + " ms");
	}
	
	// wrapper (runner) method
	public static void quickSort(int[] nums) {
		sort(nums, 0, nums.length - 1);
	}
	
	// recursive method, pass state through parameters
	private static void sort(int[] nums, int loIndex, int hiIndex) {
		// base case, when to stop?
		if(hiIndex <= loIndex) return; //if the array gets to length 0 basically it's finished iterating through all the elements
			
		//partition int (using random element)
		Random r = new Random();
		//System.out.println("beginning partition step from index " + loIndex + " to " + hiIndex); => debug 
		int p = partition(nums, loIndex, hiIndex, r.nextInt(hiIndex - loIndex) + loIndex); 
		
		// recursive calls
		sort(nums, 0, p-1); //sort the array before the pivot
		sort(nums, p+1, hiIndex); //sort the array after the pivot
	}
	
	
	
	// iterative (i think this works for repeats???)
	private static int partition(int[] nums, int loIndex, int hiIndex, int pivotIndex) {
		//System.out.println(Arrays.toString(nums)); => debug
		//
		int pivot = nums[pivotIndex];
		//System.out.println("partition chosen: " + pivot + " @ index " + pivotIndex); => debug
		int i = loIndex - 1;
		int k = hiIndex + 1;
		while(true) { //no condition needed bc we will always break out of it at one point
			//find some number on left to swap 
			do { //do while loop because it should increment before moving onto the next element
				if(i > 0 && nums[i] == pivot && i == pivotIndex) break; //break out if it's about to pass the pivot 
																		//but not if it's a repeat number since we don't 
																		//really care if it's a repeat it just can't be confused 
																		//with the pivot
				i++;
				//System.out.println("i = " + i); => debug
			} while(nums[i] < pivot);
			
			//find some number on right to swap
			do {
				if(k <= hiIndex && nums[k] == pivot && k == pivotIndex) break; //same deal here
				k--; 
				//System.out.println("k = " + k); => debug
			} while(nums[k] > pivot);
			
			if(i >= k) break; //if the pointers ever cross then break out of the while loop 
			
			//pivotIndex updating so it always knows which one was the original pivot and which ones are imposters
			if(nums[i] == pivot && i == pivotIndex) {
				//System.out.println("swapping with pivot - new pivot index: " + k);
				pivotIndex = k; 
			} else if(nums[k] == pivot && k == pivotIndex) {
				//System.out.println("swapping with pivot - new pivot index: " + k);
				pivotIndex = i;
			}
			
			//System.out.println("swapping: " + nums[i] + " (index " + i + ") and " + nums[k] + " (index " + k + ")"); => debug
			//if all above tests pass, swap the two elements 
			swap(nums, i, k);
			//System.out.println("resulting array: " + Arrays.toString(nums)); => debug
		}
		return pivotIndex; // this is important! yes it is!
	}
	
	//simple swapping method
	public static void swap(int[] nums, int p, int q) {
		int t = nums[p];
		nums[p] = nums[q];
		nums[q] = t;
	}


    

	

    

	

	
	
	
	
	
	
}
