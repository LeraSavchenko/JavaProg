package testFrame;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Terminator implements Element {
	
	private JLabel icon;
	private JPanel panel;
	private String code = "";
	
	public Terminator(JPanel p) {
		icon = new JLabel(new ImageIcon("terminator.png"));
		this.panel = p;
	}
	
	@Override
	public void draw() {
        icon.setText(code);
        icon.setVerticalTextPosition(SwingConstants.CENTER);
        icon.setHorizontalTextPosition(SwingConstants.CENTER);
        
		panel.add(icon);
		panel.validate();
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
	public JLabel getIcon() {
		return icon;
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
        this.code = "terminator" + number;
    }
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public Element copy() {
        Terminator clone = new Terminator(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}

	@Override
	public String getCode() {
		return this.code;
	}

}
