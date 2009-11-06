package logic;

import java.util.Scanner;

public class Klondike {

	private FoundationPile[] foundation;
	private TableauPile[] tableau;
	private BuildPile[] buildPile;
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
		buildPile = new BuildPile[7];
		for (int i = 0; i < buildPile.length; i++) {
			buildPile[i] = new BuildPile();
		}
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
	
	//Denne skal flytte riktig fra en bunke til en annen
	public void moveFromOnePileToTheAnother(int onePile, int anotherPile){
		//litt oversikt bare:
//		BuildPile onePileBuildPile = this.buildPile[onePile];
//		BuildPile anotherPileBuildPile = this.buildPile[anotherPile];
//		TableauPile onePileTableauPile = this.tableau[anotherPile];
//		TableauPile anotherPileTableauPile = this.tableau[anotherPile];
		
		if(this.buildPile[onePile].size() == 0){
			Card temp = this.tableau[onePile].pop();
			temp.setFaceUp();
			this.buildPile[onePile].push(temp);
		}
		
	}

	public void pushThrowCardToFoundation(FoundationPile fp){
		if(!this.throwPile.isEmpty()){
			if(fp.canTake(this.throwPile.peek())){
				this.throwPile.drawCard(fp);
			}
		}
	}
	
	/**
	 * Metode for å skrive ut brettet
	 */
	public void printTablaeu() {
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
				System.out.print(this.foundation[i].peek().toString());
			}
		}
		System.out.println("\n");
		
		for (int i = 0; i < tableau.length; i++) {
			System.out.println("L" + i + ":  " + this.tableau[i].toString());
		}
	}
	
	
	public void pushThrowCardToTableau(TableauPile tp){
		if(!this.throwPile.isEmpty()){
			if(tp.canTake(this.throwPile.peek())){
				this.throwPile.drawCard(tp);
			}
		}
	}
	
	/**
	 * Metode som følger med på trekk og oppdaterer spillet
	 */
	public void play() {
		String move = "";
		Scanner scanner = new Scanner(System.in);
		this.printTablaeu();
		while (!move.equals("done") || this.solved()) {
			move = scanner.nextLine();
			
			//Flytte kort fra Trekkbunke til Kastebunke
			if (move.equals("D")) {
				this.drawPile.drawCard(this.throwPile);
			}
			
			//Funker ikke, canTake() gir false uansett.
			else if (move.matches("^L[0-6]L[0-6]$")) {
				this.tableau[Integer.parseInt(move.substring(1, 2))]
				             .drawCard(this.tableau[Integer.parseInt(move.substring(3))]);
			}
			
			//Flytte kort fra Tablå til Fundament
			else if (move.matches("^L[0-6]F[0-3]$")) {
				this.foundation[Integer.parseInt(move.substring(3))]
				                .addCard(this.tableau[Integer.parseInt(move.substring(1, 2))]);
				this.tableau[Integer.parseInt(move.substring(1, 2))].peek().setFaceUp();
			}
			
			//Flytte kort fra Kastebunken til Fundament
			else if (move.matches("^TF[0-3]$")) {
				this.foundation[Integer.parseInt(move.substring(2))].addCard(this.throwPile);
			}
			
			//Funker ikke, samme problem som LXLX
			else if (move.matches("^TL[0-6]$")) {
				this.tableau[Integer.parseInt(move.substring(2))]
				                .drawCard(this.throwPile);
			}
			
			//Ikke testet
			else if (move.matches("^F[0-3]L[0-6]$")) {
				this.tableau[Integer.parseInt(move.substring(1, 2))]
				             .drawCard(this.foundation[Integer.parseInt(move.substring(3))]);
			}
			
			this.printTablaeu();
		}
		System.out.println("Enten vant du eller så ga du opp, gratulerer!");
		scanner.close();
	}
	
	public static void main(String[] args) {		
		Klondike k = new Klondike();		
		k.play();
	}
	
	

}