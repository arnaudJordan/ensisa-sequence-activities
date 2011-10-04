package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Actuator {
	private List<Position> positions;
	
	public Actuator() {
		this.positions=new ArrayList<Position>();
	}
	
	public int size() {
		return positions.size();
	}
	public boolean isEmpty() {
		return positions.isEmpty();
	}
	public boolean contains(Object o) {
		return positions.contains(o);
	}
	public Iterator<Position> iterator() {
		return positions.iterator();
	}
	public Position get(int index) {
		return positions.get(index);
	}
	public void addPosition(Position position)
	{
		this.positions.add(position);
	}

	@Override
	public String toString() {
		return "Actuator [positions=" + positions + "]";
	}
	
	
}
