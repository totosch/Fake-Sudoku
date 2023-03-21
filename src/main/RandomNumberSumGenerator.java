package main;
import java.util.Random;

public class RandomNumberSumGenerator {
	private int minNumber;
	private int maxNumber;
	private int amountOfNumbers;
	private Random random;
	private int sum;
	
	public RandomNumberSumGenerator(int minNumber, int maxNumber, int amountOfNumbers) {
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		this.amountOfNumbers = amountOfNumbers;
		this.random = new Random();
	}
	
	public int[] generateRandomNumbersAndItsSum () {
		int[] randomNumbers = new int[amountOfNumbers];
		int sum = 0;
			
		for (int i = 0; i < amountOfNumbers; i++) {
			randomNumbers[i] = random.nextInt(maxNumber - minNumber + 1) + minNumber;
		}
		
		this.sum = sum;
		
		return randomNumbers;
	}
	
	public int [] generateRandomNumberMeetingSum () {
		int[] randomNumbers = new int[amountOfNumbers];
		int currentSum = 0;
		
		while (!((sum - currentSum) >= minNumber && (sum - currentSum) <= maxNumber) || currentSum == 0) {
			currentSum = 0;
			for (int i = 0; i < amountOfNumbers - 1; i++) {
				randomNumbers[i] = random.nextInt(maxNumber - minNumber + 1) + minNumber;
				currentSum += randomNumbers[i];
			}
		}

		randomNumbers[amountOfNumbers - 1] = sum - currentSum;

		currentSum += randomNumbers[amountOfNumbers - 1];
		
		return randomNumbers;
	}
	
	public void setAmountOfNumbers(int amountOfNumbers) {
		this.amountOfNumbers = amountOfNumbers;
	}
}
