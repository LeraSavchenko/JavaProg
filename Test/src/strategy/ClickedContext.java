package strategy;

import javax.swing.JLabel;

public class ClickedContext {
	private ClickedStrategy strategy;
	private JLabel selected;

	public ClickedContext(JLabel e) {
		this.selected = e;
	}
	
	public void setStrategy(ClickedStrategy str) {
		this.strategy = str;
	}
	
	public void executeStrategy() {
		strategy.execute(selected);
	}

}
