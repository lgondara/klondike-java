package logic;

public class BuildPile extends CardPile {
	private static final long serialVersionUID = 1L;

	public BuildPile(){
		super();
	}
	
	public void push(CardPile source){
		if(!this.isEmpty()){
			if(this.peek().getColor() != source.peek().getColor()){
				this.push(source.pop());
			}
			else{
				System.out.println("Feil farge");
				
			}
		}
	}
	
	//testing
	public static void main(String[] args) {
		CardPile b = new CardPie();
		p.push(1, "")
		BuildPile bp = new BuildPile();
		bp.push(new Card(1,"D"));
		while(enBunke.size()>0){
		bp.push(enBunke);
		System.out.println(bp);
		}
	}
}
