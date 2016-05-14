package Elements;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Condition extends Element {
	
	public Condition(JPanel p) {
		icon = new JLabel(new ImageIcon("condition.png"));
		this.panel = p;
	}
	
	@Override
	public void resetIcon() {
		icon.setIcon(new ImageIcon("condition.png"));
	}
	
	@Override
	public ImageIcon changeIcon() {
		return new ImageIcon("redCondition.png");
	}
	
	@Override
    public void setCode(int number) {
        this.code = "condition" + number;
    }
	
	@Override
	public Element copy() {
        Condition clone = new Condition(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}
}