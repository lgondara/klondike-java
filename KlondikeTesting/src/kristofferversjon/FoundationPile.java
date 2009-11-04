package kristofferversjon;

public class FoundationPile extends CardPile {
//foundation er der du bygger opp gjennom spillet

	
	public FoundationPile() {
		super();
	}
	
	public boolean canTake(Card card) {
		if(isEmpty()) {
			return card.getFace() == 0;
		}	
		else {
			Card top = peek();
			return (card.getSuit() == top.getSuit()) 
					&& (card.getFace() == (top.getFace()+1));
		}
	}
	
	//FIX
	public void addCard(Card card) {
		if(canTake(card)) {
			push(card);
		}
	}
	
	//FIX
	public void addBuild(BuildPile pile) {
		
	}
	
	//FIX
	public boolean solved() {
		return false;
	}
	
}

