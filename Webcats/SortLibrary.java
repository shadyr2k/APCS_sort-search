
import java.util.Arrays;
import java.util.Collections;

public class SortLibrary {

	public static void main(String[] args) {
		// Test arrays you can use to check your sorts.
		// They represent common arrangements: random, already sorted, reversed, mostly sorted
		int[] random = new int[]{33,94,9,40,77,82,47,15,51,64,76,28,2,85,11};
		int[] alreadySorted = new int[]{2,9,11,15,28,33,40,47,51,64,76,77,82,85,94};
		int[] reversed = new int[]{94,85,82,77,76,64,51,47,40,33,28,15,11,9,2};
		int[] mostlySorted = new int[]{2,85,11,15,28,33,47,40,51,64,76,77,82,9,94};
		int[] longerArray = ArrayImporter.readArrayFile("smallArray.txt"); 
		int[] myCustomTest = new int[]{15, 23, 42, 4, 8, 16, 1};		
		
		// ***Enter your array to sort here
		int[] arrayToSort = random; // arrayToSort will point to the array you choose
		int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
		int[] random1 = Arrays.copyOf(random, random.length);
		
		// ***Enter which sort you want to test
		mergeSort(arrayToSort);		// Call your sort method -- Remember array is modified in the method, not returned!
		Arrays.sort(copyOfArrayToSort);	// call java.util.Array's sort method for comparison
		
		if(arrayToSort.length < 50) {
			System.out.println("Result before sort: " + Arrays.toString(random1));
			System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
			System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
		}
		
		System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));
		
	}
	
	// void normally would be OK.  Don't need to return int[] or anything.
	// However, I want you to keep track of and return the number of swaps.
	public static int bubbleSort(int[] nums) {
		int numSwaps = 0;
		for(int i = 0; i < nums.length - 1; ++i) {
			for(int k = 0; k < nums.length - 1 - i; ++k) {
				if(nums[k] > nums[k+1]) {
					numSwaps++;
					int temp = nums[k];
					nums[k] = nums[k+1];
					nums[k+1] = temp;
				}	
			}
			
		}
		return numSwaps;
	}
	
	// void is OK.  'unsorted' simply receives a copy of reference to the unsorted
	// array 'arrayToSort' when method is called.  When your method finishes, 
	// 'arrayToSort' will point to the sorted array
	public static void insertionSort(int[] unsorted) {
		for(int i = 1; i < unsorted.length; ++i) {
			int t = unsorted[i];
			for(int k = i-1; k >= 0; --k) {
				if(t < unsorted[k]) {
					int temp = unsorted[k];
					unsorted[k] = unsorted[k+1];
					unsorted[k+1] = temp;
				} else break;
			}
		}
	}
	
	public static void selectionSort(int[] nums) {
		int min = -1;
		int minIndex = 0;
		for(int i = 0; i < nums.length; ++i) {
			min = nums[i];
			minIndex = i;
			for(int k = i; k < nums.length; ++k) {
				if(nums[k] < min) {
					min = nums[k];
					minIndex = k;
				}
			}
			int temp = nums[i];
			nums[i] = nums[minIndex];
			nums[minIndex] = temp;
		}
	}
	
	public static void mergeSort(int[] nums) {
		if(nums.length <= 1) return;
		int mid = nums.length/2;
		int left = 0, right = 0, total = 0;
		int[] p1 = Arrays.copyOf(nums, mid); //partition 1
		int[] p2 = Arrays.copyOfRange(nums, mid, nums.length); //partition 2
		mergeSort(p1); mergeSort(p2);
		while(left < p1.length && right < p2.length) {
			if(p1[left] >= p2[right]) nums[total++] = p2[right++]; 
			else nums[total++] = p1[left++];
		}
		while(left < p1.length) nums[total++] = p1[left++];
		while(right < p2.length) nums[total++] = p2[right++];
	}
}
