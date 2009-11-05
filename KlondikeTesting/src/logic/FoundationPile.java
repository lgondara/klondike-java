package logic;

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
			Card top = this.peek();
			return (card.getSuit() == top.getSuit()) 
			&& (card.getFace() == (top.getFace()+1));
		}
	}

	public void addCard(Card card) {
		if(this.canTake(card)){
			this.push(card);
			this.peek().setFaceUp();
		}
	}

	//FIX
	public void addBuild(BuildPile pile) {

	}

	//FIX
	public boolean solved() {
		return this.peek().getFace() == 12;
	}
}

