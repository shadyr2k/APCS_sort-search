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
		int[] what = new int[] {33, 11, 9, 40, 28, 2, 47, 15, 51, 64};
		
		// ***Enter your array to sort here
		int[] arrayToSort = repeatingTest; // arrayToSort will point to the array you choose
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
		//System.out.println("beginning partition step from index " + loIndex + " to " + hiIndex);  
		int p = partition(nums, loIndex, hiIndex, r.nextInt(hiIndex - loIndex) + loIndex); 
		
		// recursive calls
		sort(nums, loIndex, p-1); //sort the array before the pivot
		sort(nums, p+1, hiIndex); //sort the array after the pivot
	}
	
	
	
	// iterative (i think this works for repeats???)
	private static int partition(int[] nums, int loIndex, int hiIndex, int pivotIndex) {
		int pivot = nums[pivotIndex];
		
		// DEBUG System.out.println("first swap: " + nums[pivotIndex] + " (index " + pivotIndex + ") and " + nums[loIndex] + " (index " + loIndex + ")");
		
		swap(nums, pivotIndex, loIndex); //put the pivot somewhere else for now because otherwise it's distracting to have it in the middle 
		
		//System.out.println(Arrays.toString(nums));  DEBUG
		//System.out.println("partition chosen: " + pivot + " @ index " + pivotIndex);  DEBUG
		
		int i = loIndex; //bc of the increment before check it would skip 0 but that's ok bc we know element 0 = pivot
		int k = hiIndex + 1; //this is +1 because it will increment down before checking the element
		while(true) { //no condition needed bc we will always break out of it at one point
			
			//find an element on left to swap
			do { 
				if(i == hiIndex) break; //in case i decides to increment past the array without seeing an element to swap
				i++;
				//System.out.println("i = " + i); DEBUG
			} while(nums[i] < pivot);

			//find an element on right to swap
			do {
				//no need for the check if k increments past 0 because we know 0 is the pivot
				k--; 
				//System.out.println("k = " + k); DEBUG
			} while(nums[k] > pivot); 
			
			if(i >= k) break; //if the two pointers cross paths we know it's done so break
			
			//System.out.println("swapping: " + nums[i] + " (index " + i + ") and " + nums[k] + " (index " + k + ")"); DEBUG
			
			//if all above tests pass, swap the two elements 
			swap(nums, i, k);
			
		}
		//DEBUG System.out.println("final swap: " + nums[k] + " (index " + k + ") and " + nums[loIndex] + " (index " + loIndex + ")");
		
		swap(nums, loIndex, k); //swap back the pivot with the element in the middle 
		
		//DEBUG System.out.println("resulting array: " + Arrays.toString(nums));
		
		return k;  // this is important! yes it is!
	}
	
	//simple swapping method
	public static void swap(int[] nums, int p, int q) {
		int t = nums[p];
		nums[p] = nums[q];
		nums[q] = t;
	}


    

	

    

	

	
	
	
	
	
	
}
