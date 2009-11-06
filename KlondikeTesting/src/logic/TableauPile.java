package logic;

public class TableauPile extends CardPile {
	
	/**
	 * Metode som sjekker om en bunke kan ta et kort
	 */
	public boolean canTake(Card card) {
		if(isEmpty()) {
			return card.getFace() == 12;
		}	
		else {
			Card top = this.peek();
			return (card.getColor()) != top.getColor() 
			&& (card.getFace() == (top.getFace()-1));
		}
	}
	
	public boolean canTake(BuildPile pile) {
		return false;
	}
	
	/**
	 * Metode som trekker et kort fra en bunke og pusher det inn i en annen
	 */
	public void drawCard(CardPile cardPile) {	
		if (canTake(cardPile.peek())) {
			this.push(cardPile.pop());
		}
	}
	
	public void drawBuild() {
		
	}
}