package logic;
//pile med annet vært kort svart og rødt
public class BuildPile extends CardPile {
	private static final long serialVersionUID = 1L;

	public BuildPile(){
		super();
	}
	
//	public void push(TableauPile source, int cardMoveFrom){
//		if(source.canTake(this.get(cardMoveFrom))){
//			Card c = source.pop();
//			c.setFaceUp();
//			this.push(c);
//		}
//	}

	public BuildPile moveBuildPile(TableauPile target, int i){
		CardPile tempPopPile = new CardPile();
		BuildPile returnPile = new BuildPile();
		
		if(target.canTake(this.get(i))){
			while(this.size() > 0){
				tempPopPile.push(this.pop());
			}
			returnPile.push(target.pop());
			while(tempPopPile.size()>0){
				returnPile.push(tempPopPile.pop());
			}
			return returnPile;
		}
		return null;
	}

//	testing
		public static void main(String[] args) {
			Card a = new Card(3, "C");
			a.setFaceUp();
			Card b = new Card(2, "H");
			b.setFaceUp();
			Card c = new Card(4, "C");
			c.setFaceUp();
			
			BuildPile bp = new BuildPile();;
			bp.push(b);
			bp.push(a);
			
			System.out.println(bp);
			TableauPile tp = new TableauPile();
			
			tp.push(c);
			System.out.println(tp);
			BuildPile returnbp = bp.moveBuildPile(tp, 1);
			System.out.println(returnbp);
			

			
		
		}
}

