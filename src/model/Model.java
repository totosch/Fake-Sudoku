package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Model {
	private int minNumber;
	private int maxNumber;
	private int lengthOfList;
	private ArrayList<Integer> rowOfExpectedResults;
	private ArrayList<Integer> columnOfExpectedResults;
	private int dimensionOfGrid;
	private static Random random = new Random();
	
	public enum ListOfCells {
		ROW,
		COLUMN
	}
	
	public void setLengthOfListOfRandomNumbersAndGenerateStaticData (int lengthOfList) {
		this.minNumber = lengthOfList + 1;
		this.maxNumber = (lengthOfList + 1) * 3;
		this.dimensionOfGrid = lengthOfList;
		this.rowOfExpectedResults = generateRandomNumbers(lengthOfList);
		int sumOfRowResults = getSumOfListOfNumbers(this.rowOfExpectedResults);
		this.columnOfExpectedResults = generateRandomNumbersMeetingSum(lengthOfList, sumOfRowResults);
	}

	public ArrayList<Integer> getRowOfExpectedResults() {
		return rowOfExpectedResults;
	}
	
	public ArrayList<Integer> getColumnOfExpectedResults() {
		return columnOfExpectedResults;
	}
	
	public int getLengthOfList() {
		return lengthOfList;
	}

	private ArrayList<Integer> generateRandomNumbers (int lengthOfList) {
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(lengthOfList);
		
		for (int i = 0; i < lengthOfList; i++) {
			randomNumbers.add(Model.random.nextInt(maxNumber - minNumber + 1) + minNumber);
		}

		return randomNumbers;
	}
	
	private ArrayList<Integer> generateRandomNumbersMeetingSum (int lengthOfList, int sum) {
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
	
	private boolean canRandomNumberListAddUpToGivenSum(int sum, int newArraySum) {
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
	
	private int getSumOfListOfNumbers (ArrayList<Integer> list) {	
		int sum = 0;
		
		for (Integer currentNumber: list) {
			sum += currentNumber; 
		}

		return sum;
	}
	
	public int getExpectedResultFromCell (ListOfCells entity, int position) {
		if (position > dimensionOfGrid) {
			throw new IllegalArgumentException("La posicion tiene que ser menor o igual que las dimensiones de la tabla");
		}
		
		if (entity == ListOfCells.COLUMN) {
			return columnOfExpectedResults.get(position);
		}
		
		return rowOfExpectedResults.get(position);
	}
	
	public boolean manageNewHighScore(int newElapsedTime) throws IOException {
		String srcDirectoryPath = System.getProperty("user.dir") + "/src";
		String filePath = srcDirectoryPath + "/" + "highest_score.txt";
		
		BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
		
		String currentHighestScore = fileReader.readLine();
		
		fileReader.close();
		
		if (Integer.parseInt(currentHighestScore) <= newElapsedTime) {
			return false;
		}
		
		FileWriter fileWriter = new FileWriter(filePath);
			
		fileWriter.write(Integer.toString(newElapsedTime));
		
		fileWriter.close();
		
		return true;
	}
}