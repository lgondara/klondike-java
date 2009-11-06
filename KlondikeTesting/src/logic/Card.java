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
		if(getFaceUp()) {
			if (getFace() == 12) {
				return suit +"K";
			}
			else if (getFace() == 11) {
				return suit +"Q";
			}
			else if (getFace() == 10) {
				return suit +"J";
			}
			else if (getFace() == 0) {
				return suit +"A";
			}
			else {
				return suit+(face+1);
			}
		}
		else {
			return "Card";
		}
	}
}