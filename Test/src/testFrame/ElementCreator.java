package testFrame;

import javax.swing.JPanel;

import Elements.Condition;
import Elements.Data;
import Elements.Element;
import Elements.Process;
import Elements.SubProcess;
import Elements.Terminator;

public class ElementCreator implements Creator {

	@Override
	public Element factoryMethod(String name, JPanel p) {
		if (name.equals("terminator")) {
			return new Terminator(p);
		}
		if (name.equals("data")) {
			return new Data(p);
		}
		if (name.equals("process")) {
			return new Process(p);
		}
		if (name.equals("subprocess")) {
			return new SubProcess(p);
		}
		if (name.equals("condition")) {
			return new Condition(p);
		}
		// TODO Auto-generated method stub
		return null;
	}

}
