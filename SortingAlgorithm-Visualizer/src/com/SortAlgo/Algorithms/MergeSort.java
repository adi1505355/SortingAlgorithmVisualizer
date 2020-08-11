package com.SortAlgo.Algorithms;

import com.VisualizerHelper.DrawComponents;

/*
 * <MergeSort Algorithm>
 * @author : aditya.shahi
 * 
 * Avergae Case : O(nLogn)
 * Worst Case : O(nLogn)
 * Best Case : O(nLogn) 
 * Since in every case we are dividing the array, in the end we get a perfectly balanced binary tree of height Log(N). 
 * Not at each level it takes N steps to combine , hence the complexity is O(NLogN) for all the cases.
 * 
 * Space Complexity : O(N)
 */

public class MergeSort implements IDynamicAlgorithm{

	private int eachStep; //time in millis
	DrawComponents drawComp = null;

	@Override
	public void sortArray(DrawComponents draw, String stepRate) {
		eachStep = getDefaultTimeStep();
		if(stepRate != null && !stepRate.isEmpty()) {
			eachStep = Integer.parseInt(stepRate);
		}
		int[] array = draw.displayArray;
		int length = array.length; 
		drawComp = draw;
		sort(array, 0 ,length -1);
	}

	@Override
	public int getDefaultTimeStep() {
		return 10;
	}
	
	 // Merges two subarrays of arr[]. 
    // First subarray is arr[l..m] 
    // Second subarray is arr[m+1..r] 
    void merge(int arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; //due to case l=0,m=0,r=1
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int[n1]; 
        int R[] = new int[n2]; 
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j = 0; j < n2; ++j) 
            R[j] = arr[m + 1 + j]; //due to  case l=0,m=0,r=1
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) { 
               // arr[k] = L[i]; 
            	drawComp.updateSingle(k, L[i], eachStep, false,false);
                i++; 
            } 
            else { 
                //arr[k] = R[j]; 
            	drawComp.updateSingle(k, R[j], eachStep, false,false);
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) { 
            //arr[k] = L[i]; 
        	drawComp.updateSingle(k, L[i], eachStep, false,false);
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) { 
          //  arr[k] = R[j]; 
        	drawComp.updateSingle(k, R[j], eachStep, false,false);
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts arr[l..r] using 
    // merge() 
    void sort(int[] arr, int l, int r) 
    { 
        if (l < r) { 
            // Find the middle point 
        	//divide and conquer approach
            int m = (l + r) / 2; 
  
            // Sort first and second halves 
            sort(arr, l, m); 
            sort(arr, m + 1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    } 
  	
}
