package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Discipline model.
 */
public class Discipline {

	/** The participants. */
	private final List<Participant> participants;

	/**
	 * Instantiates a new discipline.
	 */
	public Discipline() {
		participants = new ArrayList<Participant>();
	}

	/**
	 * Size.
	 * 
	 * @return the int
	 */
	public int size() {
		return participants.size();
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return participants.isEmpty();
	}

	/**
	 * Contains.
	 * 
	 * @param o
	 *            the o
	 * @return true, if successful
	 */
	public boolean contains(final Object o) {
		return participants.contains(o);
	}

	/**
	 * Iterator.
	 * 
	 * @return the iterator
	 */
	public Iterator<Participant> iterator() {
		return participants.iterator();
	}

	/**
	 * Adds the participant.
	 * 
	 * @param e
	 *            the e
	 * @return true, if successful
	 */
	public boolean addParticipant(final Participant e) {
		return participants.add(e);
	}

	/**
	 * Gets the last participant.
	 * 
	 * @return the last participant
	 */
	public Participant getLastParticipant() {
		return participants.get(participants.size() - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Discipline [participants=" + participants + "]";
	}

	/**
	 * Return discipline in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		final StringBuilder sb = new StringBuilder("<discipline>" + NEW_LINE);
		for (final Participant p : participants)
			sb.append(NEW_LINE + p.toXML() + NEW_LINE);
		sb.append("</discipline>");
		return sb.toString();
	}
}
