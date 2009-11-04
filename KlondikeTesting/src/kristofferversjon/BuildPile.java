package kristofferversjon;

public class BuildPile extends CardPile {
	
	public BuildPile() {
		super();
	}
	
	

	public BuildPile(){
		super();
	}
	
	public void push(Card card){
		if(this.peek().getColor() != card.getColor()){
			this.push(card);
		}
	}
}
