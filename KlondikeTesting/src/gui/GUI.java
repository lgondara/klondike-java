package gui;

import java.awt.*;
import javax.swing.*;


public class GUI extends JFrame {
	
	private JFrame window;
	private Table table;
	
	public GUI() {
		window = new JFrame("Klondike");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(table);
		window.pack();
		window.setVisible(true);
		
		table.refreshMouseAreas();
	}
	
	public void repaint() {
		table.repaint();
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
}
