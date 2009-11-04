package logikk;

public class CardDeck {
	
public Card[] cards;
	
	public void buildDeck(){
		cards = new Card[52];
		for (int i=0;i<4;i++){
			for (int j=0;j<13;j++){
				cards[j + i*13] = new Card();
				cards[j + i*13].face = j+1;
				if (i == 0)
					cards[j + i*13].suit = "S"; 
				else if (i == 1)
					cards[j + i*13].suit = "H";
				else if (i == 2)
					cards[j + i*13].suit = "D";
				else if (i == 3)
					cards[j + i*13].suit = "C";
			}
		}
		
	}
	
	public Card getCard(int index){
		return cards[index];
			
	}	
	public void shuffle(){
		Card temp;
		for (int i = 0;i<1000;i++){
			int num1 = (int)(Math.random()*52);
			int num2 = (int)(Math.random()*52);
			temp = cards[num1];
			cards[num1] = cards[num2];
			cards[num2] = temp;
		}
	
	}
	

	int z; //antall kort som er tatt ut av bunken
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

	public int getCardCount(){
		return (cards.length - z);
		
	}


}
