package testFrame;

import java.util.ArrayList;

public class State {
	
	private ArrayList<Memento> states = new ArrayList<>();
	
	public void addState(Memento s, int index) {
		if (index < states.size()) {
		    for (int i = states.size() - 1; i >= index; i--) {
                states.remove(states.get(i));
            }
        }
        states.add(s);
    }
	
	public Memento getState(int index) {
        if (index < 0 || index >= states.size()) {
            return null;
        }
        return states.get(index);
	}

	public int getAmountOfStates() {
		return states.size();
	}
}
