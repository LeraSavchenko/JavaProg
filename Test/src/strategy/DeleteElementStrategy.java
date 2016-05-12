package strategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import testFrame.Arrow;
import testFrame.Element;
import testFrame.FlowChart;

public class DeleteElementStrategy implements ClickedStrategy {
	private FlowChart fc;
	
	public DeleteElementStrategy(FlowChart f) {
		this.fc = f;
	}

	@Override
	public void execute(JLabel e) {
//		  deleteElement(e);
//		  fc.setFocus();
	}
//	
//	public void deleteElement(JLabel selected) {
//		ArrayList<Integer> deleted = new ArrayList<>();
//		for (Element current : fc.elements) {
//			if (selected != null && current.getCode().compareTo(selected.getText()) == 0) {
//				boolean isDelete = true;
//		        while (isDelete) {
//		        	isDelete = false;
//		            for (Arrow arrow : fc.panel.getArrows()) {
//		            	if (arrow.getStart().equals(current) || arrow.getEnd().equals(current)) {
//		            		fc.panel.getArrows().remove(arrow);
//		                    	isDelete = true;
//		                    	break;
//		                	}
//		              	}
//		          	}
//		        fc.panel.remove(current.getIcon());
//		        deleted.add(fc.elements.indexOf(current));
//		              
//		              fc.saveLocation();
//		              fc.panel.validate();
//		              fc.panel.repaint();
//		              fc.refresh();
//		              fc.saveState();
//				  }
//		  }
//		  for (int i = 0; i < deleted.size(); i++) {
//			  fc.elements.remove(deleted.get(i));
//		  }
//		  fc.selected = null;
//	  }

}
