package com.SortAlgo.Algorithms;

import com.VisualizerHelper.DrawComponents;

/*
 * <SelectionSort Algorithm>
 * @author : aditya.shahi
 * 
 * WorstCase : O(n^2)
 * BestCase : O(n^2)
 * 
 */

public class SelectionSort implements IDynamicAlgorithm{

	private int eachStep; //time in millis

	@Override
	public void sortArray(DrawComponents draw, String stepRate) {
		eachStep = getDefaultTimeStep();
		if(stepRate != null && !stepRate.isEmpty()) {
			eachStep = Integer.parseInt(stepRate);
		}
		int[] array = draw.displayArray;
		int length = array.length; 
		for (int i = 0; i < length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}

			draw.swap(i, minIndex, eachStep, true);
		}
	}

	@Override
	public int getDefaultTimeStep() {
		return 20;
	}
}
