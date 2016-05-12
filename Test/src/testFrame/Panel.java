package testFrame;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Panel extends JPanel{

    private ArrayList<Arrow> arrows = new ArrayList<>();

    public void addArrow(Arrow arrow) {
        for (Arrow current : arrows) {
            if (current.equals(arrow)) {
                return;
            }
            if (current.counterEdge(arrow)) {
                arrows.add(arrow);
                repaint();
                return;
            }
        }
        arrows.add(arrow);
        repaint();
    }

    public ArrayList<Arrow> getArrows() {
        return arrows;
    }

//    @Override
//	public void repaint() {
//    	
//    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Arrow arrow : arrows) {
        	if (arrow.getStart() != null && arrow.getEnd() != null) {
	            int x1 = (arrow.getStart().getIcon().getWidth() / 2) + arrow.getStart().getIcon().getX();
	            int y1 = arrow.getStart().getIcon().getHeight() + arrow.getStart().getIcon().getY();
	            int x2 = (arrow.getEnd().getIcon().getWidth() / 2) + arrow.getEnd().getIcon().getX();
	            int y2 = arrow.getEnd().getIcon().getY();
	            drawArrowLine(g, x1, y1, x2, y2);
        	}
        }
    }
    
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
//        int dx = x2 - x1, dy = y2 - y1, d = 22, h = 4;
//        double D = Math.sqrt(dx * dx + dy * dy);
//        double xm = D - d, xn = xm, ym = h, yn = -h, x;
//       double sin = dy / D, cos = dx / D;
//        x = xm * cos - ym * sin + x1;
//       ym = xm * sin + ym * cos + y1;
//       xm = x;
//        x = xn * cos - yn * sin + x1;
//       yn = xn * sin + yn * cos + y1;
//       xn = x;
    	
        //TODO maybe some pattern here(state for 2nd arrows)
        int[] xPoints = {x2, x2 + 4, x2 - 4};   //(int) xm, (int) xn};
        int[] yPoints = {y2, y2 - 20, y2 - 20}; //(int) ym, (int) yn};
        
        if (x1 == x2) {
        	g.drawLine(x1, y1, x2, y2);
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
