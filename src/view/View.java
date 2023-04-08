package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JOptionPane;

public class View {
	private JFrame frame;
	private ArrayList<JLabel> columnOfResultsLabels = new ArrayList<JLabel>();
	private ArrayList<JLabel> rowOfResultsLabels = new ArrayList<JLabel>();
	private int innerGridDimension;
	private int fullScreenDimension;
	private ArrayList<JTextField> listOfInputs = new ArrayList<JTextField>();
	private int[] columnOfResultsIndexes;
	private int[] rowOfResultsIndexes;
	private JButton checkResultButton = new JButton("Check!");
	private JButton gameScreenChangerButton = new JButton();
	JComboBox<String> dimensionsComboBox = new JComboBox<String>();

	private Timer timer;
	private int count;

	public View() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Enjoyer or not-enjoyer?!?! FIND OUT! STROMG!!!!");
	}

	public void initializeView() {
		this.frame.setVisible(true);
	}

	public void addActionListenerToButton(ActionListener listener, JButton button) {
		button.addActionListener(listener);
	}

	public JButton getCheckResultButton() {
		return checkResultButton;
	}

	public JButton getGameScreenChangerButton() {
		return gameScreenChangerButton;
	}

	public int getInnerGridDimension() {
		return innerGridDimension;
	}

	public ArrayList<JTextField> getListOfInputs() {
		return listOfInputs;
	}

	public int getSelectedItemFromDimensionsBox() {
		return Integer.parseInt((String) dimensionsComboBox.getSelectedItem());
	}

	public void generateMenu() {
		JLabel lblNewLabel = new JLabel("Bienvenido al Fake Sudoku!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(184, 66, 264, 53);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblPorFavorSeleccione = new JLabel("Por favor, seleccione la dificultad.");
		lblPorFavorSeleccione.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorFavorSeleccione.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPorFavorSeleccione.setBounds(184, 201, 264, 53);
		frame.getContentPane().add(lblPorFavorSeleccione);

		dimensionsComboBox.setToolTipText("Seleccione");
		dimensionsComboBox.setBounds(260, 249, 119, 21);
		frame.getContentPane().add(dimensionsComboBox);

		int[] comboBoxOptions = { 4, 5, 6 };

		for (int i = 0; i < comboBoxOptions.length; i++) {
			dimensionsComboBox.addItem(Integer.toString(comboBoxOptions[i]));
		}

		gameScreenChangerButton.setBounds(250, 379, 85, 21);
		
		gameScreenChangerButton.setText("dale");
		gameScreenChangerButton.setHorizontalAlignment(SwingConstants.CENTER);//no se xq esto no anda
		frame.getContentPane().add(gameScreenChangerButton);
	}

	public void prepareScreen(int innerGridDimension) {
		wipeOutPreviousScreen();
		addComponentLayout(innerGridDimension);
	}

	public void wipeOutPreviousScreen() {
		for (Component c : frame.getContentPane().getComponents()) {
			frame.getContentPane().remove(c);
			frame.getContentPane().revalidate();
		}
	}

	public void addComponentLayout(int innerGridDimension) {
		this.innerGridDimension = innerGridDimension;
		this.fullScreenDimension = innerGridDimension + 1;

		setIndexesForColumnOfResults(innerGridDimension);
		setIndexesForRowOfResults(innerGridDimension);

		frame.getContentPane().setLayout(new GridLayout(fullScreenDimension + 1, fullScreenDimension));
		frame.getContentPane().setBackground(Color.white);

		for (int i = 0; i < (fullScreenDimension * fullScreenDimension) - 1; i++) {
			if (isIncludedInResultIndexes(i)) {
				ArrayList<JLabel> correspondingResultsList = isIndexInTheLastRow(i) ? rowOfResultsLabels
						: columnOfResultsLabels;
				JLabel label = new JLabel();
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize()));
				label.setOpaque(true);
				frame.getContentPane().add(label);
				correspondingResultsList.add(label);
			} else {
				JTextField textField = new JTextField();
				textField.setHorizontalAlignment(SwingConstants.CENTER);
				textField.setFont(new Font(textField.getFont().getName(), Font.BOLD, textField.getFont().getSize()));
				listOfInputs.add(textField);
				frame.getContentPane().add(textField);
			}
		}

		frame.getContentPane().add(checkResultButton);

		int[] cellPositionOfResults = getCopyOfArraysOfResultsIndexes();

		changeBackgroundColorOfResultLabels(cellPositionOfResults, Color.CYAN);
		
		setTimer();
	}

	public void paintEntireGrid(Color color) {
		for (int i = 0; i < listOfInputs.size(); i++) {
			listOfInputs.get(i).setBackground(color);
		}
	}

	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void setBackgroundOfRow(int rowIndex, Color color) {
		for (int i = rowIndex * innerGridDimension; i < innerGridDimension; i++) {
			listOfInputs.get(i).setBackground(color);
		}
	}

	public void setBackgroundOfColumn(int rowIndex, Color color) {
		for (int i = rowIndex; i < rowIndex + innerGridDimension * (innerGridDimension - 1); i++) {
			listOfInputs.get(i).setBackground(color);
		}
	}

	public void setBackgroundOfTextFieldByIndex(int index, Color color) {
		listOfInputs.get(index).setBackground(color);
	}
	
	private void setTimer() {
		JLabel timerTitleLabel = new JLabel("Time elapsed:");
	    timerTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    JLabel timerLabel = new JLabel ("0 seconds");
	    
	    

	    //porque 1000? porque esta en ms, entonces 1000ms = 1seg, no preguntes, lo googlie (no fue chatgpt)
	    
	    timer = new Timer(1000, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            count++;
	            timerLabel.setText(count + " seconds");
	        }
	    });
	    
	    //mi problema aca es que lo agrego en la pantalla de menu, hoy mostrame como se llama el otro panel y es agregarlo ahi tambien y listo, o de hecho solo en ese
	    //estaria bueno agregar la cantidad de count del for de aca arriba al mensaje de cuando ganas, asi te tira cuanto tardaste
	    frame.getContentPane().add(timerTitleLabel);
	    frame.getContentPane().add(timerLabel);
	    timer.start();
	}

	private void changeBackgroundColorOfResultLabels(int[] cellPositionOfResults, Color color) {
		for (int i = 0; i < cellPositionOfResults.length; i++) {
			int indexForGivenList = i >= innerGridDimension ? i - innerGridDimension : i;
			ArrayList<JLabel> correspondingResultsList = isIndexInTheLastRow(cellPositionOfResults[i])
					? rowOfResultsLabels
					: columnOfResultsLabels;
			correspondingResultsList.get(indexForGivenList).setBackground(color);
		}
	}

	private int[] getCopyOfArraysOfResultsIndexes() {
		int[] cellPositionOfResults = new int[innerGridDimension * 2];

		System.arraycopy(columnOfResultsIndexes, 0, cellPositionOfResults, 0, innerGridDimension);
		System.arraycopy(rowOfResultsIndexes, 0, cellPositionOfResults, innerGridDimension, innerGridDimension);

		return cellPositionOfResults;
	}

	private boolean isIncludedInResultIndexes(int index) {
		boolean isIncludedInColumnOfResults = isIncludedInListOfIndexes(index, columnOfResultsIndexes);
		boolean isIncludedInRowOfResults = isIncludedInListOfIndexes(index, rowOfResultsIndexes);

		return isIncludedInColumnOfResults || isIncludedInRowOfResults;
	}

	private boolean isIncludedInListOfIndexes(int index, int[] listOfIndexes) {
		for (int resultIndex : listOfIndexes) {
			if (index == resultIndex) {
				return true;
			}
		}

		return false;
	}

	private boolean isIndexInTheLastRow(int index) {
		return (fullScreenDimension * fullScreenDimension) - index <= fullScreenDimension;
	}

	public void populateWithResults(ArrayList<Integer> columnResults, ArrayList<Integer> rowResults) {
		populateColumnWithResults(columnResults);
		populateRowWithResults(rowResults);
	}

	private void populateColumnWithResults(ArrayList<Integer> results) {
		for (int i = 0; i < rowOfResultsLabels.size(); i++) {
			columnOfResultsLabels.get(i).setText(Integer.toString(results.get(i)));
		}
	}

	private void populateRowWithResults(ArrayList<Integer> results) {
		for (int i = 0; i < rowOfResultsLabels.size(); i++) {
			rowOfResultsLabels.get(i).setText(Integer.toString(results.get(i)));
		}
	}

	private void setIndexesForColumnOfResults(int lengthOfList) {
		int[] indexes = new int[lengthOfList];
		indexes[0] = lengthOfList;

		for (int i = 1; i < lengthOfList; i++) {
			indexes[i] = indexes[i - 1] + (lengthOfList + 1);
		}

		this.columnOfResultsIndexes = indexes;
	}

	private void setIndexesForRowOfResults(int lengthOfList) {
		int[] indexes = new int[lengthOfList];
		int indexOfFirstCellOfLastRow = lengthOfList * (lengthOfList + 1);
		indexes[0] = indexOfFirstCellOfLastRow;

		for (int i = 1; i < lengthOfList; i++) {
			indexes[i] = indexes[i - 1] + 1;
		}

		this.rowOfResultsIndexes = indexes;
	}
}