package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Discipline {
	private List<Participant> participants;
	
	public Discipline()
	{
		this.participants = new ArrayList<Participant>();
	}

	public int size() {
		return participants.size();
	}

	public boolean isEmpty() {
		return participants.isEmpty();
	}

	public boolean contains(Object o) {
		return participants.contains(o);
	}

	public Iterator<Participant> iterator() {
		return participants.iterator();
	}

	public boolean addParticipant(Participant e) {
		return participants.add(e);
	}
	
	public Participant getLastParticipant()
	{
		return participants.get(participants.size()-1);
	}

	@Override
	public String toString() {
		return "Discipline [participants=" + participants + "]";
	}
	
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<discipline>"+NEW_LINE);
		for(Participant p : participants)
			sb.append(NEW_LINE + p.toXML() + NEW_LINE);
		sb.append("</discipline>");
		return sb.toString();
	}
}
