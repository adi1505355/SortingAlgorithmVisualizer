package com.SortAlgo.Algorithms;

import com.VisualizerHelper.DrawComponents;


/*
 * <QuickSort Algorithm>
 * @author : aditya.shahi
 * 
 * 
 * InPlace Algorithm
 * 
 * Avergae Case : O(nLogn)
 * Worst Case : O(n^2)
 * Best Case : O(nLogn) 
 * 
 * Space Complexity : O(N)
 * 
 *  Since quicksort by definition involves recursion, you must consider the space that will be taken up by the
 *  call stack. In the worst case, quicksort will be called one time for every element of the list since a 
 *  solution could recurse all the way down to single element lists. So, there must be a call stack entry for 
 *  every one of these function calls. This means that with a list of n integers, at worst there will be n stack
 *   entries created, hence O(n) space complexity.However, disregarding the call stack, 
 *   the algorithm is in-place, meaning no extra space is used. There are no extra arrays floating around.
 *   
 *   Reference @geeksForgeeks : https://www.geeksforgeeks.org/quicksort-better-mergesort/
 */

public class QuickSort implements IDynamicAlgorithm{

	private int eachStep; //time in millis
	DrawComponents drawComp = null;

	@Override
	public void sortArray(DrawComponents draw, String stepRate) {
		eachStep = getDefaultTimeStep();
		if(stepRate != null && !stepRate.isEmpty()) {
			eachStep = Integer.parseInt(stepRate);
		}
		int[] array = draw.displayArray;
		draw.displayArray = new int[5];
		int length = array.length; 
		drawComp = draw;
		quickSort(array, 0 ,length -1);
	}

	@Override
	public int getDefaultTimeStep() {
		return 10;
	}

	private int partition(int[] array, int lowIndex, int highIndex) {
		int pivotValue = array[highIndex];
		int i = lowIndex - 1;
		for (int j = lowIndex; j <= highIndex - 1; j++) {
			if (array[j] <= pivotValue) {
				i++;
				drawComp.swap(i, j, eachStep, true);
			}
		}
		drawComp.swap(i + 1, highIndex, eachStep, true);//swapping again to put pivot at required place
		return i+1;
	}


	private void quickSort(int[] array, int lowIndex, int highIndex) {
		if (lowIndex < highIndex) {
			int pivotPoint = partition(array, lowIndex, highIndex);
			quickSort(array, lowIndex, pivotPoint - 1);
			quickSort(array, pivotPoint + 1, highIndex);
		}
	}

}
