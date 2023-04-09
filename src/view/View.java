package view;

import java.awt.Color;
import java.awt.Component;

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
	private ArrayList<JLabel> columnLabels = new ArrayList<JLabel>();
	private ArrayList<JLabel> rowLabels = new ArrayList<JLabel>();
	private int gridSize;
	private int fullScreenDimension;
	private ArrayList<JTextField> inputFields = new ArrayList<JTextField>();
	private int[] columnResultsIndexes;
	private int[] rowResultsIndexes;
	private JButton checkResultButton = new JButton("Check!");
	private JButton gameScreenChangerButton = new JButton();
	JComboBox<String> dimensionsComboBox = new JComboBox<String>();

	private Timer timer;
	private int elapsedTime;
	private int count;

	public View() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Fake Sudoku");
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

	public int getGridSize() {
		return gridSize;
	}

	public ArrayList<JTextField> getListOfInputs() {
		return inputFields;
	}

	public int getSelectedItemFromDimensionsBox() {
		switch ((String) dimensionsComboBox.getSelectedItem()) {
			case "Facil": return 4;
			case "Normal": return 5;
			case "Díficil": return 6;
		}
		
		return 4;
	}
	
	private void setElapsedTime(int time) {
		this.elapsedTime = time;
	}
	
	public int getElapsedTime() {
		return elapsedTime;
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

		String[] comboBoxOptions = { "Facil", "Normal", "Díficil" };

		for (int i = 0; i < comboBoxOptions.length; i++) {
			dimensionsComboBox.addItem(comboBoxOptions[i]);
		}

		gameScreenChangerButton.setBounds(260, 379, 85, 21);
		
		gameScreenChangerButton.setText("Jugar");
		gameScreenChangerButton.setHorizontalAlignment(SwingConstants.CENTER);
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
		this.gridSize = innerGridDimension;
		this.fullScreenDimension = innerGridDimension + 1;

		setIndexesForColumnOfResults(innerGridDimension);
		setIndexesForRowOfResults(innerGridDimension);

		frame.getContentPane().setLayout(new GridLayout(fullScreenDimension + 1, fullScreenDimension));
		frame.getContentPane().setBackground(Color.white);

		for (int i = 0; i < (fullScreenDimension * fullScreenDimension) - 1; i++) {
			if (isIncludedInResultIndexes(i)) {
				ArrayList<JLabel> correspondingResultsList = isIndexInTheLastRow(i) ? rowLabels
						: columnLabels;
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
				inputFields.add(textField);
				frame.getContentPane().add(textField);
			}
		}

		frame.getContentPane().add(checkResultButton);

		int[] cellPositionOfResults = getCopyOfArraysOfResultsIndexes();
		
		Color scoreColor = new Color(196, 77, 255);
		changeBackgroundColorOfResultLabels(cellPositionOfResults, scoreColor);
		
		setTimer();
		setHighScore();
	}

	public void paintEntireGrid(Color color) {
		for (int i = 0; i < inputFields.size(); i++) {
			inputFields.get(i).setBackground(color);
		}
	}

	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void setBackgroundOfRow(int rowIndex, Color color) {
		for (int i = rowIndex * gridSize; i < gridSize; i++) {
			inputFields.get(i).setBackground(color);
		}
	}

	public void setBackgroundOfColumn(int rowIndex, Color color) {
		for (int i = rowIndex; i < rowIndex + gridSize * (gridSize - 1); i++) {
			inputFields.get(i).setBackground(color);
		}
	}

	public void setBackgroundOfTextFieldByIndex(int index, Color color) {
		inputFields.get(index).setBackground(color);
	}
	
	private void setTimer() {
		JLabel timerTitleLabel = new JLabel("Time elapsed:");
	    timerTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    JLabel timerLabel = new JLabel ("0 seconds");
	    
	    timer = new Timer(1000, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            count++;
	            setElapsedTime(count);
	            timerLabel.setText(count + " seconds");
	        }
	    });
	    
	    frame.getContentPane().add(timerTitleLabel);
	    frame.getContentPane().add(timerLabel);
	    timer.start();
	}
	
	private void setHighScore() {
		JLabel empty = new JLabel("");
		JLabel highscore = new JLabel("Highscore: ");
		highscore.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel highscoreValue = new JLabel("50");
		highscoreValue.setHorizontalAlignment(SwingConstants.CENTER);
		
		frame.getContentPane().add(empty);
		frame.getContentPane().add(highscore);
		frame.getContentPane().add(highscoreValue);
	}

	private void changeBackgroundColorOfResultLabels(int[] cellPositionOfResults, Color color) {
		for (int i = 0; i < cellPositionOfResults.length; i++) {
			int indexForGivenList = i >= gridSize ? i - gridSize : i;
			ArrayList<JLabel> correspondingResultsList = isIndexInTheLastRow(cellPositionOfResults[i])
					? rowLabels
					: columnLabels;
			correspondingResultsList.get(indexForGivenList).setBackground(color);
		}
	}

	private int[] getCopyOfArraysOfResultsIndexes() {
		int[] cellPositionOfResults = new int[gridSize * 2];

		System.arraycopy(columnResultsIndexes, 0, cellPositionOfResults, 0, gridSize);
		System.arraycopy(rowResultsIndexes, 0, cellPositionOfResults, gridSize, gridSize);

		return cellPositionOfResults;
	}

	private boolean isIncludedInResultIndexes(int index) {
		boolean isIncludedInColumnOfResults = isIncludedInListOfIndexes(index, columnResultsIndexes);
		boolean isIncludedInRowOfResults = isIncludedInListOfIndexes(index, rowResultsIndexes);

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
		for (int i = 0; i < rowLabels.size(); i++) {
			columnLabels.get(i).setText(Integer.toString(results.get(i)));
		}
	}

	private void populateRowWithResults(ArrayList<Integer> results) {
		for (int i = 0; i < rowLabels.size(); i++) {
			rowLabels.get(i).setText(Integer.toString(results.get(i)));
		}
	}

	private void setIndexesForColumnOfResults(int lengthOfList) {
		int[] indexes = new int[lengthOfList];
		indexes[0] = lengthOfList;

		for (int i = 1; i < lengthOfList; i++) {
			indexes[i] = indexes[i - 1] + (lengthOfList + 1);
		}

		this.columnResultsIndexes = indexes;
	}

	private void setIndexesForRowOfResults(int lengthOfList) {
		int[] indexes = new int[lengthOfList];
		int indexOfFirstCellOfLastRow = lengthOfList * (lengthOfList + 1);
		indexes[0] = indexOfFirstCellOfLastRow;

		for (int i = 1; i < lengthOfList; i++) {
			indexes[i] = indexes[i - 1] + 1;
		}

		this.rowResultsIndexes = indexes;
	}
}