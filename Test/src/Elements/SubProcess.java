package Elements;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SubProcess extends Element {

	public SubProcess(JPanel p) {
		icon = new JLabel(new ImageIcon("subprocess.png"));
		this.panel = p;
	}
	
	@Override
	public void resetIcon() {
		icon.setIcon(new ImageIcon("subprocess.png"));
	}

	@Override
	public ImageIcon changeIcon() {
		return new ImageIcon("redSubProcess.png");
	}
	
	@Override
    public void setCode(int number) {
        this.code = "subprocess" + number;
    }

	@Override
	public Element copy() {
        SubProcess clone = new SubProcess(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}
}
