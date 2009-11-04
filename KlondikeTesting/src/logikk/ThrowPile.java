package logikk;

public class ThrowPile extends CardPile {
	
	public ThrowPile() {
		
	}
	
	//FIXXXXXXXXXxXxX
	public void drawCard(CardPile throwPile, CardPile source) {
		if(!isEmpty()) {
			source.push(throwPile.pop());
		}
	}
	
	public void addCard(Card card) {
		this.push(card);
	}
}