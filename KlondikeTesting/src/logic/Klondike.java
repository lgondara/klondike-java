package logic;

import java.util.Scanner;

public class Klondike {

	private FoundationPile[] foundation;
	private TableauPile[] tableau;
	private BuildPile buildPile;
	private ThrowPile throwPile;
	private DrawPile drawPile;

	/**
	 * Konstruktør som lager de forskjellige bunkene og starter utdeling av kort
	 */
	public Klondike() {
		
		foundation = new FoundationPile[4];
		for(int i = 0; i< foundation.length ; i++){
			foundation[i] = new FoundationPile();
		}
		
		tableau = new TableauPile[7];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = new TableauPile();
		}
		
		buildPile = new BuildPile();
		throwPile = new ThrowPile();
		drawPile = new DrawPile();
		
		this.dealAllCards();
	}

	/**
	 * Metode som sjekker om spillet er ferdig
	 * @return
	 */
	public boolean solved() {
		return this.foundation[0].solved() && this.foundation[1].solved() && this.foundation[2].solved() && this.foundation[3].solved();
	}
	
	/**
	 * Metode som deler ut kort til Tablåbunkene
	 */
	public void dealAllCards(){
		//gammel kode som ikke funker, sånn man egentlig skal dele ut
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
	}

	public DrawPile getDrawPile() {
		return this.drawPile;
	}
	
	public ThrowPile getThrowPile() {
		return this.throwPile;
	}
	
	/**
	 * Metode for å skrive ut brettet
	 */
	public void printGame() {
		if(this.drawPile.isEmpty()) {
			System.out.println("D: Empty");
		}
		else {
			System.out.println("D: " + this.getDrawPile().peek().toString());
		}
		
		if (this.throwPile.isEmpty()) {
			System.out.println("T: Empty\n");
		}
		else {
			System.out.println("T: " + this.getThrowPile().peek().toString() + "\n");
		}

		for (int i = 0; i < foundation.length; i++) {
			if (this.foundation[i].isEmpty()) {
				System.out.print("F" + i + ": ");
				System.out.print("Empty\t");
			}
			else {
				System.out.print("F" + i + ": ");
				System.out.print(this.foundation[i].peek().toString() + "\t");
			}
		}
		System.out.println("\n");
		
		for (int i = 0; i < tableau.length; i++) {
			if (this.tableau[i].isEmpty()) {
				System.out.println("L" + i + ":  Empty");
			}
			else {
				System.out.println("L" + i + ":  " + this.tableau[i].toString());
			}
		}
	}
	
	/**
	 * Metode som følger med på trekk og oppdaterer spillet
	 */
	public void play() {
		String move = "";
		Scanner scanner = new Scanner(System.in);
		this.printGame();
		while (!move.equals("done") || this.solved()) {
			move = scanner.nextLine();
			
			//Flytte kort fra Trekkbunke til Kastebunke
			if (move.equals("D")) {
				this.drawPile.drawCard(this.throwPile);
			}
			
			//Flytte kort fra Tablå til Tablå
			else if (move.matches("^L[0-6]L[0-6]$")) {
				this.tableau[Integer.parseInt(move.substring(3))]
				             .drawCard(this.tableau[Integer.parseInt(move.substring(1,2))]);
				if (!this.tableau[Integer.parseInt(move.substring(1,2))].isEmpty()) {
					this.tableau[Integer.parseInt(move.substring(1,2))].peek().setFaceUp();
				}
			}
			
			//Flytte kort fra Tablå til Fundament
			else if (move.matches("^L[0-6]F[0-3]$")) {
				this.foundation[Integer.parseInt(move.substring(3))]
				                .addCard(this.tableau[Integer.parseInt(move.substring(1, 2))]);
				if (!this.tableau[Integer.parseInt(move.substring(1, 2))].isEmpty()) {
					this.tableau[Integer.parseInt(move.substring(1, 2))].peek().setFaceUp();
				}
			}
			
			//Flytte kort fra Kastebunken til Fundament
			else if (move.matches("^TF[0-3]$")) {
				this.foundation[Integer.parseInt(move.substring(2))].addCard(this.throwPile);
			}
			
			//Flytte kort fra Kastebunke til Tablå
			else if (move.matches("^TL[0-6]$")) {
				this.tableau[Integer.parseInt(move.substring(2))].drawCard(this.throwPile);
			}
			
			//Flytte fra Fundamentet til Tablået
			else if (move.matches("^F[0-3]L[0-6]$")) {
				this.tableau[Integer.parseInt(move.substring(3))]
				             .drawCard(this.foundation[Integer.parseInt(move.substring(1,2))]);
			}
			
			// Flytte bygg fra Tablå til Tablå
			else if (move.matches("^L[0-6]L[0-6]B$")) {
				this.tableau[Integer.parseInt(move.substring(3,4))]
				             .drawBuild(this.tableau[Integer.parseInt(move.substring(1,2))], this.buildPile);
				if (!this.tableau[Integer.parseInt(move.substring(1,2))].isEmpty()) {
					this.tableau[Integer.parseInt(move.substring(1,2))].peek().setFaceUp();
				}
			}
			
			this.printGame();
		}
		System.out.println("Enten vant du eller så ga du opp, gratulerer!");
		scanner.close();
	}
	
	public static void main(String[] args) {		
		Klondike k = new Klondike();		
		k.play();

	}
}