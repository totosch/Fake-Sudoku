package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

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

		// generating a button to check for the win

		// changing color of Objectives to make it prettier
		int[] cellsWithObjectives = {4, 9, 14, 19, 20, 21, 22, 23};
		Color color = Color.CYAN;
		for (int index : cellsWithObjectives) {
		    textFieldList.get(index).setBackground(color);
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

				if (firstRow == rowObjectives[0] && firstColumn == colObjectives[0]
						|| firstRow == rowObjectives[0] && secondColumn == colObjectives[1]
						|| firstRow == rowObjectives[0] && thirdColumn == colObjectives[2]
						|| firstRow == rowObjectives[0] && fourthColumn == colObjectives[3]) {

					for (int i = 0; i < 4; i++) {
						JTextField textFields = textFieldList.get(i);
						textFields.setBackground(Color.GREEN);
					}
				} else {
					for (int i = 0; i < 4; i++) {
						JTextField textField = textFieldList.get(i);
						textField.setBackground(Color.WHITE);
					}
				}

				if (secondRow == rowObjectives[1] && firstColumn == colObjectives[0]
						|| secondRow == rowObjectives[1] && secondColumn == colObjectives[1]
						|| secondRow == rowObjectives[1] && thirdColumn == colObjectives[2]
						|| secondRow == rowObjectives[1] && fourthColumn == colObjectives[3]) {

					for (int i = 5; i < 9; i++) {
						JTextField textFields = textFieldList.get(i);
						textFields.setBackground(Color.GREEN);
					}
				} else {
					for (int i = 5; i < 9; i++) {
						JTextField textField = textFieldList.get(i);
						textField.setBackground(Color.WHITE);
					}
				}

				if (thirdRow == rowObjectives[2] && firstColumn == colObjectives[0]
						|| thirdRow == rowObjectives[2] && secondColumn == colObjectives[1]
						|| thirdRow == rowObjectives[2] && thirdColumn == colObjectives[2]
						|| thirdRow == rowObjectives[2] && fourthColumn == colObjectives[3]) {

					for (int i = 10; i < 14; i++) {
						JTextField textFields = textFieldList.get(i);
						textFields.setBackground(Color.GREEN);
					}
				} else {
					for (int i = 10; i < 14; i++) {
						JTextField textField = textFieldList.get(i);
						textField.setBackground(Color.WHITE);
					}
				}

				if (fourthRow == rowObjectives[3] && firstColumn == colObjectives[0]
						|| fourthRow == rowObjectives[3] && secondColumn == colObjectives[1]
						|| fourthRow == rowObjectives[3] && thirdColumn == colObjectives[2]
						|| fourthRow == rowObjectives[3] && fourthColumn == colObjectives[3]) {
					for (int i = 15; i < 19; i++) {
						JTextField textFields = textFieldList.get(i);
						textFields.setBackground(Color.GREEN);
					}
				} else {
					for (int i = 15; i < 19; i++) {
						JTextField textField = textFieldList.get(i);
						textField.setBackground(Color.WHITE);
					}
				}

			}

		});

		// this arrays contains the textFields in the ArrayList where we should place
		// the 8 random values
		int[] textFieldsWithFirstBatch = { 4, 9, 14, 19 };
		int[] textFieldsWithSecondBatch = { 20, 21, 22, 23 };

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

		int sumOfNewArray = 0;
		int[] secondBatchOfRandomNumbers = new int[4];

		while (!((sum - sumOfNewArray) >= minNumber && (sum - sumOfNewArray) <= maxNumber) || sumOfNewArray == 0) {
			sumOfNewArray = 0;
			for (int i = 0; i < lengthOfRandomNumberArray - 1; i++) {
				secondBatchOfRandomNumbers[i] = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
				sumOfNewArray += secondBatchOfRandomNumbers[i];
			}
		}

		secondBatchOfRandomNumbers[lengthOfRandomNumberArray - 1] = sum - sumOfNewArray;

		sumOfNewArray += secondBatchOfRandomNumbers[lengthOfRandomNumberArray - 1];

		for (int i = 0; i < textFieldsWithFirstBatch.length; i++) {
			int indexOne = textFieldsWithFirstBatch[i];
			int indexTwo = textFieldsWithSecondBatch[i];
			textFieldList.get(indexOne).setText(Integer.toString(firstBatchOfRandomNumbers[i]));
			textFieldList.get(indexTwo).setText(Integer.toString(secondBatchOfRandomNumbers[i]));

		}

	}
}
