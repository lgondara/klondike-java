package logikk;

public class Card {

	private int face;
	private String suit;
	private boolean faceUp;
	
	public Card(int face, String suit) {
		this.suit = suit;
		this.face = face;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getFace() {
		return face;
	}
	
	public void setFaceDown() {
		this.faceUp = false;
	}
	
	public void setFaceUp() {
		this.faceUp = true;
	}
	
	public String toString() {
		return suit+face;
	}
}