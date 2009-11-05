package logic;

import java.awt.Image;

public class Card {

	public int face;
	public String suit;
	private boolean faceUp;
	private String color;
	private int X;
	private int Y;
	private Image img = null;
	
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
		return suit+face;
	}

	public void setX(int x) {
		this.X = x;
	}

	public int getX() {
		return this.X;
	}

	public void setY(int y) {
		this.Y = y;
	}

	public int getY() {
		return this.Y;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Image getImg() {
		return img;
	}
}