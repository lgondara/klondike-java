package logic;

import java.util.Stack;

/**Generell klasse som alle andre piles arver fra
 * 
 *
 */
public class CardPile extends Stack<Card>{
	
	public CardPile() {
		super();
	}
	
	public boolean canTake(Card card) {
		return false;
	}
	
	public void addCard(Card card) {
		this.push(card);
	}

}