package main;

import java.awt.EventQueue;

import model.Model;
import presenter.Presenter;
import view.View;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Presenter gameStarter = new Presenter(new View(), new Model());
				gameStarter.startGame();
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}