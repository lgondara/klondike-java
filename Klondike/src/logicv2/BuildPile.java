package logicv2;
//pile med annet vært kort svart og rødt
public class BuildPile extends CardPile {
	private static final long serialVersionUID = 1L;

	public BuildPile(){
		super();
	}
	
	//this objektet blir kappa av, og returneres som en ny buildpile chopet av
	public BuildPile chopBuildPile(int numberOfChoppyChops){
		BuildPile bp = new BuildPile();
		while(numberOfChoppyChops > 0){
			bp.push(this.pop());
			numberOfChoppyChops--;
		}
		return bp;
	}

	public BuildPile moveBuildPile(BuildPile target){
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
//		public static void main(String[] args) {
//			Card a = new Card(4, "H");
//			a.setFaceUp();
//			Card b = new Card(3, "C");
//			b.setFaceUp();
//			Card c = new Card(2, "H");
//			c.setFaceUp();
//			
//			Card d = new Card(4, "D");
//			d.setFaceUp();
//			
//			BuildPile bp = new BuildPile();;
//			bp.push(a);
//			bp.push(b);
//			bp.push(c);
//			
//			System.out.println(bp);
//			TableauPile tp = new TableauPile();
//			
//			tp.push(d);
//			System.out.println(tp);
//			
//			//buildpile splittes på 2 og flyttes fra H5(bp) til D5(tp)
//			
//			BuildPile newpile = bp.chopBuildPile(2);
////			BuildPile returnbp = newpile.moveBuildPile(tp);
//			System.out.println(returnbp);	
//		
//		}
}

