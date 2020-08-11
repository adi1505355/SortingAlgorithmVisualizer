package com.SortAlgo.Algorithms;

import com.VisualizerHelper.DrawComponents;
/*
 * 
 * @author : aditya.shahi
 * <Base Interface for all sorting algorithms>
 */
public interface IDynamicAlgorithm {
	
	public void sortArray(DrawComponents draw, String stepRate);
	
	public int getDefaultTimeStep();

}
