package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Actions {
	private List<Action> actions;
	
	public Actions() {
		this.actions=new ArrayList<Action>();
	}
	
	public int size() {
		return actions.size();
	}
	public boolean isEmpty() {
		return actions.isEmpty();
	}
	public boolean contains(Object o) {
		return actions.contains(o);
	}
	public Iterator<Action> iterator() {
		return actions.iterator();
	}
	public Action get(int index) {
		return actions.get(index);
	}
	public void addAction(Action Action)
	{
		this.actions.add(Action);
	}
	
	@Override
	public String toString() {
		return "Actions [actions=" + actions + "]";
	}
	
	
}
