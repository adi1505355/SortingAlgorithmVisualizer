package com.VisualizerHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import com.SortAlgo.Algorithms.IDynamicAlgorithm;
import com.SortAlgo.GUIBase.MainScreen;

@SuppressWarnings("serial")
public class VisualizerGUIHelper extends JFrame {
	DrawComponents draw = null;
	boolean backToHome = false;
	public VisualizerGUIHelper(int length,int width,String className, String barsWidth , 
			String stepRate){
		setVisible(true);//make the class, hence the grid visible
		setSize(length,width);//size of grid
		draw = new DrawComponents(length,width, barsWidth,className);
		try {
			final ExecutorService executorService = Executors.newSingleThreadExecutor();
			executorService.execute(() -> sorter(className, stepRate));
			executorService.shutdown();
			add(draw);
			setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		}catch(Exception ex){
			System.out.println("Exception occurred : " + ex.getStackTrace());
		}
	}

	@SuppressWarnings("rawtypes")
	public void sorter(String className, String stepRate) {
		Class algoClass = null;
		Object instance = null;
		try {
			sleepThread();
			algoClass = Class.forName("com.SortAlgo.Algorithms." + className);
			if(algoClass != null){
				instance = algoClass.newInstance();
			}
			IDynamicAlgorithm sortingAlgo = (IDynamicAlgorithm)instance;
			if(sortingAlgo != null) {
				sortingAlgo.sortArray(draw, stepRate);
			}
			draw.highlightArray();
		}catch(ClassNotFoundException cnf){
			System.out.println("Class Not Found Please Check the Package/ClassName..");
		}catch(Exception ex){
			System.out.println("Exception occurred :" + ex.getStackTrace());
		}
		sleepThread();
		MainScreen start = new MainScreen(1280,720);
		start.createScreen();
		dispose();
	}

	private void sleepThread() {
		try {
			Thread.sleep(2000);
		} catch (Exception ex) {
			System.out.println("Exception occurred :" + ex.getStackTrace());
		}
	}

}
