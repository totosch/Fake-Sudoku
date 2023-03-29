package gameModel;

import java.util.ArrayList;
import java.util.Random;

public class Model {
	public static int minNumber;
	public static int maxNumber;
	private ArrayList<Integer> rowOfExpectedResults;
	private ArrayList<Integer> columnOfExpectedResults;
	private int[] cellPositionOfRowOfResults;
	private int[] cellPositionOfColumnOfResults;
	private int dimensionOfGrid;
	private static Random random = new Random();
	
	public enum ListOfCells {
		ROW,
		COLUMN
	}
	
	public Model(int lengthOfList) {
		minNumber = lengthOfList + 1;
		maxNumber = (lengthOfList + 1) * 3; 
		this.dimensionOfGrid = lengthOfList;
		this.rowOfExpectedResults = Model.generateRandomNumbers(lengthOfList);
		int sumOfRowResults = Model.getSumOfListOfNumbers(this.rowOfExpectedResults);
		this.columnOfExpectedResults = Model.generateRandomNumbersMeetingSum(lengthOfList, sumOfRowResults);
		
		this.cellPositionOfRowOfResults = Model.generateIndexesForRowOfResults(lengthOfList);
		this.cellPositionOfColumnOfResults = Model.generateIndexesForColumnOfResults(lengthOfList);
	}

	public ArrayList<Integer> getRowOfExpectedResults() {
		return rowOfExpectedResults;
	}
	
	public ArrayList<Integer> getColumnOfExpectedResults() {
		return columnOfExpectedResults;
	}

	public int[] getCellPositionOfRowOfResults() {
		return cellPositionOfRowOfResults;
	}

	public int[] getCellPositionOfColumnOfResults() {
		return cellPositionOfColumnOfResults;
	}

	private static ArrayList<Integer> generateRandomNumbers (int lengthOfList) {
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(lengthOfList);
		
		for (int i = 0; i < lengthOfList; i++) {
			randomNumbers.add(Model.random.nextInt(maxNumber - minNumber + 1) + minNumber);
		}

		return randomNumbers;
	}
	
	private static ArrayList<Integer> generateRandomNumbersMeetingSum (int lengthOfList, int sum) {
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(lengthOfList);
		int sumOfNewArray = 0;
		
		while(!canRandomNumberListAddUpToGivenSum(sum, sumOfNewArray) || sumOfNewArray == 0) {
			sumOfNewArray = 0;
			for (int i = 0; i < (lengthOfList - 1); i++) {
				randomNumbers.add(i, Model.random.nextInt(maxNumber - minNumber + 1) + minNumber);
				sumOfNewArray += randomNumbers.get(i);
			}
		}
		
		randomNumbers.add(lengthOfList - 1, sum - sumOfNewArray);
		
		sumOfNewArray += randomNumbers.get(lengthOfList - 1);

		return randomNumbers;
	}
	
	private static boolean canRandomNumberListAddUpToGivenSum(int sum, int newArraySum) {
		if (newArraySum > sum) {
			return false;
		}
		
		int difference = sum - newArraySum;
		
		if (difference < 0) {
			return false;
		}
		
		boolean differenceIsBiggerOrEqualThanMinNumber = difference >= minNumber;
		boolean differenceIsSmallerOrEqualThanMaxNumber = difference <= maxNumber;
		
		return differenceIsBiggerOrEqualThanMinNumber && differenceIsSmallerOrEqualThanMaxNumber;
	}
	
	private static int getSumOfListOfNumbers (ArrayList<Integer> list) {	
		int sum = 0;
		
		for (Integer currentNumber: list) {
			sum += currentNumber; 
		}

		return sum;
	}
	
	private static int[] generateIndexesForColumnOfResults (int lengthOfList) {
		int[] indexes = new int[lengthOfList];
		indexes[0] = lengthOfList;
		
		for (int i = 1; i < lengthOfList; i++) {
			indexes[i] = indexes[i - 1] + (lengthOfList + 1);
		}
		
		return indexes;
	}
	
	private static int[] generateIndexesForRowOfResults (int lengthOfList) {
		int[] indexes = new int[lengthOfList];
		int indexOfFirstCellOfLastRow = lengthOfList * (lengthOfList + 1);
		indexes[0] = indexOfFirstCellOfLastRow ;
		
		for (int i = 1; i < lengthOfList; i++) {
			indexes[i] = indexes[i - 1] + 1;
		}
		
		return indexes;
	}
	
	public int getExpectedResultFromCell (ListOfCells entity, int position) {
		if (position > dimensionOfGrid) {
			throw new IllegalArgumentException("La posicion tiene que ser menor o igual que las dimensiones de la tabla");
		}
		
		if (entity == ListOfCells.COLUMN) {
			return columnOfExpectedResults.get(position - 1);
		}
		
		return rowOfExpectedResults.get(position - 1);
	}
}
