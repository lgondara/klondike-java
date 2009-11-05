package logic;

import java.awt.Image;

public class Card {

	public int face;
	public String suit;
	private boolean faceUp;
	private String color;
	
	public Card(int face, String suit) {
		this.suit = suit;
		this.face = face;
		if(this.suit == "C" || this.suit =="S"){
			this.color = "black";
		}
		else{
			this.color = "red";
		}
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public String getColor(){
		return this.color;
	}
	
	public int getFace() {
		return this.face;
	}
	
	public void setFaceDown() {
		this.faceUp = false;
	}
	
	public void setFaceUp() {
		this.faceUp = true;
	}
	
	public boolean getFaceUp() {
		return faceUp;
	}
	
	public String toString() {
		return suit+face+faceUp;
	}
}