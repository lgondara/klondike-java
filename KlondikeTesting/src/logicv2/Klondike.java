package logicv2;

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
		int cardsToDeal = 1;
		for (TableauPile currentTableauPile : tableau) {
			for (int i = 0; i < cardsToDeal; i++) {
				//System.out.println(this.drawPile.size());
				currentTableauPile.push(this.drawPile.pop());
			}
			Card card =currentTableauPile.pop();
			card.setFaceUp();
			this.buildPile[cardsToDeal-1].push(card);
			cardsToDeal++;				
		}

		//debug();
		//System.out.println("Ferdig med å dele ut ut");
	}

	//private void debug() {
	//	System.err.println("Lengden er: " + this.drawPile.size());
	//}
	
	public DrawPile getDrawPile() {
		return this.drawPile;
	}
	
	public ThrowPile getThrowPile() {
		return this.throwPile;
	}


	public void dealThrowCards(){
		this.throwPile.addCard(this.drawPile);
	}
	
	//Metode som vender om på kort som ligger uvendte i tableau
	public void autoturnPile(int index){
		if(this.buildPile[index].size() == 0){
			Card temp1 = this.tableau[index].pop();
			temp1.setFaceUp();
			this.buildPile[index].push(temp1);
		}
	}
	
	public void moveFromOnePileToTheAnother(int onePile, int anotherPile){
		//litt oversikt bare:
		//	BuildPile onePileBuildPile = this.buildPile[onePile];
		//	BuildPile anotherPileBuildPile = this.buildPile[anotherPile];
		//	TableauPile onePileTableauPile = this.tableau[anotherPile];
		//	TableauPile anotherPileTableauPile = this.tableau[anotherPile];
		//	Buildpile: tenker at alle kort om er vendt opp i bunkene på brettet skal ligge i buildpile,
		//	Tableaupile: og alle kort som ligger ned ligger i tableaupile
		
		//nå kan vi flytte på piles	
		//sjekker om det er 1 ting i en buildpile, dvs flytter 1 kort fra onePile til anotherPile
		if(this.buildPile[onePile].size() == 1 && this.buildPile[anotherPile].size() == 1){
			this.buildPile[anotherPile] = this.buildPile[onePile].moveBuildPile(this.buildPile[anotherPile]);
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
		for(int i = 0; i < buildPile.length; i++){
			if(this.buildPile[i].isEmpty()){
				System.out.println("BuildPile empty");
			}
			else{
				System.out.println("B" + i + ":  " + this.buildPile[i].toString());
			}
		}
	}
	
	/*
	public void pushThrowCardToTableau(TableauPile tp){
		if(!this.throwPile.isEmpty()){
			if(tp.canTake(this.throwPile.peek())){
				this.throwPile.drawCard(tp);
			}
		}
	}
	*/
	
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
			
			else if (move.matches("^B[0-6]B[0-6]$")) {
				this.buildPile[Integer.parseInt(move.substring(3))].moveBuildPile(this.buildPile[Integer.parseInt(move.substring(1,2))]);
				if (!this.buildPile[Integer.parseInt(move.substring(1,2))].isEmpty()) {
					this.autoturnPile([Integer.parseInt(move.substring(1,2))]);
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
			
			// Flytte bygg fra Tablå til Tablå (IKKE IMPLEMENTERT ENDA!)
			else if (move.matches("^L[0-6]L[0-6]B$")) {
				//this.tableau[Integer.parseInt(move.substring(3))]
				//             .drawCard(this.tableau[Integer.parseInt(move.substring(1,2))]);
				//if (!this.tableau[Integer.parseInt(move.substring(1,2))].isEmpty()) {
				//	this.tableau[Integer.parseInt(move.substring(1,2))].peek().setFaceUp();
				//}
			}
			
			this.printGame();
		}
		System.out.println("Enten vant du eller så ga du opp, gratulerer!");
		scanner.close();
	}
	
	public static void main(String[] args) {		
		Klondike k = new Klondike();
//		k.printGame();
//		k.moveFromOnePileToTheAnother(1, 2);
//		k.printGame();
		k.play();
		
	}
}