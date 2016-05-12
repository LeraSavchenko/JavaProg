package testFrame;

import java.awt.Point;
import java.util.ArrayList;

public class Memento {
	private ArrayList<Element> elements;
	private ArrayList<Arrow> arrows;
	private ArrayList<Point> loc;
	
	public Memento(ArrayList<Element> e, ArrayList<Arrow> a) {
		elements = new ArrayList<>();
		arrows = new ArrayList<>();
		
		for (Element current : e) {
			this.elements.add(current.copy());
		}
		
		for (Arrow arrow : a) {
            Arrow newArrow = new Arrow();
            for (Element element : e) {
                if (element.getCode().compareTo(arrow.getStart().getCode()) == 0) {
                    newArrow.setStart(element);
                }
                if (arrow.getEnd() != null && element.getCode().compareTo(arrow.getEnd().getCode()) == 0) {
                    newArrow.setEnd(element);
                }
            }
            this.arrows.add(newArrow);
        }
	}
	
	public ArrayList<Element> getElements() {
		ArrayList<Element> newEl = new ArrayList<>();
		for (Element current : elements) {
			newEl.add(current.copy());
		}
		return newEl;
	}
	
    public ArrayList<Arrow> getArrows() {
        ArrayList<Arrow> newArrows = new ArrayList<>();
        for (Arrow current : arrows) {
            newArrows.add(current.copy());
        }
        return newArrows;
    }
    
    public ArrayList<Point> getLoc() {
        ArrayList<Point> newLoc = new ArrayList<>();
        for (Point current : loc) {
            newLoc.add(current);
        }
        return newLoc;
    }

}
