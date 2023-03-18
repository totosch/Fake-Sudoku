package main;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(5, 5));

		textField = new JTextField();

		frame.getContentPane().add(textField);
		textField.setColumns(10);

		// 8 random numbers that will become the player's objective
		Random randomNumber = new Random();
		int[] numbers = new int[8];

		for (int i = 0; i < numbers.length; i++) {
			int num = randomNumber.nextInt(11) + 5;
			numbers[i] = num;
		}

		// generating the textFields for the inputs of the player
		ArrayList<JTextField> textFieldList = new ArrayList<>();

		for (int i = 0; i < 23; i++) {
			JTextField textField = new JTextField();
			textFieldList.add(textField);
		}

		// this arrays contains the textFields in the ArrayList where we should place
		// the 8 random values
		int[] textFieldsWithFirstBatch = { 3, 8, 13, 18 };
		int[] textFieldsWithSecondBatch = { 19, 20, 21, 22 };

		// generating a button to check for the win
		JButton checkButton = new JButton("Check!");

		// adding the button and the textFields to the frame
		for (JTextField textFields : textFieldList) {
			frame.getContentPane().add(textFields);
		}
		frame.getContentPane().add(checkButton);

		Random rand = new Random();
		int[] firstBatchOfRandomNumbers = new int[4];
		int lengthOfRandomNumberArray = firstBatchOfRandomNumbers.length;
		int sum = 0;

		// we used 5 and 15 to create a range between them
		int minNumber = 5;
		int maxNumber = 15;

		for (int i = 0; i < lengthOfRandomNumberArray; i++) {
			firstBatchOfRandomNumbers[i] = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
			sum += firstBatchOfRandomNumbers[i];
		}

		int sumOfSecondBatchNumbers = 0;
		int[] secondBatchOfRandomNumbers = new int[4];

		while (sumOfSecondBatchNumbers <= (sum - maxNumber) 
				&& sumOfSecondBatchNumbers < sum - minNumber
				|| sumOfSecondBatchNumbers > sum
				|| sumOfSecondBatchNumbers == 0) {
			sumOfSecondBatchNumbers = 0;
			for (int i = 0; i < lengthOfRandomNumberArray - 1; i++) {
				secondBatchOfRandomNumbers[i] = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
				sumOfSecondBatchNumbers += secondBatchOfRandomNumbers[i];
			}

		}

		secondBatchOfRandomNumbers[lengthOfRandomNumberArray - 1] = sum - sumOfSecondBatchNumbers;

		sumOfSecondBatchNumbers += secondBatchOfRandomNumbers[lengthOfRandomNumberArray - 1];

		for (int i = 0; i < textFieldsWithFirstBatch.length; i++) {
			int indexOfRandomPosition = textFieldsWithFirstBatch[i];
			textFieldList.get(indexOfRandomPosition).setText(Integer.toString(firstBatchOfRandomNumbers[i]));
		}

		for (int i = 0; i < textFieldsWithSecondBatch.length; i++) {
			int indexOfRandomPosition = textFieldsWithSecondBatch[i];
			textFieldList.get(indexOfRandomPosition).setText(Integer.toString(secondBatchOfRandomNumbers[i]));
		}

	}
}
