package presenter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;

import model.Model;
import model.Model.ListOfCells;
import view.View;

public class Presenter {
	private Model model;
	private View view;
	
	public Presenter(View view, Model model) {
		this.view = view;
		this.model = model;
		
		view.addActionListenerToButton(new CheckListener(), view.getCheckResultButton());
		view.addActionListenerToButton(new ScreenGameChangerListener(), view.getGameScreenChangerButton());
	}
	
	public void startGame() {
		view.initializeView();
		view.generateMenu();
	}
	
	class ScreenGameChangerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int innerGridDimension = view.getSelectedItemFromDimensionsBox();	
	
			model.setLengthOfListOfRandomNumbersAndGenerateStaticData(innerGridDimension);
			
			view.prepareScreen(innerGridDimension);
			view.populateWithResults(model.getColumnOfExpectedResults(), model.getRowOfExpectedResults());
		}
		
	}
	
	class CheckListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// values of the 16 squares that the user has input
			int innerGridDimension = view.getInnerGridDimension();

			ArrayList<JTextField> listOfInputs = view.getListOfInputs();

			int[] inputValues = new int[innerGridDimension * innerGridDimension];
			for (int i = 0; i < inputValues.length; i++) {
				inputValues[i] = Integer.parseInt(listOfInputs.get(i).getText());
			}

			// calculating the row's values

			int[] rowSums = new int[innerGridDimension];
			for (int i = 0; i < innerGridDimension; i++) {
				int index = i * innerGridDimension;
				for (int j = 0; j < innerGridDimension; j++) {
					rowSums[i] += inputValues[index + j];
				}
			}
			
			System.out.println(Arrays.toString(rowSums));

			int firstRow = rowSums[0];
			int secondRow = rowSums[1];
			int thirdRow = rowSums[2];
			int fourthRow = rowSums[3];

			// calculating the column's values

			int[] colSums = new int[innerGridDimension];
			for (int i = 0; i < innerGridDimension; i++) {
				for (int j = 0; j < innerGridDimension; j++) {
					int index = j * innerGridDimension;
					colSums[i] += inputValues[index + i];
				}
			}
			
			System.out.println(Arrays.toString(colSums));
			
			int firstColumn = colSums[0];
			int secondColumn = colSums[1];
			int thirdColumn = colSums[2];
			int fourthColumn = colSums[3];

			// validating wip

			// validating the first row
			
			int currentRowCheckingIndex = innerGridDimension;
					
			boolean doColumnsHaveCorrectValues = firstColumn == model.getExpectedResultFromCell(ListOfCells.ROW, 1)
					&& secondColumn == model.getExpectedResultFromCell(ListOfCells.ROW, 2)
					&& thirdColumn == model.getExpectedResultFromCell(ListOfCells.ROW, 3)
					&& fourthColumn == model.getExpectedResultFromCell(ListOfCells.ROW, 4);

			if (firstRow == model.getExpectedResultFromCell(ListOfCells.COLUMN, 1) && doColumnsHaveCorrectValues) {
				for (int i = 0; i < currentRowCheckingIndex; i++) {
					view.setBackgroundOfTextFieldByIndex(i, Color.GREEN);
				}
			}
			
			if (secondRow == model.getExpectedResultFromCell(ListOfCells.COLUMN, 2) && doColumnsHaveCorrectValues) {
				for (int i = currentRowCheckingIndex; i < currentRowCheckingIndex + innerGridDimension; i++) {
					view.setBackgroundOfTextFieldByIndex(i, Color.GREEN);
				}
			}
			
			currentRowCheckingIndex += innerGridDimension;

			if (thirdRow == model.getExpectedResultFromCell(ListOfCells.COLUMN, 3) && doColumnsHaveCorrectValues) {
				for (int i = currentRowCheckingIndex; i < currentRowCheckingIndex * innerGridDimension; i++) {
					view.setBackgroundOfTextFieldByIndex(i, Color.GREEN);
				}
			}
			
			currentRowCheckingIndex += innerGridDimension;

			if (fourthRow == model.getExpectedResultFromCell(ListOfCells.COLUMN, 4) && doColumnsHaveCorrectValues) {
				for (int i = currentRowCheckingIndex; i < currentRowCheckingIndex + innerGridDimension; i++) {
					view.setBackgroundOfTextFieldByIndex(i, Color.GREEN);
				}
			}
		}
		
	}
		
}