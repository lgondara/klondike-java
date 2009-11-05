package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Table extends Canvas implements MouseListener, MouseMotionListener {

	private PileGraphics drawPile;
	private PileGraphics throwPile;
	private TableauGraphics[] tableaus;
	private PileGraphics[] foundations;
	public static final int CARDWIDTH = 70;
	public static final int CARDHEIGHT = 96;
	private final int TOPROWY = 30;
	private final int BOTTOMROWY = 200;
	private Pile draggingPile = null;
	private Pile draggingFromPile;
	private int dragOffsetX;
	private int dragOffsetY;
	private Solitaire solitaire;
	
	public Table(TableTop tableTop, Solitaire solitaire) 
	{
		this.solitaire = solitaire;
		foundations = new PileGraphics[tableTop.getFoundations().length];
		for (int i = 0; i < foundations.length; i++) {
			foundations[i] = new PileGraphics(this, tableTop.getFoundation(i), 360+(i*100), TOPROWY);
		}
		
		tableaus = new TableauGraphics[tableTop.getTableaus().length];
		for (int i = 0; i < tableaus.length; i++) {
			tableaus[i] = new TableauGraphics(this, tableTop.getTableau(i), 60+(i*100) , BOTTOMROWY);
		}
		
		drawPile = new PileGraphics(this, tableTop.getDrawPile(), 30, TOPROWY);
		throwPile = new PileGraphics(this, tableTop.getThrowPile(), 160, TOPROWY);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void refreshMouseAreas()
	{
		for (int i = 0; i < foundations.length; i++) {
			foundations[i].updateMouseArea();
		}
		for (int i = 0; i < tableaus.length; i++) {
			tableaus[i].updateMouseArea();
		}
		drawPile.updateMouseArea();
		throwPile.updateMouseArea();
	}
    public void update(Graphics g) 
    {
    	Graphics offgc;
    	Image offscreen = null;
    	Dimension d = size();

    	// create the offscreen buffer and associated Graphics
    	offscreen = createImage(d.width, d.height);
    	offgc = offscreen.getGraphics();
    	// clear the exposed area
    	offgc.setColor(getBackground());
    	offgc.fillRect(0, 0, d.width, d.height);
    	offgc.setColor(getForeground());
    	// do normal redraw
    	paint(offgc);
    	// transfer offscreen to window
    	g.drawImage(offscreen, 0, 0, this);
        }

	public void paint(Graphics g)
	{
		g.setColor(new Color(56,162,30));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);

		g.drawRect(drawPile.getX(), drawPile.getY(), CARDWIDTH, CARDHEIGHT);
		drawPile.paint(g);
		
		g.drawRect(throwPile.getX(), throwPile.getY(), CARDWIDTH, CARDHEIGHT);
		throwPile.paint(g);
		
		for(int i = 0; i < foundations.length; i++) {
			g.drawRect(foundations[i].getX(), foundations[i].getY(), CARDWIDTH, CARDHEIGHT);
			foundations[i].paint(g);
		}
		
		for (int i = 0; i < tableaus.length; i++) {
			g.drawRect(tableaus[i].getX(), tableaus[i].getY(), CARDWIDTH, CARDHEIGHT);
			tableaus[i].paint(g);
		}
		
		if(draggingPile != null) {
			for (int i = 0; i < draggingPile.getSize(); i++ ) {
				g.drawImage(draggingPile.getCard(i).getImg(), getMousePosition().x - dragOffsetX, (getMousePosition().y - dragOffsetY) + (i*17), this);

			}
		}
	}

	public PileGraphics getClickedPile(int x, int y) {
		
		if(drawPile.getMouseArea().contains(x, y)) {
			return drawPile;
		}
		if(throwPile.getMouseArea().contains(x, y) && !throwPile.getPile().isEmpty()) {
			return throwPile;
		}
		for (int i = 0; i < tableaus.length; i++) {
			if(tableaus[i].getMouseArea().contains(x, y) && !tableaus[i].getPile().isEmpty()) {
				return tableaus[i];
			}
		}
		return null;	
	}
	
	public PileGraphics getPressedPile(int x, int y) {
		if(throwPile.getMouseArea().contains(x, y) && !throwPile.getPile().isEmpty()) {
			return throwPile;
		}
		for (int i = 0; i < tableaus.length; i++) {
			if(tableaus[i].getMouseArea().contains(x, y)) {
				return tableaus[i];
			}
		}
		for (int i = 0; i < foundations.length; i++) {
			if(foundations[i].getMouseArea().contains(x, y)) {
				return foundations[i];
			}
		}
		return null;
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		
		refreshMouseAreas();
		PileGraphics pile = getClickedPile(e.getX(), e.getY());
		if(pile != null) {
			if (solitaire.pileClick(pile.getPile(), e.getClickCount())) {
				repaint();
				refreshMouseAreas();
			}
		}
	}

	public void mousePressed(MouseEvent e) 
	{
		PileGraphics pile = getPressedPile(e.getX(), e.getY());
		if(pile != null && !pile.getPile().isEmpty()) {
			int cardClicked = 0;
			
			// we need to get which card in a tableau was pressed
			if(pile instanceof TableauGraphics) {
				for(int i = (pile.getPile().getSize() - 1); i > -1; i--) {
					if(pile.getPile().getCard(i).getY() < e.getY()) {
						cardClicked = i;
						break;
					}
				}
			// for the other piles, it's the top card
			} else {
				cardClicked = pile.getPile().getSize() - 1;	
			}
			draggingPile = new Pile(pile.getPile(),cardClicked);
			dragOffsetX = e.getX() - draggingPile.getCard(0).getX();
			dragOffsetY = e.getY() - draggingPile.getCard(0).getY();
			draggingFromPile = pile.getPile();	
		}	
	}

	public void mouseReleased(MouseEvent e) 
	{
		if(draggingPile != null) {
			PileGraphics pile = getPressedPile(e.getX(), e.getY());
			if(pile != null) {
				
				if(solitaire.pileDragged(draggingPile, pile.getPile())) {
				// illegal move, reset stuff
				} else {
					draggingFromPile.appendPile(draggingPile);
				}
				
				draggingFromPile = null;
				draggingPile = null;
				repaint();
			// the pile was dragged onto nothing. lets go back to the way it was before the drag
			} else {
				draggingFromPile.appendPile(draggingPile);
				draggingFromPile = null;
				draggingPile = null;
				repaint();
			}
		}
	}
	public void mouseDragged(MouseEvent e) {
		if(draggingPile != null) {
			repaint();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {

	}
}