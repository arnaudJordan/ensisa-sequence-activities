package sequence.model.logs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Logs {
	private List<EditEvent> editEvents;
	
	public Logs()
	{
		this.editEvents = new ArrayList<EditEvent>();
	}

	public int size() {
		return editEvents.size();
	}

	public boolean isEmpty() {
		return editEvents.isEmpty();
	}

	public boolean contains(Object o) {
		return editEvents.contains(o);
	}

	public Iterator<EditEvent> iterator() {
		return editEvents.iterator();
	}

	public boolean add(EditEvent e) {
		return editEvents.add(e);
	}
	
	public EditEvent getLastEditEvent()
	{
		return editEvents.get(editEvents.size()-1);
	}

	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<sw-logs>"+NEW_LINE);
		for(EditEvent editEvent : editEvents)
			sb.append("\t"+editEvent.toXML()+NEW_LINE);
		sb.append("</sw-logs>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Logs [editEvents=" + editEvents + "]";
	}
}
