package ArrowStrategy;

import java.awt.Graphics;

import testFrame.Arrow;

public class ArrowContext {
	private DrawArrowStrategy strategy;
	
	public void setStrategy(DrawArrowStrategy str) {
		this.strategy = str;
	}

	public void drawNiceArrow(Graphics g, Arrow arrow) {
		strategy.drawNiceArrow(g, arrow);
	}
}
