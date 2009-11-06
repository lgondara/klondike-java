package logic;

import java.util.Scanner;

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
			System.out.println("Throwpile: " + this.getThrowPile().peek().toString() + "\n\n");
		}
		
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
	
	public void play() {
		String move = "";
		Scanner scanner = new Scanner(System.in);
		this.printTablaeu();
		
		while (!move.equals("done")) {
			move = scanner.nextLine();
			if (move.equals("D")) {
				this.drawPile.drawCard(this.throwPile);
				System.out.println("Kort igjen: " + this.getDrawPile().size());
			}
			else if () {
				
			}
			this.printTablaeu();
		}
		scanner.close();
	}
	
	public static void main(String[] args) {		
		Klondike k = new Klondike();		
		k.play();
	}
	
	

}