package Elements;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Terminator extends Element {
	
	public Terminator(JPanel p) {
		icon = new JLabel(new ImageIcon("terminator.png"));
		this.panel = p;
	}

	
	@Override
	public void resetIcon() {
		icon.setIcon(new ImageIcon("terminator.png"));
	}

	@Override
	public ImageIcon changeIcon() {
		return new ImageIcon("redTerminator.png");
	}
	
	@Override
    public void setCode(int number) {
        this.code = "terminator" + number;
    }
	
	@Override
	public Element copy() {
        Terminator clone = new Terminator(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}
}
