package testFrame;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Data implements Element {
	private JLabel icon;
	private JPanel panel;
	private String code;
	private JFormattedTextField text;
	
	public Data(JPanel p) {
		icon = new JLabel(new ImageIcon("data.png"));
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
		icon.setIcon(new ImageIcon("data.png"));
	}

	@Override
	public JLabel getIcon() {
		return icon;
	}
	
	@Override
	public ImageIcon changeIcon() {
		return new ImageIcon("redData.png");
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
        this.code = "data" + number;
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
        Data clone = new Data(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}

}
