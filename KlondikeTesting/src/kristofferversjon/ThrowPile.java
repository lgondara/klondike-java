package kristofferversjon;
//throwpile er der de kortene du trekker ender
public class ThrowPile extends CardPile {
	
	public ThrowPile() {
		super();
	}
	
	//FIXXXXXXXXXxXxX
	public void drawCard(CardPile throwPile, CardPile source) {
		if(!isEmpty()) {
			source.push(throwPile.pop());
		}
	}
}