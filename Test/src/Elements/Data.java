package Elements;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Data extends Element {

	public Data(JPanel p) {
		icon = new JLabel(new ImageIcon("data.png"));
		this.panel = p;
	}
	
	@Override
	public void resetIcon() {
		icon.setIcon(new ImageIcon("data.png"));
	}
	
	@Override
	public ImageIcon changeIcon() {
		return new ImageIcon("redData.png");
	}
	
	@Override
    public void setCode(int number) {
        this.code = "data" + number;
    }
	
	@Override
	public Element copy() {
        Data clone = new Data(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}
}
