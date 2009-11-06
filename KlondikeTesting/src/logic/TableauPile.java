package logic;

public class TableauPile extends CardPile {
//tableaupile er de 7 bunkene
	
	public boolean canTakeBuildPile(Card card) {
		if(isEmpty()) {
			return card.getFace() == 12;
		}
		else {
			return (!card.getColor().equals(this.peek().getColor()) 
					&& card.getFace() == this.peek().getFace()-1);
		}
	}
	
	public boolean canTake(Card card) {
		if(isEmpty()) {
			return card.getFace() == 12;
		}
		else {
			return (card.getSuit() != this.peek().getSuit() 
					&& card.getFace() == this.peek().getFace()-1)
					&& card.getFaceUp();
		}
	}
	
	
	public boolean canTake(BuildPile pile) {
		return false;
	}
	
	public void drawCard(CardPile cardPile) {	
		System.out.println(canTake(this.peek()));
		if (canTake(this.peek())) {
			cardPile.push(this.pop());
			this.peek().setFaceUp();
		}
	}
	
	public void drawBuild() {
		
	}
}

