package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Model {
	private int minRandomNumber;
	private int maxRandomNumber;
	private int gridSize;
	private ArrayList<Integer> expectedRowResults;
	private ArrayList<Integer> expectedColumnResults;
	private int dimensionOfGrid;
	private static Random random = new Random();
	
	public enum ListOfCells {
		ROW,
		COLUMN
	}
	
	public void setLengthOfListOfRandomNumbers (int lengthOfList) {
		this.minRandomNumber = lengthOfList + 1;
		this.maxRandomNumber = (lengthOfList + 1) * 3;
		this.dimensionOfGrid = lengthOfList;
		this.expectedRowResults = generateRandomNumbers(lengthOfList);
		int sumOfRowResults = getSumOfListOfNumbers(this.expectedRowResults);
		this.expectedColumnResults = randomNumbersSum(lengthOfList, sumOfRowResults);
	}

	public ArrayList<Integer> getRowOfExpectedResults() {
		return expectedRowResults;
	}
	
	public ArrayList<Integer> getColumnOfExpectedResults() {
		return expectedColumnResults;
	}
	
	public int getLengthOfList() {
		return gridSize;
	}

	private ArrayList<Integer> generateRandomNumbers (int lengthOfList) {
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(lengthOfList);
		
		for (int i = 0; i < lengthOfList; i++) {
			randomNumbers.add(Model.random.nextInt(maxRandomNumber - minRandomNumber + 1) + minRandomNumber);
		}

		return randomNumbers;
	}
	
	private ArrayList<Integer> randomNumbersSum (int lengthOfList, int sum) {
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>(lengthOfList);
		int sumOfNewArray = 0;
		
		while(!isRandomListValid(sum, sumOfNewArray) || sumOfNewArray == 0) {
			sumOfNewArray = 0;
			for (int i = 0; i < (lengthOfList - 1); i++) {
				randomNumbers.add(i, Model.random.nextInt(maxRandomNumber - minRandomNumber + 1) + minRandomNumber);
				sumOfNewArray += randomNumbers.get(i);
			}
		}
		
		randomNumbers.add(lengthOfList - 1, sum - sumOfNewArray);
		
		sumOfNewArray += randomNumbers.get(lengthOfList - 1);

		return randomNumbers;
	}
	
	private boolean isRandomListValid(int sum, int newArraySum) {
		if (newArraySum > sum) {
			return false;
		}
		
		int difference = sum - newArraySum;
		
		if (difference < 0) {
			return false;
		}
		
		boolean isDifferenceGreaterThanOrEqualToMin = difference >= minRandomNumber;
		boolean isDifferenceLessThanOrEqualToMax = difference <= maxRandomNumber;

		
		return isDifferenceGreaterThanOrEqualToMin && isDifferenceLessThanOrEqualToMax;
	}
	
	private int getSumOfListOfNumbers (ArrayList<Integer> list) {	
		int sum = 0;
		
		for (Integer number: list) {
			sum += number; 
		}

		return sum;
	}
	
	public int getExpectedResultFromCell (ListOfCells entity, int position) {
		if (position > dimensionOfGrid) {
			throw new IllegalArgumentException("La posicion tiene que ser menor o igual que las dimensiones de la tabla");
		}
		
		if (entity == ListOfCells.COLUMN) {
			return expectedColumnResults.get(position);
		}
		
		return expectedRowResults.get(position);
	}
	
	public boolean manageNewHighScore(int newElapsedTime) throws IOException {
	    String srcDirectoryPath = System.getProperty("user.dir") + "/src";
	    String filePath = srcDirectoryPath + "/" + "highscore.txt";
		int currentHighestScore = this.getCurrentHighestScore();
		
		if (currentHighestScore <= newElapsedTime) {
			return false;
		}
		
		FileWriter fileWriter = new FileWriter(filePath);			
		fileWriter.write(Integer.toString(newElapsedTime));		
		fileWriter.close();
		
		return true;
	}
	
	public int getCurrentHighestScore() throws IOException {
	    String srcDirectoryPath = System.getProperty("user.dir") + "/src";
	    String filePath = srcDirectoryPath + "/" + "highscore.txt";
	    
	    BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
	    
	    String currentHighestScore = fileReader.readLine();
	    
	    fileReader.close();
	    
	    return Integer.parseInt(currentHighestScore);
	}

}