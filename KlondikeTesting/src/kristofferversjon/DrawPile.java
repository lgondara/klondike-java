package kristofferversjon;
//drawpile er der du trekker kort 
public class DrawPile extends CardPile {
	private static final long serialVersionUID = 1L;

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
				this.push(aCard);
			}
		}	
		this.shuffle();
		System.out.println("Lagde ny kortstokk" + this);
		System.out.println("Størrelse: "+ this.size());
		
	}
	

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

	//evt lage egen del ut metode
	/** int z; //antall kort som er tatt ut av bunken
	public Card[] deal(int x){

		Card[] hand = new Card [x];
		if (z+x>52){
			Card[] tom = new Card[0];
			return tom;
		}
		Card[] cards = new Card[x];
		for (int i=0; i<hand.length;i++){	
			cards[i] = this.getCard(this.z);
			z++;
		}

		return cards;

	}			
	**/
	
	public void drawCard(ThrowPile target) {
		if(this.isEmpty()) {
			for (int i = 0; i < target.size(); i++) {
				this.push(target.get(i));
			}
		}
		else {
			target.push(this.pop());
		}
	}

//testing
	public static void main(String[] args) {
		DrawPile dp = new DrawPile();	
	}

}
