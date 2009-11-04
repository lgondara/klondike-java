package logikk;

import java.util.ArrayList;
import java.util.Stack;

/**
 *Lager Brett av typen ArrayList: dvs at hver plass i arraylisten tilsvarer en plass på brett i GUI
 *Plass 0: Neste kort fra kortstokken 
 *Plass 1-7: Plassene som holder på kortene nede
 *Plass 8-11: Plassene hvor man legger opp kort
 *Hver plass i ArrayListen inneholder en stack, som igjen holder på masse kort. 
 *En stack har metoden pop() som kan brukes til å hente det øverste kortet.
 **/
public class Board extends ArrayList<Stack<Card>>{
	private static final long serialVersionUID = 1L;

	private ArrayList<Stack<Card>> brett;

	public Board(){
		brett = new ArrayList<Stack<Card>>();


	}

	public void delUt(CardDeck cd){

		int nedteller = 7;
		while(nedteller !=0){


		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardDeck cd = new CardDeck();
		cd.buildDeck();
		cd.shuffle();
		System.out.println(cd.getCard(0));
		Board b = new Board();

	}

}
