package testFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

import java.util.ArrayList;

public class FlowChart extends JFrame{
	  private Panel panel = new Panel();
	  private ArrayList<Element> elements = new ArrayList<>();
	  private ArrayList<Point> locations = new ArrayList<>();
	  
	  private State saving = new State();
	  
	  private int currentState = -1;
	  private Arrow arrow;
	  private boolean hasStart = false;
	  private String selected = "";
	  
	  private JButton newDiagram = new JButton("New diagram");
	  private JButton terminator = new JButton("Terminator");
	  private JButton data = new JButton("Data");
	  private JButton process = new JButton("Process");
	  private JButton subProcess = new JButton("Subprocess");
	  private JButton condition = new JButton("Condition");
	  private JButton loop = new JButton("Loop");
	  private JTextField input = new JTextField();
	  
	  //TODO PATTERNS
	  //mediator for selected to delete
	  private JButton delete = new JButton("Delete");
	  private JButton text = new JButton("Text");
	  
	  private JLabel component = new JLabel();
	  
	  //facade or strategy for mouseclick-selected
	  //factory method(PROTOTYPE!!) ? for creating Element
	  //composite for multiple selected
	  //template method for Element classes
	  //decorator for arrow(don't fill the "no" arrow)
	  //some pattern for condition arrows
	  public FlowChart() throws HeadlessException {
		  super("Paint Test");
		  pack();
		  setLayout(null);
		  setSize(1200, 600);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      setFrame();
	      saveState();
	      setFocus();
	      
	      setResizable(false);
	      setVisible(true);
	  }
	  
