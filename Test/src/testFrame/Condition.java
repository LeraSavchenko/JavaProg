package testFrame;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Condition implements Element {
	private JLabel icon;
	private JPanel panel;
	private String code;
	private JFormattedTextField text;
	
	public Condition(JPanel p) {
		icon = new JLabel(new ImageIcon("condition.png"));
		text = new JFormattedTextField();
		text.setLocation(icon.getLocation());
		text.setColumns(4);
		this.panel = p;
	}

	@Override
	public void draw() {
        icon.setText(this.code);
        icon.setVerticalTextPosition(SwingConstants.CENTER);
        icon.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(icon);
		panel.validate();
	}
	
	@Override
	public void resetIcon() {
		icon.setIcon(new ImageIcon("condition.png"));
	}

	@Override
	public JLabel getIcon() {
		return icon;
	}
	
	@Override
	public ImageIcon changeIcon() {
		return new ImageIcon("redCondition.png");
	}
	
	@Override
	public Point getLocation() {
		return new Point(icon.getLocation());
	}
	
	@Override
    public void setLocation(Point location) {
        icon.setLocation(location);
    }
	
	@Override
    public void setCode(int number) {
        this.code = "condition" + number;
    }
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public Element copy() {
        Condition clone = new Condition(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}

}