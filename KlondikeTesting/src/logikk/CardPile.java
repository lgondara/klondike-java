package logikk;

import java.util.ArrayList;
import java.util.Stack;

public class CardPile extends Stack {
	
	private ArrayList<Card> pile;
	
	public CardPile() {
		this.pile = new ArrayList<Card>();
	}
	
	//fiiiiix
	public CardPile(CardPile pile, int i) {
		this.pile = new ArrayList<Card>();
		
		for (i = 0; i < pile.getSize(); i++) {
			Card card = pile.getCard(i);
			this.pile.add(card);
		}
	}
	
	public boolean isEmpty() {
		return pile.empty();
	}
	
	public Card top() {
		return (Card)pile.peek();
	}
	
	public Card pop() {
		return (Card)pile.pop();
	}
	
	public void push(Card card) {
		pile.push(card);
	}
	
	public boolean canTake() {
		return false;
	}
	
	public int getSize() {
		return this.pile.size();
	}
	
	public Card getCard(int i) {
		return this.pile.get(i);
	}
}