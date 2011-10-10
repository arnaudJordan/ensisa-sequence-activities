package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsedInstruments {
	private List<Instrument> usedInstruments;
	
	public UsedInstruments() {
		this.usedInstruments=new ArrayList<Instrument>();
	}
	
	public void addInstrument(Instrument instrument)
	{
		usedInstruments.add(instrument);
	}
	public int size() {
		return usedInstruments.size();
	}
	public boolean isEmpty() {
		return usedInstruments.isEmpty();
	}
	public boolean contains(Object o) {
		return usedInstruments.contains(o);
	}
	public Iterator<Instrument> iterator() {
		return usedInstruments.iterator();
	}
	public Instrument get(int index) {
		return usedInstruments.get(index);
	}
	public List<Instrument> getUsedInstruments() {
		return usedInstruments;
	}
	@Override
	public String toString() {
		return "UsedInstruments [usedInstruments=" + usedInstruments + "]";
	}
}
