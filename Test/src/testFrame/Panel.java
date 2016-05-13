package testFrame;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import ArrowStrategy.ArrowContext;
import ArrowStrategy.DrawDown;
import ArrowStrategy.DrawUp;

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

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Arrow arrow : arrows) {
        	if (arrow.getStart() != null && arrow.getEnd() != null) {
	            drawArrowLine(g, arrow);
        	}
        }
    }
    
    private void drawArrowLine(Graphics g, Arrow arrow/**int x1, int y1, int x2, int y2**/) {
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
    	
    		ArrowContext context = new ArrowContext();
    		if (arrow.getStart().getIcon().getY() < arrow.getEnd().getIcon().getY()) {
    			context.setStrategy(new DrawDown());
    			context.drawNiceArrow(g, arrow);
    		}
    		else {
    			context.setStrategy(new DrawUp());
    			context.drawNiceArrow(g, arrow);
    		}
    }

}
