package main;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

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

		JTextField[] arrayOfTextFields = { new JTextField(), new JTextField(), new JTextField(), new JTextField("Random Number 1"), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField("Random Number 2"), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField("Random Number 3"), new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField("Random Number 4"), new JTextField("Random Number 5"), new JTextField("Random Number 6"), new JTextField("Random Number 7"), new JTextField("Random Number 8"), new JTextField("--------------------------------") };

		for (JTextField textFields : arrayOfTextFields) {
			frame.getContentPane().add(textFields);
		}

	}
}
