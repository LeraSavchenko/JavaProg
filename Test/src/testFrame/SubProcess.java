package testFrame;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SubProcess implements Element {
	private JLabel icon;
	private JPanel panel;
	private String code;
	private JFormattedTextField text;
	
	public SubProcess(JPanel p) {
		icon = new JLabel(new ImageIcon("subprocess.png"));
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
		icon.setIcon(new ImageIcon("subprocess.png"));
	}

	@Override
	public JLabel getIcon() {
		return icon;
	}

	@Override
	public ImageIcon changeIcon() {
		return new ImageIcon("redSubProcess.png");
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
	public String getCode() {
		return this.code;
	}
	
	@Override
    public void setCode(int number) {
        this.code = "subprocess" + number;
    }
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public void relocateText(String string) {
		panel.remove(icon);
		code = string;
		this.draw();
	}
	
	@Override
	public Element copy() {
        SubProcess clone = new SubProcess(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}

}
