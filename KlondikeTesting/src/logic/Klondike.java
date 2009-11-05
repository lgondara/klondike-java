package logic;

public class Klondike {

	private FoundationPile[] foundation;
	private TableauPile[] tableau;
	private ThrowPile throwPile;
	private DrawPile drawPile;



	// fix me
	public Klondike() {
		foundation = new FoundationPile[4];
		for(int i = 0; i< foundation.length ; i++){
			foundation[i] = new FoundationPile();
		}
		tableau = new TableauPile[7];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = new TableauPile();
		}
		throwPile = new ThrowPile();
		drawPile = new DrawPile();
	}

	public boolean solved() {
		for (int i = 0; i < foundation.length; i++) {
			if (foundation[i].solved()) {
				return true;
			}
		}
		return false;
	}	

	//superhax klasse deler ut alt!
	public void dealAllCards(){
		for(int i = 0; i<7;i++){
			int counter = 0;
			for(int j = counter; j<7;j++){
				this.tableau[j].push(this.drawPile.pop());
				counter++;
			}
			Card c = this.tableau[i].pop();
			c.setFaceUp();
			this.tableau[i].push(c);
			System.out.println(tableau[i]);
		}
		System.out.println("Antall kort i drawpile nå: " + this.drawPile.size()+ "       WTF?!!!!!!!!");
	}



	public static void main(String[] args) {
		Klondike k = new Klondike();
		k.dealAllCards();
		
	}


	public CardPile getTableau(int pos) {
		return this.tableau[pos];
	}

	public CardPile[] getTableaus() {
		return this.tableau;
	}

	public CardPile getFoundation(int pos) {
		return this.foundation[pos];
	}

	public CardPile[] getFoundations() {
		return this.foundation;
	}

	public CardPile getThrowPile() {
		return this.throwPile;
	}

	public CardPile getDrawPile() {
		return this.drawPile;
	}


}