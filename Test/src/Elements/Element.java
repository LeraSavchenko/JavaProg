package Elements;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class Element {

	protected JLabel icon;
	protected JPanel panel;
	protected String code = "";
	
	public abstract void resetIcon();

	public void draw() {
		icon.setText(code);
	    icon.setVerticalTextPosition(SwingConstants.CENTER);
	    icon.setHorizontalTextPosition(SwingConstants.CENTER);
	    
		panel.add(icon);
		panel.validate();
	};

	public JLabel getIcon() {
		return this.icon;
	};
	
	public abstract ImageIcon changeIcon();
	
	public Point getLocation() {
		return new Point(icon.getLocation());
	};
	
	public void setLocation(Point location) {
        icon.setLocation(location);
	};
	
	public abstract void setCode(int number);
	
	public void setCode(String code) {
		this.code = code;
	};
	
	public void relocateText(String string) {
		panel.remove(icon);
		code = string;
		this.draw();
	};
	
	public String getCode() {
		return code;
	};

	public abstract Element copy();

}
