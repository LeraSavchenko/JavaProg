package testFrame;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public interface Element {

	public void resetIcon();

	public void draw();

	public JLabel getIcon();
	
	public ImageIcon changeIcon();
	
	public Point getLocation();
	
	public void setLocation(Point location);
	
	public void setCode(int number);
	
	public void setCode(String code);
	
	public String getCode();

	public Element copy();

}
