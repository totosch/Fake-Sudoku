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

		// end of generating random numbers


		ArrayList<JTextField> textFieldList = new ArrayList<>();

		for (int i = 0; i < 23; i++) {
			JTextField textField = new JTextField();
			textFieldList.add(textField);
		}

		int[] textFieldsWithRandomNumbers = { 3, 8, 13, 18, 19, 20, 21, 22 };

		
		for (int i = 0; i < textFieldsWithRandomNumbers.length; i++) {
			int indexOfRandomPosition = textFieldsWithRandomNumbers[i];
			textFieldList.get(indexOfRandomPosition).setText(Integer.toString(numbers[i]));
		}

		JButton checkButton = new JButton("Check!");

		for (JTextField textFields : textFieldList) {
			frame.getContentPane().add(textFields);
		}
		frame.getContentPane().add(checkButton);
		
		Random rand = new Random();
		int[] firstBatchOfRandomNumbers = new int[4];
		int lengthOfRandomNumberArray = firstBatchOfRandomNumbers.length;
		int sum = 0;

		int minNumber = 5;
		int maxNumber = 15;

		// Generate 4 random numbers
		for (int i = 0; i < lengthOfRandomNumberArray; i++) {
			firstBatchOfRandomNumbers[i] = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
			sum += firstBatchOfRandomNumbers[i];
		}

		int sumOfSecondBatchNumbers = 0;
		int[] secondBatchOfRandomNumbers = new int[4];
		
		System.out.println("pppppp" + (sum - maxNumber));

		while (sumOfSecondBatchNumbers <= (sum - maxNumber) || sumOfSecondBatchNumbers > sum
				|| sumOfSecondBatchNumbers == 0) {
			sumOfSecondBatchNumbers = 0;
			for (int i = 0; i < lengthOfRandomNumberArray - 1; i++) {
				secondBatchOfRandomNumbers[i] = rand.nextInt(maxNumber - minNumber  + 1) + minNumber;
				sumOfSecondBatchNumbers += secondBatchOfRandomNumbers[i];
			}

			System.out.println("numbers" + sumOfSecondBatchNumbers);
			System.out.println("totalSum" + sum);
		}

		secondBatchOfRandomNumbers[lengthOfRandomNumberArray - 1] = sum - sumOfSecondBatchNumbers;

		sumOfSecondBatchNumbers += secondBatchOfRandomNumbers[lengthOfRandomNumberArray - 1];

		System.out.println("first" + Arrays.toString(firstBatchOfRandomNumbers));
		System.out.println('s' + Arrays.toString(secondBatchOfRandomNumbers));

		// 50: 10 - 11 - 14. 50 - 33: 15

		// Si la suma dividido 4 es mayor a alguno de los numeros que tenemos no
		// permitir que sea ese numero
		// desde que numero random? = (suma / cantidad de elementos) - ( numero mas
		// chico / 2 )

		// Generar ocho numeros random para sumarlos. La suma la dividis por dos. El
		// resultado de la division es lo que suma cada lado.
		//

		System.out.println(sum);

	}
}
