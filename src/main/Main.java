package main;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		// generating a button to check for the win
		
		// changing color of Objectives to make it prettier
		textFieldList.get(3).setBackground(Color.BLUE);
		textFieldList.get(8).setBackground(Color.BLUE);
		textFieldList.get(13).setBackground(Color.BLUE);
		textFieldList.get(18).setBackground(Color.BLUE);
		textFieldList.get(19).setBackground(Color.BLUE);
		textFieldList.get(20).setBackground(Color.BLUE);
		textFieldList.get(21).setBackground(Color.BLUE);
		textFieldList.get(22).setBackground(Color.BLUE);
		
		

		JButton checkButton = new JButton("Check!");

		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// start calculating the firstRow and compare with firstObjective
				int firstValue = Integer.parseInt(textField.getText());
				int secondValue = Integer.parseInt(textFieldList.get(0).getText());
				int thirdValue = Integer.parseInt(textFieldList.get(1).getText());
				int fourthValue = Integer.parseInt(textFieldList.get(2).getText());

				int firstObjective = Integer.parseInt(textFieldList.get(3).getText());
				int firstRow = firstValue + secondValue + thirdValue + fourthValue;

				if (firstRow == firstObjective) {
					textField.setBackground(Color.YELLOW);
					textFieldList.get(0).setBackground(Color.YELLOW);
					textFieldList.get(1).setBackground(Color.YELLOW);
					textFieldList.get(2).setBackground(Color.YELLOW);
				} else {
					textField.setBackground(Color.WHITE);
					textFieldList.get(0).setBackground(Color.WHITE);
					textFieldList.get(1).setBackground(Color.WHITE);
					textFieldList.get(2).setBackground(Color.WHITE);
				}

				// start calculating the secondRow and compare with secondObjective
				int fifthValue = Integer.parseInt(textFieldList.get(4).getText());
				int sixthValue = Integer.parseInt(textFieldList.get(5).getText());
				int seventhValue = Integer.parseInt(textFieldList.get(6).getText());
				int eightValue = Integer.parseInt(textFieldList.get(7).getText());

				int secondObjective = Integer.parseInt(textFieldList.get(8).getText());
				int secondRow = fifthValue + sixthValue + seventhValue + eightValue;

				if (secondRow == secondObjective) {
					textFieldList.get(4).setBackground(Color.YELLOW);
					textFieldList.get(5).setBackground(Color.YELLOW);
					textFieldList.get(6).setBackground(Color.YELLOW);
					textFieldList.get(7).setBackground(Color.YELLOW);
				} else {
					textFieldList.get(4).setBackground(Color.WHITE);
					textFieldList.get(5).setBackground(Color.WHITE);
					textFieldList.get(6).setBackground(Color.WHITE);
					textFieldList.get(7).setBackground(Color.WHITE);
				}

				// start calculating the thridRow and compare with thirdObjective
				int ninthValue = Integer.parseInt(textFieldList.get(9).getText());
				int tenthValue = Integer.parseInt(textFieldList.get(10).getText());
				int eleventhValue = Integer.parseInt(textFieldList.get(11).getText());
				int twelveValue = Integer.parseInt(textFieldList.get(12).getText());

				int thirdObjective = Integer.parseInt(textFieldList.get(13).getText());
				int thirdRow = ninthValue + tenthValue + eleventhValue + twelveValue;

				if (thirdRow == thirdObjective) {
					textFieldList.get(9).setBackground(Color.YELLOW);
					textFieldList.get(10).setBackground(Color.YELLOW);
					textFieldList.get(11).setBackground(Color.YELLOW);
					textFieldList.get(12).setBackground(Color.YELLOW);
				} else {
					textFieldList.get(9).setBackground(Color.WHITE);
					textFieldList.get(10).setBackground(Color.WHITE);
					textFieldList.get(11).setBackground(Color.WHITE);
					textFieldList.get(12).setBackground(Color.WHITE);
				}

				// start calculating the thridRow and compare with thirdObjective
				int thirteenValue = Integer.parseInt(textFieldList.get(14).getText());
				int fourteenValue = Integer.parseInt(textFieldList.get(15).getText());
				int fifteenValue = Integer.parseInt(textFieldList.get(16).getText());
				int sixteenValue = Integer.parseInt(textFieldList.get(17).getText());

				int fourthObjective = Integer.parseInt(textFieldList.get(18).getText());
				int fourthRow = thirteenValue + fourteenValue + fifteenValue + sixteenValue;

				if (fourthRow == fourthObjective) {
					textFieldList.get(14).setBackground(Color.YELLOW);
					textFieldList.get(15).setBackground(Color.YELLOW);
					textFieldList.get(16).setBackground(Color.YELLOW);
					textFieldList.get(17).setBackground(Color.YELLOW);
				} else {
					textFieldList.get(14).setBackground(Color.WHITE);
					textFieldList.get(15).setBackground(Color.WHITE);
					textFieldList.get(16).setBackground(Color.WHITE);
					textFieldList.get(17).setBackground(Color.WHITE);
				}

			}

		});

		// this arrays contains the textFields in the ArrayList where we should place
		// the 8 random values
		int[] textFieldsWithFirstBatch = { 3, 8, 13, 18 };
		int[] textFieldsWithSecondBatch = { 19, 20, 21, 22 };

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

		while (sumOfSecondBatchNumbers <= (sum - maxNumber) && sumOfSecondBatchNumbers < sum - minNumber
				|| sumOfSecondBatchNumbers > sum || sumOfSecondBatchNumbers == 0) {
			sumOfSecondBatchNumbers = 0;
			for (int i = 0; i < lengthOfRandomNumberArray - 1; i++) {
				secondBatchOfRandomNumbers[i] = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
				sumOfSecondBatchNumbers += secondBatchOfRandomNumbers[i];
			}

		}

		secondBatchOfRandomNumbers[lengthOfRandomNumberArray - 1] = sum - sumOfSecondBatchNumbers;

		sumOfSecondBatchNumbers += secondBatchOfRandomNumbers[lengthOfRandomNumberArray - 1];

		for (int i = 0; i < textFieldsWithFirstBatch.length; i++) {
			int indexOne = textFieldsWithFirstBatch[i];
			int indexTwo = textFieldsWithSecondBatch[i];
			textFieldList.get(indexOne).setText(Integer.toString(firstBatchOfRandomNumbers[i]));
			textFieldList.get(indexTwo).setText(Integer.toString(secondBatchOfRandomNumbers[i]));

		}

	}
}