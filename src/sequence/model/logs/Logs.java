package sequence.model.logs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Logs model.
 */
public class Logs {

	/** The edit events. */
	private final List<EditEvent> editEvents;

	/**
	 * Instantiates a new logs.
	 */
	public Logs() {
		editEvents = new ArrayList<EditEvent>();
	}

	/**
	 * Size.
	 * 
	 * @return the int
	 */
	public int size() {
		return editEvents.size();
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return editEvents.isEmpty();
	}

	/**
	 * Contains.
	 * 
	 * @param o
	 *            the o
	 * @return true, if successful
	 */
	public boolean contains(final Object o) {
		return editEvents.contains(o);
	}

	/**
	 * Iterator.
	 * 
	 * @return the iterator
	 */
	public Iterator<EditEvent> iterator() {
		return editEvents.iterator();
	}

	/**
	 * Adds the.
	 * 
	 * @param e
	 *            the e
	 * @return true, if successful
	 */
	public boolean add(final EditEvent e) {
		return editEvents.add(e);
	}

	/**
	 * Gets the last edit event.
	 * 
	 * @return the last edit event
	 */
	public EditEvent getLastEditEvent() {
		return editEvents.get(editEvents.size() - 1);
	}

	/**
	 * Return Logs in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		final StringBuilder sb = new StringBuilder("<sw-logs>" + NEW_LINE);
		for (final EditEvent editEvent : editEvents)
			sb.append("\t" + editEvent.toXML() + NEW_LINE);
		sb.append("</sw-logs>");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Logs [editEvents=" + editEvents + "]";
	}
}
