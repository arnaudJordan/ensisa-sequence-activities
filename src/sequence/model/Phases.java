package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Phases {
	private List<Phase> phases;
	
	public Phases()
	{
		this.phases = new ArrayList<Phase>();
	}

	public int size() {
		return phases.size();
	}

	public boolean isEmpty() {
		return phases.isEmpty();
	}

	public Iterator<Phase> iterator() {
		return phases.iterator();
	}

	public boolean add(Phase e) {
		return phases.add(e);
	}

	public Phase get(int index) {
		return phases.get(index);
	}
	public Phase getLastPhase() {
		return phases.get(phases.size()-1);
	}

	@Override
	public String toString() {
		return "Phases [phase=" + phases + "]";
	}

	
	
}
