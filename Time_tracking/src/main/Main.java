package main;

import gui.MainFrame;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {

		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
							
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
				frame.pack();
				
			}
		});
		
		
	}
}
