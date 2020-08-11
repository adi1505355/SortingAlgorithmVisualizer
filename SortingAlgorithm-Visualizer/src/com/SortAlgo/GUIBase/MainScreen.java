package com.SortAlgo.GUIBase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.VisualizerHelper.VisualizerGUIHelper;

/*
 * 
 * @author : aditya.shahi
 */
@SuppressWarnings("serial")
public class MainScreen extends JFrame implements ActionListener{

	private static final String VERDANA = "Verdana";
	JButton selectionSortButton = null;
	JButton bubbleSortButton = null;
	JButton insertionSortButton = null;
	JButton mergeSortButton = null;
	JButton quickSortButton = null;
	JButton heapSortButton = null;
	JButton customSortButton = null;
	//JButton customSortButton1 = null;

	private int windowLength = 0;
	private int windowWidth = 0;

	JTextField barsWidth = null;
	JTextField stepRate = null;

	public MainScreen(int length, int width){
		setVisible(true);//make the class, hence the grid visible
		setSize(length,width);//size of grid
		windowLength = length; windowWidth = width;
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);//to open window in maximised mode alys
	} 

	public void createScreen() {
		try {
			setLayout(new FlowLayout(FlowLayout.CENTER,140,80));//setting x & y margins
			addAlgoButtons();
			getInputData();
			getScreenHeader();
			//choiceBar();
		}catch(Exception ex) {
			System.out.println("Exception occurred :"+ex.getStackTrace());
		}

	}

	protected void getInputData() {
		JPanel jp = new JPanel();
		jp.setFont(Font.getFont(VERDANA));
		jp.setBackground(Color.gray);
		jp.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JLabel width = new JLabel("Bars Width :");
		barsWidth = new JTextField(15);
		JLabel motionVelocity = new JLabel("StepRate :");
		stepRate = new JTextField(15);
		jp.add(width);
		jp.add(barsWidth);
		jp.add(motionVelocity);
		jp.add(stepRate);
		Border border = BorderFactory.createLineBorder(Color.CYAN);
		jp.setBorder(border);
		add(jp);

	}

	protected void addAlgoButtons() {
		selectionSortButton = new JButton("Selection Sort");
		addButtonsToFrame(selectionSortButton);
		selectionSortButton.addActionListener(this);

		bubbleSortButton = new JButton("Bubble Sort");
		addButtonsToFrame(bubbleSortButton);
		bubbleSortButton.addActionListener(this);

		insertionSortButton = new JButton("Insertion Sort");
		addButtonsToFrame(insertionSortButton);
		insertionSortButton.addActionListener(this);

		mergeSortButton = new JButton("Merge Sort");
		addButtonsToFrame(mergeSortButton);
		mergeSortButton.addActionListener(this);

		quickSortButton = new JButton("Quick Sort");
		addButtonsToFrame(quickSortButton);
		quickSortButton.addActionListener(this);

		heapSortButton = new JButton("Heap Sort");
		addButtonsToFrame(heapSortButton);
		heapSortButton.addActionListener(this);

		customSortButton = new JButton("Custom Sort");
		addButtonsToFrame(customSortButton);
		customSortButton.addActionListener(this);

		/*		customSortButton1 = new JButton("Custom Sort2");
		addButtonsToFrame(customSortButton1);
		customSortButton1.addActionListener(this);*/
	}

	@Override
	public void actionPerformed (ActionEvent ae) {
		VisualizerGUIHelper algo = null;
		if(ae.getSource() == selectionSortButton) {
			System.out.println("Selection Sort Algorithm selected");
			algo = new VisualizerGUIHelper(windowLength,windowWidth,
					"SelectionSort", barsWidth.getText(), stepRate.getText());
			dispose();
		}
		else if(ae.getSource() == bubbleSortButton) {
			System.out.println("Bubble Sort Algorithm selected");
			algo = new VisualizerGUIHelper(windowLength,windowWidth,
					"BubbleSort", barsWidth.getText(), stepRate.getText());
			dispose();
		}
		else if(ae.getSource() == insertionSortButton) {
			System.out.println("Insertion Sort Algorithm selected");
			algo = new VisualizerGUIHelper(windowLength,windowWidth,
					"InsertionSort", barsWidth.getText(), stepRate.getText());
			dispose();
		}
		else if(ae.getSource() == mergeSortButton) {
			System.out.println("Merge Sort Algorithm selected");
			algo = new VisualizerGUIHelper(windowLength,windowWidth,
					"MergeSort", barsWidth.getText(), stepRate.getText());
			dispose();
		}
		else if(ae.getSource() == quickSortButton) {
			System.out.println("Quick Sort Algorithm selected");
			algo = new VisualizerGUIHelper(windowLength,windowWidth,
					"QuickSort", barsWidth.getText(), stepRate.getText());
			dispose();
		}
		else if(ae.getSource() == heapSortButton) {
			System.out.println("Heap Sort Algorithm selected");
			algo = new VisualizerGUIHelper(windowLength,windowWidth,
					"HeapSort", barsWidth.getText(), stepRate.getText());
			dispose();
		}
	}

	protected void addButtonsToFrame(JButton button) {
		JPanel panel = new JPanel();
		//panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		panel.setBounds(250,500,195,30);
		button.setFont(new Font(VERDANA, Font.BOLD, 18));
		panel.add(button);
		panel.setBackground(Color.RED);
		Border border = BorderFactory.createLineBorder(Color.CYAN);
		panel.setBorder(border);
		add(panel);
	}

	protected void getScreenHeader(){
		JLabel header = new JLabel("!! Welcome to Your Sorting Algorithm Visualizer !!");
		header.setHorizontalAlignment(SwingConstants.CENTER); // set the horizontal alignement on the x axis !
		header.setVerticalAlignment(SwingConstants.CENTER); 
		header.setFont(new Font(VERDANA, Font.BOLD, 30));
		header.setForeground(Color.ORANGE);
		header.setBackground(Color.ORANGE);
		add(header);
		Border border = BorderFactory.createLineBorder(Color.CYAN);
		header.setBorder(border);
	}

}
