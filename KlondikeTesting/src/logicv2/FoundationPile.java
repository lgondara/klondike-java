package logicv2;

public class FoundationPile extends CardPile {

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

	public void addCard(CardPile cardPile) {
		if(this.canTake(cardPile.peek())){
			this.push(cardPile.pop());
		}
		else {
			System.err.println("Illegal move!");
		}
	}

	//FIX
	public void addBuild(BuildPile pile) {

	}

	public boolean solved() {
		return this.peek().getFace() == 12;
	}
}

