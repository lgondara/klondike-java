package logic;
//pile med annet vært kort svart og rødt
public class BuildPile extends CardPile {
	private static final long serialVersionUID = 1L;

	public BuildPile(){
		super();
	}

	public BuildPile moveBuildPile(TableauPile target){
		CardPile tempPopPile = new CardPile();
		BuildPile returnPile = new BuildPile();
		
		if(target.canTake(this.get(0))){
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
			Card c = new Card(4, "H");
			c.setFaceUp();
			
			BuildPile bp = new BuildPile();;
			bp.push(a);
			bp.push(b);
			
			System.out.println(bp);
			TableauPile tp = new TableauPile();
			
			tp.push(c);
			System.out.println(tp);
			BuildPile returnbp = bp.moveBuildPile(tp);
			System.out.println(returnbp);	
		
		}
}

