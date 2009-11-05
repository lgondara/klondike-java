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

//testing
//	public static void main(String[] args) {
//		CardPile b = new CardPile();;
//		b.push(new Card(2, "C"));
//		b.push(new Card(2, "H"));
//		
//		BuildPile bp = new BuildPile();
//		bp.push(new Card(3,"C"));
//		bp.push(b);
//		bp.push(b);
//		
//		System.out.println(bp);
//	}
}

