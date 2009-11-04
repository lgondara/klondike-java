package kristofferversjon;

public class TableauPile extends CardPile {
//tableaupile er de 7 bunkene
	public TableauPile() {
		
	}
	
	public boolean canTake(Card card) {
		if(isEmpty()) {
			return card.getFace() == 12;
		}
		else {
			Card top = peek();
			return (card.getSuit() != top.getSuit() 
					&& card.getFace() == top.getFace()-1);
		}
	}
	
	// fix me
	public boolean canTake(BuildPile pile) {
		return false;
	}
	
	public void drawCard(Card card) {
		if (canTake(card)) {
			addCard(this.pop());
		}
	}
	
	public void drawBuild() {
		
	}
}

