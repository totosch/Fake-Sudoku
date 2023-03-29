package main;

import gameView.View;
import main.Main;

import java.awt.EventQueue;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View viewInstance = View.getViewInstance();
					viewInstance.generateMenu();
					viewInstance.initializeView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
