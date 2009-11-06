package logic;

/**
 * 
 * Klasse for Tablåbunker
 *
 */
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
	
	/**
	 * Metode som trekker et kort fra en bunke og pusher det inn i en annen
	 */
	public void drawCard(CardPile cardPile) {	
		if (canTake(cardPile.peek())) {
			this.push(cardPile.pop());
		}
	}

	public boolean canTake( BuildPile buildPile) {

		if (canTake(buildPile.peek())) {
			return true;
		}
		return false;
	}
	
	public void drawBuild(CardPile cardPile, BuildPile buildPile) {
		while (cardPile.size()>0) {
			if(cardPile.peek().getFaceUp()) {
				buildPile.push(cardPile.pop());
			}
			else {
				break;
			}
		}
		if (canTake(buildPile)) {
			while(buildPile.size()>0) {
				this.push(buildPile.pop());
			}
		}
	}
}