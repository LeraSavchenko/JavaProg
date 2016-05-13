package ArrowStrategy;

import java.awt.Graphics;

import testFrame.Arrow;

public class DrawDown implements DrawArrowStrategy {
	
	public void drawNiceArrow(Graphics g, Arrow arrow) {
		
        int x1 = (arrow.getStart().getIcon().getWidth() / 2) + arrow.getStart().getIcon().getX();
        int y1 = arrow.getStart().getIcon().getHeight() + arrow.getStart().getIcon().getY();
        int x2 = (arrow.getEnd().getIcon().getWidth() / 2) + arrow.getEnd().getIcon().getX();
        int y2 = arrow.getEnd().getIcon().getY();
		
		int[] xPoints = {x2, x2 + 4, x2 - 4};   //(int) xm, (int) xn};
	    int[] yPoints = {y2, y2 - 20, y2 - 20}; //(int) ym, (int) yn};
	        
	    if (x1 == x2) {
	    	g.drawLine(x1, y1, x2, y2); //maybe remove
	    }
	    else {
	    	g.drawLine(x1, y1, x1, y1 - (y1 - y2)/2);
	    	g.drawLine(x1, y1 - (y1 - y2)/2, x2, y1 - (y1 - y2)/2);
	    	g.drawLine( x2, y1 - (y1 - y2)/2, x2, y2);
	    }
	    //draw polygon
	    g.fillPolygon(xPoints, yPoints, 3);
	}

}
