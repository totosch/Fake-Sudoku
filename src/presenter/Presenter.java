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
		private boolean hasCompletedGame(boolean[] rowSuccessByIndex, boolean[] columnSuccessByIndex) {
			for (int i = 0; i < view.getInnerGridDimension(); i++) {
				if (!rowSuccessByIndex[i] || !columnSuccessByIndex[i]) {
					return false;
				}
			}
			
			return true;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// values of the 16 squares that the user has input
			try {
				int innerGridDimension = view.getInnerGridDimension();
				
				ArrayList<JTextField> listOfInputs = view.getListOfInputs();
				
				int[] inputValues = new int[innerGridDimension * innerGridDimension];
				for (int i = 0; i < inputValues.length; i++) {
					inputValues[i] = Integer.parseInt(listOfInputs.get(i).getText());
				}
				
				int[] rowSums = new int[innerGridDimension];
				for (int i = 0; i < innerGridDimension; i++) {
					int index = i * innerGridDimension;
					for (int j = 0; j < innerGridDimension; j++) {
						rowSums[i] += inputValues[index + j];
					}
				}
				
				int[] colSums = new int[innerGridDimension];
				for (int i = 0; i < innerGridDimension; i++) {
					for (int j = 0; j < innerGridDimension; j++) {
						int index = j * innerGridDimension;
						colSums[i] += inputValues[index + i];
					}
				}
				
				
				boolean[] rowSuccessByIndex = new boolean[innerGridDimension];
				boolean[] columnSuccessByIndex = new boolean[innerGridDimension];
				
				for (int i = 0; i < innerGridDimension; i++) {
					if (rowSums[i] == model.getExpectedResultFromCell(ListOfCells.COLUMN, i)) {
						rowSuccessByIndex[i] = true;
					}
				}
				
				for (int i = 0; i < innerGridDimension; i++) {
					if (colSums[i] == model.getExpectedResultFromCell(ListOfCells.ROW, i)) {
						columnSuccessByIndex[i] = true;
					}
				}
				
				boolean hasCompletedGame = this.hasCompletedGame(rowSuccessByIndex, columnSuccessByIndex);
				
				if (hasCompletedGame) {
					int elapsedTime = view.getElapsedTime();
					boolean isNewHighScore = model.manageNewHighScore(view.getElapsedTime());
					view.paintEntireGrid(Color.green);
					view.showMessageDialog(isNewHighScore ? "Ganaste! Nuevo record: " + elapsedTime + " segundos" : "Ganaste!");
				}
			} catch (Exception error) {
				view.showMessageDialog("Solo se permiten numeros para completar grilla");
			}
		}
		
	}
		
}