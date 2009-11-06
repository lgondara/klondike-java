package logic;

public class TableauPile extends CardPile {
//tableaupile er de 7 bunkene
	
	public boolean canTake(Card card) {
		if(isEmpty()) {
			return card.getFace() == 12;
		}
		else {
			return (card.getColor() != this.peek().getColor() 
					&& card.getFace() == this.peek().getFace()-1)
					&& this.peek().getFaceUp();
		}
	}
	
	
//	public boolean canTake(BuildPile pile) {
//		return false;
//	}
	
	public void drawCard(CardPile cardPile) {	
		System.out.println(canTake(this.peek()));
		if (canTake(this.peek())) {
			this.peek().setFaceUp();
			cardPile.push(this.pop());
		}
	}
	
	public void drawBuild() {
		
	}
}

