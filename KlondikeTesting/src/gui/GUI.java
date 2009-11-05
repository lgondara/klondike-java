package gui;

import java.awt.*;
import javax.swing.*;
import logic.Card;
import logic.DrawPile;
import logic.Klondike;


public class GUI extends JFrame {
	
	private JFrame window;
	private JPanel panel;
	private Card card;
	private JTextArea text;
	
	
	
	public GUI() {
		window = new JFrame("Klondike");
		text = new JTextArea();
		text.setPreferredSize(new Dimension(1024, 600));
		text.setEditable(false);
		//panel.setBackground(Color.PINK);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(text);
		window.pack();
		window.setVisible(true);
//		panel.add(text = new JTextArea());
		Klondike klondike = new Klondike();
		fett(klondike);

		//card.setImg(new ImageIcon(getClass().getResource("../Cards/1.bmp")).getImage());
	}
	
	public void fett(Klondike klondike) {
		klondike.dealAllCards();
		text.setText(klondike.getDrawPile().peek().toString() + "\n" +
					 klondike.getThrowPile().peek().toString()
					 );
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
}
