package com.VisualizerHelper;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawComponents extends JPanel {

	//think of logic to display total Runtime in seconds for diff algorithms
	public static final int DEFAULT_WIN_WIDTH = 1280;
	public static final int DEFAULT_WIN_HEIGHT = 720;
	private int DEFAULT_BAR_WIDTH = 5; // make this configurable
	private boolean showOffCounter = true;
	private Long algoStartTime = null;
	private Long algoEndTime = null;
	
	boolean canPopulateExecutionTime = false;
	/**
	 * This is the percent of the panel the bars will consume.
	 * Based on the original 256 bars each being 2x their height
	 * and 720px window height, or 512/720
	 */
	private static final double BAR_HEIGHT_PERCENT = 512.0/720.0;
	private  int NUM_BARS = 10;
    private String currentAlgorithm = null;
	public int[] displayArray;
	int[] barColours;
	int arrayChanges = 0;
	int maxValueInArray  = Integer.MIN_VALUE;
	
	public DrawComponents(int length, int width, String barsWidth, String algo){
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(length,width));
		currentAlgorithm = algo;
		if(barsWidth != null && !barsWidth.isEmpty()) {
			DEFAULT_BAR_WIDTH =  Integer.parseInt(barsWidth);
		}
		NUM_BARS = DEFAULT_WIN_WIDTH / DEFAULT_BAR_WIDTH;
		displayArray = new int[NUM_BARS];
		barColours = new int[NUM_BARS];
		for(int i = 0;i < NUM_BARS; i++) {
			displayArray[i] = new Random().nextInt(NUM_BARS);
			if(maxValueInArray < displayArray[i]) {
				maxValueInArray = displayArray[i];
			}
			barColours[i] = 0;
		}
		algoStartTime =  System.currentTimeMillis();
		repaint();//this will call the paintComponent method
	}

	private void finaliseUpdate(long millisecondDelay, boolean isStep) {
		repaint();
		try {
			Thread.sleep(millisecondDelay);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		if (isStep) 
			arrayChanges++;
	}

	public void swap(int firstIndex, int secondIndex , long millisecondDelay, boolean isStep) {
		int temp = displayArray[firstIndex];
		displayArray[firstIndex] = displayArray[secondIndex];
		displayArray[secondIndex] = temp;

		barColours[firstIndex] = 100;
		barColours[secondIndex] = 100;

		finaliseUpdate(millisecondDelay, isStep);
	}

	public void updateSingle(int index, int value, long millisecondDelay, boolean isStep,
			boolean isHighlight) {
		displayArray[index] = value;
		barColours[index] = 100;
		if(isHighlight) {
			barColours[index] = -50;
		}
		finaliseUpdate(millisecondDelay, isStep);
		repaint();
	}

	public void highlightArray() {
		algoEndTime =  System.currentTimeMillis();
		canPopulateExecutionTime = true;
		for (int i = 0; i < NUM_BARS; i++) {
			updateSingle(i, getValue(i), 20, false,true);
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphicsObj = (Graphics2D) g.create();
		try
		{
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphicsObj.addRenderingHints(renderingHints);
			graphicsObj.setColor(Color.RED);
			graphicsObj.setFont(new Font("Verdana", Font.BOLD, 20));
			graphicsObj.drawString("@Author : " + "Aditya Shahi :)", 10, 30);
			graphicsObj.drawString("Running Algorithm : " + currentAlgorithm, 800, 30);
			graphicsObj.setColor(Color.ORANGE);
			graphicsObj.drawString("Each Bar Width :" + DEFAULT_BAR_WIDTH , 10, 100);
			graphicsObj.drawString("Max Height Array : " + maxValueInArray , 10, 120);
			graphicsObj.drawString("Indices Swapped : " + arrayChanges, 10, 140);
			if(canPopulateExecutionTime) {
				graphicsObj.drawString("Algo Execution Time : " + (algoEndTime - algoStartTime)/1000 +" seconds", 10, 200);
			}
			if(showOffCounter) {
				graphicsObj.drawString("Sorting Action Begins in 3..2..1..GO", 10, 170);
				showOffCounter = false;
			}else {
				graphicsObj.drawString("Visualizer in Action..", 10, 170);
			}
			drawBars(graphicsObj);
		} finally {
			graphicsObj.dispose();
		}
	}

	private void drawBars(Graphics2D graphicsObj)
	{
		Graphics2D bufferedGraphics = null;
		int barWidth = getWidth() / NUM_BARS;
		int bufferedImageWidth = barWidth * NUM_BARS;
		int bufferedImageHeight = getHeight();

		if(bufferedImageHeight > 0 && bufferedImageWidth > 0) {
			if(bufferedImageWidth < 256) {
				bufferedImageWidth = 256;
			}
			BufferedImage bufferedImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight, BufferedImage.TYPE_INT_ARGB);
			makeBufferedImageTransparent(bufferedImage);
			try
			{
				bufferedGraphics = bufferedImage.createGraphics();
				for (int x = 0; x < NUM_BARS; x++) {
					double currentValue = getValue(x);
					double percentOfMax = currentValue / maxValueInArray;
					double heightPercentOfPanel = percentOfMax * BAR_HEIGHT_PERCENT;
					int height = (int) (heightPercentOfPanel * (double) getHeight());
					int xBegin = x + (barWidth - 1) * x;
					int yBegin = getHeight() - height;

					int val = barColours[x] * 2;
					if(val == -100) {
						bufferedGraphics.setColor(Color.CYAN);
					}
					else if (val < 190) {
						bufferedGraphics.setColor(new Color(255 - val, 255-val, 255 - val));
					}
					else {
						bufferedGraphics.setColor(new Color(255, 255 - val, 255 - val));
					}
					bufferedGraphics.fill3DRect(xBegin, yBegin, barWidth, height,true);
					if (barColours[x] > 0) {
						barColours[x] -= 5;
					}
				}
			}
			finally
			{
				if(bufferedGraphics != null)
				{
					bufferedGraphics.dispose();
				}
			}
			graphicsObj.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
		}else {
			System.out.println("Issue with the getHeight() or getWidth() method..");
		}
	}

	public int getValue(int x) {
		return displayArray[x];
	}
	private void makeBufferedImageTransparent(BufferedImage image)
	{
		Graphics2D bufferedGraphics = null;
		try
		{
			bufferedGraphics = image.createGraphics();

			bufferedGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
			bufferedGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
			bufferedGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		}
		finally
		{
			if(bufferedGraphics != null)
			{
				bufferedGraphics.dispose();
			}
		}
	}

}
