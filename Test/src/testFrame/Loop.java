package testFrame;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Loop implements Element {
	private JLabel icon;
	private JPanel panel;
	private String code;
	private JFormattedTextField text;
	private int flag;
	
	public Loop(JPanel p, int flag) {
		if (flag % 2 == 0) {
			this.flag = flag;
			icon = new JLabel(new ImageIcon("loop.png"));
		} else {
			this.flag = flag;
			icon = new JLabel(new ImageIcon("loopInv.png"));
		}
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
		if (this.flag % 2 == 0) {
			icon.setIcon(new ImageIcon("loop.png"));
		} else {
			icon.setIcon(new ImageIcon("loopInv.png"));
		}
	}

	@Override
	public JLabel getIcon() {
		return icon;
	}

	@Override
	public ImageIcon changeIcon() {
		if (this.flag % 2 == 0) {
			return new ImageIcon("redLoop.png");
		} else {
			return new ImageIcon("redLoopInv.png");
		}
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
        this.code = "loop" + number;
    }
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public void relocateText(String string) {
		panel.remove(icon);
		code = string;
		this.draw();
	}
	
	@Override
	public Element copy() {
        Loop clone = new Loop(panel, this.flag);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}

}