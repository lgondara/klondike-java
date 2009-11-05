package gui;

import java.awt.Graphics;
import java.awt.Rectangle;

import kristofferversjon.Card;

public class PileGraphics {
	
	private Pile pile;
	private Table table;
	private int x;
	private int y;
	protected Rectangle mouseArea;
	
	public PileGraphics(Table table, Pile pile, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.table = table;
		this.pile = pile;
	}
	public void paint(Graphics g)
	{
		Card card = null;
			
			for(int i = 0; i < pile.getSize(); i++) {
			
				card = pile.getCard(i);
				card.setX(x+(i*2));
				card.setY(y+(i*2));
				g.drawImage(card.getImg(), card.getX(), card.getY(), table);
			} 
	}
	public Rectangle getMouseArea()
	{
		return mouseArea;
	}
	public void updateMouseArea()
	{
		if (!pile.isEmpty()) {
			Card topCard = pile.getCard(pile.getSize()-1);
			//System.out.println(topCard.getX() + " " + topCard.getY());
			mouseArea = new Rectangle(topCard.getX(), topCard.getY(), Table.CARDWIDTH, Table.CARDHEIGHT);
		} else {
			mouseArea = new Rectangle(x, y, Table.CARDWIDTH, Table.CARDHEIGHT);
		}
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	protected Pile getPile()
	{
		return pile;
	}
	protected Table getTable()
	{
		return table;
	}
	
}
