
package com.SortAlgo.Algorithms;

import com.VisualizerHelper.DrawComponents;

/*
 * <HeapSort Algorithm>
 * @author : aditya.shahi
 * 
 * Average Case : O(nlOGN)
 * Worst Case : O(nlOGN)
 * Best Case : O(n) (When array is already sorted)
 * 
 * For the heapify step, we're examining every item in the tree and moving it downwards until it's larger than its children. Since our tree height is O(\lg(n))O(lg(n)),
 *  we could do up to O(\lg(n))O(lg(n)) moves. Across all nn nodes, that's an overall time complexity of O(n\lg(n))O(nlg(n)).
 *  
 *  After transforming the tree into a heap, we remove all nn elements from it—one item at a time. Removing from a heap takes O(\lg(n))O(lg(n)) time, since we have
 *   to move a new value to the root of the heap and bubble down. Doing nn remove operations will be O(n\lg(n))O(nlg(n)) time.
 *   
 *   Putting these steps together, we're at O(n\lg(n))O(nlg(n)) time in the worst case (and on average).
    But what happens if all the items in the input are the same?

   Every time we remove an element from the tree root, the item replacing it won't have to bubble down at all. In that case, each remove takes O(1)O(1) time, and doing nn remove operations takes O(n)O(n).
 
   So the best case time complexity is O(n)O(n). This is the runtime when everything in the input is identical.

   Since we cleverly reused available space at the end of the input array to store the item we removed, we only need O(1)O(1) space overall for heapsort.
 */

public class HeapSort implements IDynamicAlgorithm{

	private int eachStep; //time in millis
	DrawComponents drawComp = null;

	@Override
	public void sortArray(DrawComponents draw, String stepRate) {
		eachStep = getDefaultTimeStep();
		if(stepRate != null && !stepRate.isEmpty()) {
			eachStep = Integer.parseInt(stepRate);
		}
		int[] array = draw.displayArray;
		drawComp = draw;
		sort(array);
	}

	
	public void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr, n, i); 
  
        // One by one extract an element from heap 
        //Swap first and last node and delete the last node from the heap
        for (int i = n-1; i > 0; i--) 
        { 
            // Move current root to end 
            drawComp.swap(0, i, eachStep, true);
            
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
	//build max heap
    void heapify(int arr[], int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
        	drawComp.swap(i, largest, eachStep, true);
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    } 
    
	@Override
	public int getDefaultTimeStep() {
		return 5;
	}
}
