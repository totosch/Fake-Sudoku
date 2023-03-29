package gameView;

import gameModel.Model;
import gameModel.Model.ListOfCells;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class View {
	private JFrame frame;
	private JTextField textField;
	private ArrayList<JLabel> columnOfResultsLabels;
	private ArrayList<JLabel> rowOfResultsLabels;
	public static View  viewInstance;
	
	public static View getViewInstance() {
		if (viewInstance != null) {
			return viewInstance;
		}
		
		return new View();
	}
	
	public View() {
		initialize();
	}
	
	public void initializeView() {
		this.frame.setVisible(true);
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("Seleccione");
		comboBox.setBounds(260, 249, 119, 21);
		frame.getContentPane().add(comboBox);
		
		int[] comboBoxOptions = { 4, 5, 6 };  
		
		for (int i = 0; i < comboBoxOptions.length; i++) {
			comboBox.addItem(Integer.toString(comboBoxOptions[i]));
		}
		
		JButton button = new JButton();
		button.setBounds(250, 379, 85, 21);
		frame.getContentPane().add(button);
		button.setText("dale");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : frame.getContentPane().getComponents() ) {
					frame.getContentPane().remove(c);
				}

				changeToGameScreen();
				frame.getContentPane().revalidate();
			}
		});
	}
	
	public void changeToGameScreen () {
		frame.getContentPane().setLayout(new GridLayout(5, 5));
		frame.getContentPane().setBackground(Color.white);
		
		int innerGridDimension = 4;
		
		Model model = new Model(innerGridDimension);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font(textField.getFont().getName(), Font.BOLD, textField.getFont().getSize()));

		textField.setColumns(10);

		// generating the textFields for the inputs of the player
		ArrayList<JTextField> textFieldList = new ArrayList<>();

		for (int i = 0; i < 24; i++) {
			JTextField textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font(textField.getFont().getName(), Font.BOLD, textField.getFont().getSize()));
			textFieldList.add(textField);
		}
		
		int[] cellPositionOfColumnWithResults = model.getCellPositionOfColumnOfResults();
		int[] cellPositionOfRowWithResults = model.getCellPositionOfRowOfResults();
		
		ArrayList<Integer> columnOfResults = model.getColumnOfExpectedResults();
		ArrayList<Integer> rowOfResults = model.getRowOfExpectedResults();
		
		for (int i = 0; i < cellPositionOfColumnWithResults.length; i++) {
			int indexOne = cellPositionOfColumnWithResults[i];
			int indexTwo = cellPositionOfRowWithResults[i];
			textFieldList.get(indexOne).setText(Integer.toString(columnOfResults.get(i)));
			textFieldList.get(indexTwo).setText(Integer.toString(rowOfResults.get(i)));
		}
		
		// generating a button to check for the win

		// changing color of Objectives to make it prettier
		
		int [] cellPositionOfResults = new int [ innerGridDimension * 2 ];
		
		System.arraycopy(cellPositionOfColumnWithResults, 0, cellPositionOfResults, 0, innerGridDimension);
		System.arraycopy(cellPositionOfRowWithResults, 0, cellPositionOfResults, innerGridDimension, innerGridDimension);
		
		for (int i = 0; i < cellPositionOfResults.length; i++) {
			textFieldList.get(cellPositionOfResults[i]).setBackground(Color.CYAN);
		}

		JButton checkButton = new JButton("Check!");

		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// values of the 16 squares that the user has input

				int[] inputValues = new int[24];
				for (int i = 0; i < 24; i++) {
					inputValues[i] = Integer.parseInt(textFieldList.get(i).getText());
				}

				// calculating the row's objectives
				int[] rowObjectives = { Integer.parseInt(textFieldList.get(4).getText()),
						Integer.parseInt(textFieldList.get(9).getText()),
						Integer.parseInt(textFieldList.get(14).getText()),
						Integer.parseInt(textFieldList.get(19).getText()) };

				// calculating the row's values

				int[] rowSums = new int[4];
				for (int i = 0; i < 4; i++) {
					int index = i * 5;
					for (int j = 0; j < 4; j++) {
						rowSums[i] += inputValues[index + j];
					}
				}

				int firstRow = rowSums[0];
				int secondRow = rowSums[1];
				int thirdRow = rowSums[2];
				int fourthRow = rowSums[3];

				// calculating the column's objective

				int[] colObjectives = { Integer.parseInt(textFieldList.get(20).getText()),
						Integer.parseInt(textFieldList.get(21).getText()),
						Integer.parseInt(textFieldList.get(22).getText()),
						Integer.parseInt(textFieldList.get(23).getText()) };

				// calculating the column's values

				int[] colSums = new int[4];
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						int index = j * 5;
						colSums[i] += inputValues[index + i];
					}
				}
				int firstColumn = colSums[0];
				int secondColumn = colSums[1];
				int thirdColumn = colSums[2];
				int fourthColumn = colSums[3];

				// validating wip

				// validating the first row
				
				System.out.println("hola" + model.getExpectedResultFromCell(ListOfCells.COLUMN, 1));
				
				boolean doColumnsHaveCorrectValues = firstColumn == model.getExpectedResultFromCell(ListOfCells.ROW, 1)
						&& secondColumn == model.getExpectedResultFromCell(ListOfCells.ROW, 2)
						&& thirdColumn == model.getExpectedResultFromCell(ListOfCells.ROW, 3)
						&& fourthColumn == model.getExpectedResultFromCell(ListOfCells.ROW, 4);

				if (firstRow == model.getExpectedResultFromCell(ListOfCells.COLUMN, 1) && doColumnsHaveCorrectValues) {
					for (int i = 0; i < 4; i++) {
						JTextField textFields = textFieldList.get(i);
						textFields.setBackground(Color.GREEN);
					}
				}
				
				if (secondRow == model.getExpectedResultFromCell(ListOfCells.COLUMN, 2) && doColumnsHaveCorrectValues) {
					for (int i = 5; i < 9; i++) {
						JTextField textFields = textFieldList.get(i);
						textFields.setBackground(Color.GREEN);
					}
				}

				if (thirdRow == model.getExpectedResultFromCell(ListOfCells.COLUMN, 3) && doColumnsHaveCorrectValues) {
					for (int i = 10; i < 14; i++) {
						JTextField textFields = textFieldList.get(i);
						textFields.setBackground(Color.GREEN);
					}
				}

				if (fourthRow == model.getExpectedResultFromCell(ListOfCells.COLUMN, 4) && doColumnsHaveCorrectValues) {
					for (int i = 15; i < 19; i++) {
						JTextField textFields = textFieldList.get(i);
						textFields.setBackground(Color.GREEN);
					}
				} 
			}

		});

		// adding the button and the textFields to the frame
		for (JTextField textFields : textFieldList) {
			frame.getContentPane().add(textFields);
		}
		frame.getContentPane().add(checkButton);
		
 
	}
	
	private void setTextOfLabelList (ArrayList<Integer> arrayWithText, int[] cellPositions, ArrayList<JLabel> components){ 
		for (int i = 0; i < cellPositions.length; i++) {
			components.get(cellPositions[i]).setText(Integer.toString(arrayWithText.get(i)));
		}
	}	
	
	private void populateColumnWithResults (ArrayList<Integer> results, int[] cellPositions) {
		this.setTextOfLabelList(results, cellPositions, columnOfResultsLabels);
	}
	
	private void populateRowWithResults (ArrayList<Integer> results, int[] cellPositions) {
		this.setTextOfLabelList(results, cellPositions, rowOfResultsLabels);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
}
