package logic;

public class TableauPile extends CardPile {
//tableaupile er de 7 bunkene
	
	public boolean canTake(Card card) {
		if(isEmpty()) {
			return card.getFace() == 12;
		}
		else {
			Card top = this.peek();
			return (card.getColor() != top.getColor() 
					&& card.getFace() == top.getFace()-1);
		}
	}
	
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