	  private void setFrame() {
		  panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	      panel.setBackground(Color.WHITE);
	      panel.addKeyListener(new KeyPressed());
	      input.setVisible(false);
	      
		  terminator.addActionListener(new AddTerminator());
		  process.addActionListener(new AddProcess());
		  subProcess.addActionListener(new AddSubProcess());
		  data.addActionListener(new AddData());
		  condition.addActionListener(new AddCondition());
		  loop.addActionListener(new AddLoop());
		  delete.addActionListener(new DeleteElement()); 
		  text.addActionListener(new EditText());
		  newDiagram.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                clearAll();
	                saveState();
	                setFocus();
	            }
	        });

	      panel.setSize(1000, 520);
		  newDiagram.setSize(120, 20);
		  terminator.setSize(120, 20);
		  data.setSize(120, 20);
		  process.setSize(120, 20);
		  subProcess.setSize(120, 20);
		  condition.setSize(120, 20);
		  loop.setSize(120, 20);
		  delete.setSize(120, 20);
		  text.setSize(120, 20);
		  
		  panel.setLocation(160, 20);
	      newDiagram.setLocation(20, 20);
		  terminator.setLocation(20, 60);
		  data.setLocation(20, 100);
		  process.setLocation(20, 140);
		  subProcess.setLocation(20, 180);
		  condition.setLocation(20, 220);
		  loop.setLocation(20, 260);
		  text.setLocation(20, 300);
		  delete.setLocation(20, 340);

		  
	      add(panel);
	      add(newDiagram);
	      add(terminator);
	      add(data);
	      add(process);
	      add(subProcess);
	      add(condition);
	      add(loop);
	      add(delete);
	      add(text);
	  }
	  
	  private void setFocus() {
	      panel.setFocusable(true);
	      panel.requestFocusInWindow();
	  }
	  
	  private void saveLocation() {
	      locations = new ArrayList<>();
	      if (elements.size() > 0) {
	          for (Element current : elements) {
	              locations.add(current.getLocation());
	          }
	      }
	  }
	  
	  private void refresh() {
	      int counter = 0;
	      for (Point current : locations) {
	          elements.get(counter).setLocation(current);
	          counter++;
	      }
	  }
	  
	  private void saveState() {
		  Memento state = new Memento(elements, panel.getArrows());
		  currentState++;
		  saving.addState(state, currentState);
	  }
	  
	  private boolean updateState(int index) {
		  clearAll();
		  
		  Memento s = saving.getState(index);
		  if (saving == null) {
			  return false;
		  }
		  
		  elements = s.getElements();
	      for (Element current : elements) {
	          addElement(current);
	      }
	      
	      ArrayList<Arrow> arrows = s.getArrows();
	      for (Arrow arrow : arrows) {
	          Arrow newArrow = new Arrow();
	          for (Element element : this.elements) {
	              if (element.getCode().compareTo(arrow.getStart().getCode()) == 0) {
	                  newArrow.setStart(element);
	              }
	              if (element.getCode().compareTo(arrow.getEnd().getCode()) == 0) {
	                  newArrow.setEnd(element);
	              }
	          }
	          panel.addArrow(newArrow);
	      }
	       
	      saveLocation();
          panel.validate(); //WOOOOOT ONOOOO!!
	      refresh();
	      return true;
	  }
	  
	  private void clearAll() {
		  selected = null;
		  hasStart = false;
		  
		  locations.clear();
		  elements.clear();

		  panel.getArrows().clear();
		  panel.removeAll();
		  panel.validate();
		  panel.repaint();
	  }
	  
	  public void addElement(Element e) {

	      ClickElement click = new ClickElement();
	      e.getIcon().addMouseListener(click);
	      e.getIcon().addMouseMotionListener(click);
	      
		  saveLocation();
	      e.draw();
	      
	      DragListener drag = new DragListener();
	      e.getIcon().addMouseListener(drag);
	      e.getIcon().addMouseMotionListener(drag);
	      
	      refresh();
	  }
	  
	  public void deleteElement() {
		  ArrayList<Integer> deleted = new ArrayList<>();
		  for (Element current : elements) {
				  if (current.getCode().compareTo(selected) == 0) {
					  boolean isDelete = true;
		                while (isDelete) {
		                    isDelete = false;
		                    for (Arrow arrow : panel.getArrows()) {
		                        if (arrow.getStart().equals(current) || arrow.getEnd().equals(current)) {
		                            panel.getArrows().remove(arrow);
		                            isDelete = true;
		                            break;
		                        }
		                    }
		                }
		              panel.remove(current.getIcon());
		              deleted.add(elements.indexOf(current));
		              
		              saveLocation();
		              panel.validate();
		              panel.repaint();
		              refresh();
		              selected = "";
		              hasStart = false;
		              saveState();
				  }
		  }
		  for (int i = 0; i < deleted.size(); i++) {
			  elements.remove(deleted.get(i));
		  }
	  }
	  
	  public void drawArrow(JLabel component) {
		  if (!hasStart) {
              arrow = new Arrow();
              for (Element current : elements) {
                  if (current.getCode().compareTo(component.getText()) == 0) {
                      arrow.setStart(current);
                      selected = current.getCode();
                      component.setIcon(current.changeIcon());
                  }
              }
              hasStart = true;
              
              panel.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mouseClicked(MouseEvent e) {
                      hasStart = false;
                      arrow.getStart().resetIcon();
                      selected = "";
                  }
              });
              
          } else {
              if (arrow.getStart() == null) {
                  hasStart = false;
                  return;
              }
              for (Element current : elements) {
                  if (current.getCode().compareTo(component.getText()) == 0) {
                      if (component.getX() == arrow.getStart().getLocation().getX() 
                      		&& component.getY() == arrow.getStart().getLocation().getY()) {
                          return;
                      }
                      arrow.setEnd(current);
                  }
              }
              hasStart = false;
              arrow.getStart().resetIcon();
              selected = "";
              panel.addArrow(arrow);
              
              saveLocation();
              panel.validate();
              refresh();
              saveState();
              setFocus();
          }
	  }
	  
	  public void editText() {

		  if (selected != null) {
			  input.setVisible(true);
			  input.setText(selected);

			  for(Element current : elements) {
				  if (current.getCode() == selected) {
					  input.setBounds(current.getIcon().getX() +120, current.getIcon().getY() + 20, 100, 20);
					 
				  }
			  }
              
			  input.addKeyListener(new EnterPressed());  
			  panel.add(input);
			  //panel.validate();
              panel.repaint();
              refresh();
			  setFocus();
		  }
	  }
	  
	  public void setText() {
		  
		  for (Element current : elements) {
			  current.resetIcon();
			  if (current.getCode() == selected) {
				 current.relocateText(input.getText());
			  }
		  }

		  panel.remove(input);
		  input.setVisible(false);

		  selected = "";

		  panel.validate();
          refresh();
//          panel.repaint(); makes no difference
		  setFocus();
	  }
	  
	  public void addTerminator(int n) {
		  for (Element current : elements) {
			  current.resetIcon();
		  }
		  
		  Element newTerm = new Terminator(panel);
		  newTerm.setCode(n);
		  addElement(newTerm);
		  elements.add(newTerm);
		  saveState();
	  }
	  
	  public void addProcess(int n) {
		  for (Element current : elements) {
			  current.resetIcon();
		  }
		  
		  Element newProc = new Process(panel);
		  newProc.setCode(n);
		  addElement(newProc);
		  elements.add(newProc);
		  saveState();
	  }
	  
	  public void addSubProcess(int n) {
		  for (Element current : elements) {
			  current.resetIcon();
		  }
		  
		  Element newSub = new SubProcess(panel);
		  newSub.setCode(n);
		  addElement(newSub);
		  elements.add(newSub);
		  saveState();
	  }
	  
	  public void addData(int n) {
		  for (Element current : elements) {
			  current.resetIcon();
		  }
		  
		  Element newData = new Data(panel);
		  newData.setCode(n);
		  addElement(newData);
		  elements.add(newData);
		  saveState();
	  }
	  
	  public void addCondition(int n) {
		  for (Element current : elements) {
			  current.resetIcon();
		  }
		  
		  Element newCond = new Condition(panel);
		  newCond.setCode(n);
		  addElement(newCond);
		  elements.add(newCond);
		  saveState();
	  }
	  
	  public void addLoop(int flag, int n) {
		  for (Element current : elements) {
			  current.resetIcon();
		  }
		  
		  Loop newLoop = new Loop(panel, flag);
		  newLoop.setCode(n);
		  addElement(newLoop);
		  elements.add(newLoop);
		  saveState();
	  }
	  
	  public class AddTerminator implements ActionListener {
		  int amount = 1;
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  addTerminator(amount);
			  setFocus();
			  amount++;
		  }
	  }
	  
	  public class AddProcess implements ActionListener {
		  int amount = 1;
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  addProcess(amount);
			  setFocus();
			  amount++;
		  }
	  }
	  
	  public class AddSubProcess implements ActionListener {
		  int amount = 1;
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  addSubProcess(amount);
			  setFocus();
			  amount++;
		  }
	  }
	  
	  public class AddData implements ActionListener {
		  int amount = 1;
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  addData(amount);
			  setFocus();
			  amount++;
		  }
	  }
	  
	  public class AddCondition implements ActionListener {
		  int amount = 1;
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  addCondition(amount);
			  setFocus();
			  amount++;
		  }
	  }
	  
	  public class AddLoop implements ActionListener {
		  int amount = 1;
		  int flag = 0;
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  addLoop(flag, amount);
			  setFocus();
			  flag++;
			  amount++;
		  }
	  }
	  
	  public class ClickElement extends MouseInputAdapter {
		  public void mouseClicked(MouseEvent e) {
			  
			  JLabel clicked = (JLabel) e.getComponent();
			  component = (JLabel) e.getComponent();
			  for (Element current : elements) {
				  current.resetIcon();
			  }
			    
			  drawArrow(clicked);
			  setFocus();
		  } 
	  }
	  
	  public class DeleteElement implements ActionListener {
		  
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  deleteElement();
			  setFocus();
		  } 
	  }
	  
	  public class EditText implements ActionListener {
		  
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  editText();
			  setFocus();
		  }
	  }
	  
	  public class DragListener extends MouseInputAdapter {
	      private Point location;
	      private MouseEvent pressed;
	      private int oldX = 0;
	      private int oldY = 0;

	      @Override
	      public void mousePressed(MouseEvent e) {
	          pressed = e;
	          JLabel component = (JLabel) e.getComponent();
	          for (Element current : elements) {
	        	  if (current.getCode().compareTo(component.getText()) == 0) {
	        		  component.setIcon(current.changeIcon());
	        	  }
	          }
	          oldX = e.getComponent().getX();
	          oldY = e.getComponent().getY();
	      }

	      @Override
	      public void mouseDragged(MouseEvent e) {
	    	  Component component = e.getComponent();
	          location = component.getLocation(location);
	          int x = location.x - pressed.getX() + e.getX();
	          int y = location.y - pressed.getY() + e.getY();
	          if (x + component.getWidth() > panel.getWidth() + panel.getX() || x < 0) {
	              return;
	          }
	          if (y + component.getHeight() > panel.getHeight() || y < 0) {
	              return;
	          }
	          component.setLocation(x, y);
	          panel.repaint();
	      }

	      @Override
	      public void mouseReleased(MouseEvent e) {
	          for (Element current : elements) {
	        	  current.resetIcon();
	          }
	          if (oldX != e.getComponent().getX() && oldY != e.getComponent().getY()) {
	        	  saveLocation();
	              saveState();
	          }
	      }
	  }
	  
	  private class EnterPressed extends KeyAdapter {
		  @Override
		  public void keyReleased(KeyEvent e) {
			  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				  setText();
				  saveState();
			  }
		  }
	  }
	  
	  private class KeyPressed extends KeyAdapter {
	      @Override
	      public void keyReleased(KeyEvent e) {
	            //back
	          if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
	              ctrlZ();
	          }
	            //forward
	          if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
	              ctrlY();
	          }
	            //clear panel
	          if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X) {
	              clearAll();
	              saveState();
	          }
	      }
	  }
	  
	  private void ctrlY() {
	      if (updateState(currentState + 1)) {
	          currentState++;
	      }
	      updateState(currentState);
	  }

	  private void ctrlZ() {
	      if (updateState(currentState - 1)) {
	    	  currentState--;
	      }
	      updateState(currentState);
	  }

}
