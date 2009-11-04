package kristofferversjon;

public class BuildPile extends CardPile {

	public BuildPile(){
		super();
	}
	
	public void push(CardPile source){
		if(this.peek().getColor() != source.peek().getColor()){
			this.push(source.pop());
		}
	}
	
	//testing
	public static void main(String[] args) {
		BuildPile bp = new BuildPile();
		bp.push(new Card(1, "S"));
		System.out.println(bp);
	}
}
