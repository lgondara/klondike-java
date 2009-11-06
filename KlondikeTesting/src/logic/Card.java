package logic;

import java.awt.Color;
import java.awt.Image;

/**
 * 
 * Klasse for å lage et Kort
 *
 */
public class Card {

	public int face;
	public String suit;
	private boolean faceUp;
	private String color;
	private String pic;


	public Card(int face, String suit) {
		this.suit = suit;
		this.face = face;
		if(this.suit == "C"){
			this.pic="♣";
		}
		if(this.suit == "S"){
			this.pic="♠";
		}
		if(this.suit == "H"){
			this.pic="♥";
		}
		if(this.suit == "D"){
			this.pic="♦";
		}
		if(this.suit == "C" || this.suit =="S"){
			this.color = "black";
		}
		else{
			this.color = "red";
		}
	}

	public String getPic() {
		return pic;
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
		if(getFaceUp()) {
			if (getFace() == 12) {
				return pic+"K";
			}
			else if (getFace() == 11) {
				return pic +"Q";
			}
			else if (getFace() == 10) {
				return pic +"J";
			}
			else if (getFace() == 0) {
				return pic +"A";
			}
			else {
				return pic+(face+1);
			}
		}
		else {
			return "Card";
		}
	}
}