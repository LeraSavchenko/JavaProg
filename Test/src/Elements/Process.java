package Elements;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Process extends Element {
	
	public Process(JPanel p) {
		icon = new JLabel(new ImageIcon("process.png"));
		this.panel = p;
	}
	
	@Override
	public void resetIcon() {
		icon.setIcon(new ImageIcon("process.png"));
	}

	@Override
    public void setCode(int number) {
        this.code = "process" + number;
    }
	
	@Override
	public ImageIcon changeIcon() {
		return new ImageIcon("redProcess.png");
	}

	@Override
	public Element copy() {
        Process clone = new Process(panel);
        clone.setCode(this.getCode());
        clone.setLocation(getLocation());
        return clone;
	}
}
