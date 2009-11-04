package kristofferversjon;

public class Klondike {
	
	private CardPile[] foundation;
	private CardPile[] tableau;
	private CardPile throwPile;
	private CardPile drawPile;
	
	// fix me
	public Klondike() {
		foundation = new CardPile[4];
		tableau = new CardPile[7];
		
		throwPile = new CardPile();
		drawPile = new CardPile();
	}
	
	public CardPile getTableau(int pos) {
		return tableau[pos];
	}
	
	public CardPile[] getTableaus() {
		return tableau;
	}
	
	public CardPile getFoundation(int pos) {
		return foundation[pos];
	}
	
	public CardPile[] getFoundations() {
		return foundation;
	}
	
	public CardPile getThrowPile() {
		return throwPile;
	}
	
	public CardPile getDrawPile() {
		return drawPile;
	}
	
	public void solved() {
		
	}
	
	
	
}
