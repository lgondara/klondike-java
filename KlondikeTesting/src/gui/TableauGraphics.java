package gui;

import java.awt.Graphics;
import java.awt.Rectangle;

import logic.Card;

public class TableauGraphics extends PileGraphics
{
	private final int CARDOFFSET = 17;
	
	public TableauGraphics(Table table, Pile pile, int x, int y)
	{
		super(table, pile, x, y);
	}
	public void paint(Graphics g)
	{
		Card card = null;
		for(int i = 0; i < getPile().getSize(); i++) {
			
			card = getPile().getCard(i);
			card.setX(getX());
			card.setY(getY()+(i*CARDOFFSET));
			
			g.drawImage(card.getImg(), card.getX(), card.getY(), getTable());
		}
		
	}
	public void updateMouseArea()
	{
		if (!getPile().isEmpty()) {
			mouseArea = new Rectangle(getX(), getY(), Table.CARDWIDTH, ((getPile().getSize() - 1) * CARDOFFSET) + Table.CARDHEIGHT);
		} else {
			mouseArea = new Rectangle(getX(), getY(), Table.CARDWIDTH, Table.CARDHEIGHT);
		}
	}
	
}

