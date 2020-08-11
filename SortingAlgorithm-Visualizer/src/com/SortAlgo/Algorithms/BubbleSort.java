package com.SortAlgo.Algorithms;

import com.VisualizerHelper.DrawComponents;

/*
 * <SelectionSort Algorithm>
 * @author : aditya.shahi
 * 
 * 
 * Worst Case : O(n^2)
 * Best Case : O(n) (When array is already sorted) - in this case use a flag to see swapping
 * happened in first pass or not, if did not, then terminate
 */

public class BubbleSort implements IDynamicAlgorithm{

	private int eachStep; //time in millis
	
	@Override //recursive bubble
	public void sortArray(DrawComponents draw, String stepRate) {
		eachStep = getDefaultTimeStep();
		if(stepRate != null && !stepRate.isEmpty()) {
			eachStep = Integer.parseInt(stepRate);
		}
		int[] array = draw.displayArray;
		int length = array.length; 
		bubbleRecursive(array,length,0,draw);
	}
	
	//recusrive approach issues - stack overflow error
	void bubbleRecursive(int[] array, int length, int i,DrawComponents draw) {
		if(i == length) {
			return;
		}
		for (int j = 0; j < length-1-i; j++) {
			if (array[j] > array[j+1]) {
				draw.swap(j, j+1, eachStep, true);
			}
		}
		bubbleRecursive( array,  length,  i++, draw);

	}

	/*@Override
	public void sortArray(DrawComponents draw, String stepRate) {
		eachStep = getDefaultTimeStep();
		if(stepRate != null && !stepRate.isEmpty()) {
			eachStep = Integer.parseInt(stepRate);
		}
		int[] array = draw.displayArray;
		int length = array.length; 
		for (int i = 0; i < length -1 ; i++) {
			for (int j = 0; j < length-1-i; j++) {
				if (array[j] > array[j+1]) {
					draw.swap(j, j+1, eachStep, true);
				}
			}
		}
	}*/

	@Override
	public int getDefaultTimeStep() {
		return 5;
	}
	
	
}
