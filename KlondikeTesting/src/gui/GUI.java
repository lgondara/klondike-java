package gui;

import java.awt.*;
import javax.swing.*;


public class GUI extends JFrame {
	
	private JFrame window;
	private JPanel panel;
	
	public GUI() {
		window = new JFrame("Klondike");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setSize(800, 600);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1024, 600));
		panel.setBackground(Color.PINK);
		window.getContentPane().add(panel);
		window.pack();
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
}
