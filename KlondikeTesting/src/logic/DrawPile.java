package logic;

/** Klasse for Trekkbunke
 * 
 * 
 *
 */
public class DrawPile extends CardPile {

	/**
	 * Konstruktør som bygger en kortstokk på 52 kort for å så stokke den.
	 * Setter også hvert kort med face ned.
	 */
	public DrawPile(){
		for (int i=0;i<4;i++){
			for (int j=0;j<13;j++){
				String suit = "";
				if (i == 0)
					suit = "S"; 
				else if (i == 1)
					suit = "H";
				else if (i == 2)
					suit = "D";
				else if (i == 3)
					suit = "C";	
				Card aCard = new Card(j,suit);
				aCard.setFaceDown();
				this.push(aCard);
			}
		}	
		this.shuffle();
	}
	
	/** Metode for å stokke en kortstokk på 52 kort.
	 * 
	 */
	public void shuffle(){
		Card temp;
		for (int i = 0;i<1000;i++){
			int num1 = (int)(Math.random()*52);
			int num2 = (int)(Math.random()*52);
			temp = this.get(num1);
			this.set(num1, this.get(num2));
			this.set(num2, temp);
		}
	}
	
	/** Metode for å trekke et kort fra trekkbunken og legge den i Kastebunken. 
	 *  Er Trekkbunken tom flyttes kortene i Kastebunken over til Trekkbunken.
	 * 
	 * @param target
	 */
	public void drawCard(ThrowPile target) {
		if(this.isEmpty()) {
			while (target.size()>0) {
				target.peek().setFaceDown();
				this.push(target.pop());
			}
		}
		else {
			target.addCard(this);
		}
	}
}
