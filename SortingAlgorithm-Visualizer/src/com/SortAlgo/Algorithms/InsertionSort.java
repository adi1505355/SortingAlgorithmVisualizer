package com.SortAlgo.Algorithms;

import com.VisualizerHelper.DrawComponents;

/*
 * <InsertionSort Algorithm>
 * @author : aditya.shahi
 * 
 * 
 * Worst Case : O(n^2) : It is recommended to use binary insertion sort, which internally uses
 * binary search to find the best position of the key to be inserted : O(nLogn)
 * Best Case : O(n) (When array is already sorted)
 * 
 * This algo is very time taking and is generally used if array is almost sorted.
 */

public class InsertionSort implements IDynamicAlgorithm{

	private int eachStep; //time in millis

	@Override
	public void sortArray(DrawComponents draw, String stepRate) {
		eachStep = getDefaultTimeStep();
		if(stepRate != null && !stepRate.isEmpty()) {
			eachStep = Integer.parseInt(stepRate);
		}
		int[] array = draw.displayArray;
		int length = array.length; 
		for (int i = 1; i < length ; i++) {
			int key = array[i];
			int j = i-1;
			while(j>=0 && array[j]>key) {  // the logic here to be changed for binary insertion
				draw.updateSingle(j+1, array[j], eachStep, false,false);
				j--;
			}
			draw.updateSingle(j+1, key, eachStep, false,false);
		}
	}

	@Override
	public int getDefaultTimeStep() {
		return 1;
	}
}
