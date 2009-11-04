package logikk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Klondike extends JFrame {
	
	private FoundationPile[] foundation;
	private TableauPile[] tableau;
	private ThrowPile throwPile;
	private DrawPile drawPile;
	private JFrame window; 
	private JPanel pane;
	
	// fix me
	public Klondike() {
		init();
	}
	
	public void init() {
		foundation = new FoundationPile[4];
		tableau = new TableauPile[7];
		
		throwPile = new ThrowPile();
		drawPile = new DrawPile();
		
		window = new JFrame("Klondike");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setSize(800, 600);
		pane = new JPanel();
		pane.setPreferredSize(new Dimension(1024, 600));
		pane.setBackground(Color.PINK);
		window.getContentPane().add(pane);
		window.pack();
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		Klondike game = new Klondike();
	}

	public CardPile getTableau(int pos) {
		return tableau[pos];
	}
	
	public CardPile[] getTableaus() {
		return tableau;
	}
	
	public CardPile getFoundation(int pos) {
		return foundation[pos];
	}
	
	public CardPile[] getFoundations() {
		return foundation;
	}
	
	public CardPile getThrowPile() {
		return throwPile;
	}
	
	public CardPile getDrawPile() {
		return drawPile;
	}
	
	public boolean solved() {
		for (int i = 0; i < foundation.length; i++) {
			if (foundation[i].solved()) {
				return true;
			}
		}
		return false;
	}
	
}