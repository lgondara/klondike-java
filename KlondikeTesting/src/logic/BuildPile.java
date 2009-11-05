package logic;
//pile med annet vært kort svart og rødt
public class BuildPile extends CardPile {
	private static final long serialVersionUID = 1L;

	public BuildPile(){
		super();
	}
	
//denne har egen metode for push siden det kreves spesielle regler her
	public void push(CardPile source){
		if(!this.isEmpty()){
			//sjekker om riktig farge
			if(this.peek().getColor() != source.peek().getColor()){
				//sjekker om riktig nummer
				if(this.peek().getFace()-1 == source.peek().getFace()){
					//hvis alt er ok kan man legge til kortet
					Card c = source.pop();
					c.setFaceUp();
					this.push(c);
				}
				else{
					System.out.println("Feil nummer");
				}
			}
			else{
				System.out.println("Feil farge");

			}
		}
	}
	
	//testen som tar bunnen av en buildpile(this) og sjekker om den passer oppå target
	public boolean canTake(Card target){
		return target.getFaceUp() && this.get(this.size()).getColor() != target.getColor() && this.get(this.size()).getFace() +1 == target.getFace();
	}
	
	//target er den pilen du vil flytte en buildpile(this) til. i er hvor i buildpilen(this) du vil flytte.
	public boolean canTake(Card target, int i){
		return target.getFaceUp() && this.get(i).getColor() != target.getColor() && this.get(i).getFace() +1 == target.getFace();
	}

	
	public void moveBuildPile(CardPile target){
		CardPile tempPopPile = new CardPile();
		if(this.canTake(target.peek())){
			while(this.size() > 0){
				tempPopPile.push(this.pop());
			}
			while(tempPopPile.size()>0){
				target.push(tempPopPile.pop());
			}
		}
	}

//testing
	public static void main(String[] args) {
		CardPile b = new CardPile();;
		b.push(new Card(2, "C"));
		b.push(new Card(2, "H"));
		
		BuildPile bp = new BuildPile();
		bp.push(new Card(3,"C"));
		bp.push(b);
		bp.push(b);
		
		System.out.println(bp);
	}
}

