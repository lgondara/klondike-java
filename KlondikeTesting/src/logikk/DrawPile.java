package logikk;

public class DrawPile extends CardPile {
	
	ThrowPile throwPile;
	
	//FIX throwPile osv..
	public void drawCard(CardPile pile) {
		if(!isEmpty()) {
			throwPile.addCard(pile.pop());
		}
	}
}
