package testFrame;

import javax.swing.JPanel;

import Elements.Element;

public interface Creator {
	
	public Element factoryMethod(String name, JPanel panel);

}
