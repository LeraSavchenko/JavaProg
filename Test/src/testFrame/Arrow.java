package testFrame;

public class Arrow {
	private Element start, end;

    public Element getStart() {
        return start;
    }

    public void setStart(Element start) {
        this.start = start;
    }

    public Element getEnd() {
        return end;
    }

    public void setEnd(Element end) {
        this.end = end;
    }

    public boolean equals(Arrow arrow) {
        return arrow.getStart().equals(start) && arrow.getEnd().equals(end);
    }

    public boolean counterEdge(Arrow arrow) {
    	if (arrow.getEnd() == null) {
    		return false;
    	}
    	else {
    		return arrow.getEnd().equals(start) && arrow.getStart().equals(end);
    	}
    }

    public Arrow copy() {
        Arrow clone = new Arrow();
        clone.setStart(start.copy());
        clone.setEnd(end.copy());
        return clone;
    }

}
