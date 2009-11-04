package logikk;

public class TableauPile extends CardPile {

	public TableauPile() {
		
	}
	
	public boolean canTake(Card card) {
		if(isEmpty()) {
			return card.getFace() == 12;
		}
		else {
			Card top = top();
			return (card.getSuit() != top.getSuit() 
					&& card.getFace() == top.getFace()-1);
		}
	}
	
	// fix me
	public boolean canTake(BuildPile pile) {
		return false;
	}
	
	public void drawCard() {
		
	}
	
	public void drawBuild() {
		
	}
}

