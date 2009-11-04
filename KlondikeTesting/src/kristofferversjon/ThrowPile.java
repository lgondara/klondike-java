package kristofferversjon;
//throwpile er der de kortene du trekker ender
public class ThrowPile extends CardPile {
	
	public ThrowPile() {
		super();
	}
	
	//FIXXXXXXXXXxXxX
	public void addCard(CardPile source) {
		if(this.isEmpty()){
			this.push(source.pop());
		}
	}
	
	public void drawCard(CardPile target){
		target.push(this.pop());
	}
	
	public static void main(String[] args) {
		
		DrawPile dp = new DrawPile();
		ThrowPile tp = new ThrowPile();
		System.out.println(tp);
		tp.drawCard(dp);
		System.out.println(dp);
		System.out.println(tp);
		
	}
}