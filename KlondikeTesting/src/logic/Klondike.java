package logic;

public class Klondike {

	private FoundationPile[] foundation;
	private TableauPile[] tableau;
	private BuildPile[] buildPile;
	private ThrowPile throwPile;
	private DrawPile drawPile;

	public Klondike() {
		foundation = new FoundationPile[4];
		for(int i = 0; i< foundation.length ; i++){
			foundation[i] = new FoundationPile();
		}
		tableau = new TableauPile[7];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = new TableauPile();
		}
		buildPile = new BuildPile[7];
		for (int i = 0; i < buildPile.length; i++) {
			buildPile[i] = new BuildPile();
		}
		throwPile = new ThrowPile();
		drawPile = new DrawPile();
		
		this.dealAllCards();
	}

	public boolean solved() {
		return this.foundation[0].solved() && this.foundation[1].solved() && this.foundation[2].solved() && this.foundation[3].solved();
	}
	//superhax klasse deler ut alt!
	public void dealAllCards(){
		//gammel kode som ikke funker
		//		for(int i = 0; i<7;i++){
		//			int counter = 0;
		//			debug();
		//			for(int j = counter; j<7;j++){
		//				this.tableau[j].push(this.drawPile.pop());
		//				counter++;
		//				debug();
		//			}
		//			Card c = this.tableau[i].pop();
		//			c.setFaceUp();
		//			this.tableau[i].push(c);
		//			System.out.println(tableau[i]);
		//		}

		int cardsToDeal = 1;
		for (TableauPile currentTableauPile : tableau) {
			for (int i = 0; i < cardsToDeal; i++) {
				//System.out.println(this.drawPile.size());
				currentTableauPile.push(this.drawPile.pop());
			}
			currentTableauPile.get(currentTableauPile.size() - 1).setFaceUp();
			cardsToDeal++;

			//System.out.println(currentTableauPile);

			System.out.print(currentTableauPile);
			
		}

		//debug();
		System.out.println("Ferdig med å dele ut ut");
	}

	private void debug() {
		System.err.println("Lengden er: " + this.drawPile.size());
	}
	
	public DrawPile getDrawPile() {
		return this.drawPile;
	}
	
	public ThrowPile getThrowPile() {
		return this.throwPile;
	}

	public void dealThrowCards(){
		this.throwPile.addCard(this.drawPile);
	}

	public void pushThrowCardToFoundation(FoundationPile fp){
		if(!this.throwPile.isEmpty()){
			if(fp.canTake(this.throwPile.peek())){
				this.throwPile.drawCard(fp);
			}
		}
	}
	
	public void printTablaeu() {
		for (int i = 0; i < foundation.length; i++) {
			if (this.foundation[i].isEmpty()) {
				System.out.print("Empty\t");
			}
			else {
				System.out.print(this.foundation[i].peek().toString());
			}
		}
		
		System.out.println("");
		System.out.println("");
		
		if(this.drawPile.isEmpty()) {
			System.out.println("Drawpile: Empty");
		}
		else {
			System.out.println("Drawpile: " + this.getDrawPile().peek().toString());
		}
		
		if (this.throwPile.isEmpty()) {
			System.out.println("ThrowPile: Empty");
		}
		else {
			System.out.println("Throwpile: " + this.getThrowPile().peek().toString());
		}
		
		System.out.println("");
		System.out.println("");
		
		for (int i = 0; i < tableau.length; i++) {
			System.out.println(this.tableau[i].toString());
		}
	}
	
	
	public void pushThrowCardToTableau(TableauPile tp){
		if(!this.throwPile.isEmpty()){
			if(tp.canTake(this.throwPile.peek())){
				this.throwPile.drawCard(tp);
			}
		}
	}
	
	//setter opp ett tenkt spille scenario:
	public static void main(String[] args) {
		System.out.println("testing 1: ");
		
		Klondike k = new Klondike();
		//spiller trykker på kortbunken og dealer ut i throwbunken:
		//k.dealThrowCards();
		
		k.printTablaeu();
		//k.dealThrowCards();
		//System.out.println(k.throwPile);
		//FoundationPile cp = new FoundationPile();
		//k.pushThrowCardToFoundation(cp);
		//k.pushThrowCardToFoundation(cp);
		//System.out.println(cp);
		k.dealThrowCards();
		System.out.println(k.throwPile);
		//spiller tar kort fra throw og legger det på en tableau:
		k.pushThrowCardToTableau(k.tableau[0]);
		
		System.out.println();
		System.out.println("testing 2: prøver å flytte en buildpile oppå en tableaupile");
		BuildPile bp = new BuildPile();
		Card a = new Card(5, "S");
		a.setFaceUp();
		Card b = new Card(4, "H");
		b.setFaceUp();
		bp.push(a);
		bp.push(b);
		System.out.println("bp: " + bp);
		TableauPile tp = new TableauPile();
		Card c = new Card(6, "H");
		c.setFaceUp();
		tp.push(c);
		System.out.println("tp: " + tp);
		bp.moveBuildPile(tp);
		System.out.println("tp: " + tp);
		System.out.println("bp: " +bp);
		
	}
	
	

}