package logikk;

public class DrawPile extends CardPile {
	
	ThrowPile throwPile;
	
	//FIX throwPile osv..
	public void drawCard(CardPile pile) {
		if(pile.top().getFaceUp()) {
			throwPile.addCard(pile.pop());
		
		}
	}
}
