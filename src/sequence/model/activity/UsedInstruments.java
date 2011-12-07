package sequence.model.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UsedInstruments implements Iterable<Instrument> {
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
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof UsedInstruments)) return false;
		return this.usedInstruments.equals(((UsedInstruments)o).usedInstruments);
	}	
	@Override
	public String toString() {
		return usedInstruments.toString();
	}
	
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<usedInstruments>"+NEW_LINE);
		for(Instrument instrument : usedInstruments)
			sb.append("\t"+instrument.toXML()+NEW_LINE);
		sb.append("</usedInstruments>");
		return sb.toString();
	}
}
