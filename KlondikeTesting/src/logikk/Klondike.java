package logikk;

public class Klondike {
	
	private FoundationPile[] foundation;
	private TableauPile[] tableau;
	private ThrowPile throwPile;
	private DrawPile drawPile;
	// fix me
	public Klondike() {
		init();
	}
	
	public void init() {
		foundation = new FoundationPile[4];
		tableau = new TableauPile[7];
		
		throwPile = new ThrowPile();
		drawPile = new DrawPile();
		
	}
	
	public static void main(String[] args) {
		Klondike game = new Klondike();
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
	
	public boolean solved() {
		for (int i = 0; i < foundation.length; i++) {
			if (foundation[i].solved()) {
				return true;
			}
		}
		return false;
	}
	
}