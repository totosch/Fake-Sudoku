package presenter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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
			int gridSize = view.getSelectedItemFromDimensionsBox();	
	
			model.setLengthOfListOfRandomNumbers(gridSize);
			
			view.prepareScreen(gridSize);
			view.populateWithResults(model.getColumnOfExpectedResults(), model.getRowOfExpectedResults());
			
		}
		
	}
	
	class CheckListener implements ActionListener {
		private boolean hasCompletedGame(boolean[] rowSuccessByIndex, boolean[] columnSuccessByIndex) {
			for (int i = 0; i < view.getGridSize(); i++) {
				if (!rowSuccessByIndex[i] || !columnSuccessByIndex[i]) {
					return false;
				}
			}
			
			return true;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int gridSize = view.getGridSize();
				
				ArrayList<JTextField> inputFields = view.getListOfInputs();
				
				int[] gridValues = new int[gridSize * gridSize];
				for (int i = 0; i < gridValues.length; i++) {
					gridValues[i] = Integer.parseInt(inputFields.get(i).getText());
				}
				
				int[] rowSums = new int[gridSize];
				for (int i = 0; i < gridSize; i++) {
					int index = i * gridSize;
					for (int j = 0; j < gridSize; j++) {
						rowSums[i] += gridValues[index + j];
					}
				}
				
				int[] colSums = new int[gridSize];
				for (int i = 0; i < gridSize; i++) {
					for (int j = 0; j < gridSize; j++) {
						int index = j * gridSize;
						colSums[i] += gridValues[index + i];
					}
				}
				
				
				boolean[] isRowSuccessful = new boolean[gridSize];
				boolean[] isColumnSuccessful = new boolean[gridSize];
				
				for (int i = 0; i < gridSize; i++) {
					if (rowSums[i] == model.getExpectedResultFromCell(ListOfCells.COLUMN, i)) {
						isRowSuccessful[i] = true;
					}
				}
				
				for (int i = 0; i < gridSize; i++) {
					if (colSums[i] == model.getExpectedResultFromCell(ListOfCells.ROW, i)) {
						isColumnSuccessful[i] = true;
					}
				}
				
				boolean isGameCompleted = this.hasCompletedGame(isRowSuccessful, isColumnSuccessful);
				
				if (isGameCompleted) {
					int elapsedTime = view.getElapsedTime();
					boolean isNewHighScore = model.manageNewHighScore(view.getElapsedTime());
					view.paintEntireGrid(Color.cyan);
					view.showMessageDialog(isNewHighScore ? "Ganaste! Nuevo record: " + elapsedTime + " segundos" : "Ganaste!");
				}
			} catch (Exception error) {
				view.showMessageDialog("Solo se permiten numeros!");
			}
		}
		
	}
		
}