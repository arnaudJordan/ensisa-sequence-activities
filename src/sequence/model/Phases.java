package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sequence.mvc.DefaultModel;

/**
 * The Phases model.
 */
public class Phases extends DefaultModel implements Iterable<Phase> {

	/** The phases. */
	private final List<Phase> phases;

	/**
	 * Instantiates a new phases.
	 */
	public Phases() {
		phases = new ArrayList<Phase>();
	}

	/**
	 * Size.
	 * 
	 * @return the int
	 */
	public int size() {
		return phases.size();
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return phases.isEmpty();
	}

	/**
	 * Index of.
	 * 
	 * @param o
	 *            the object
	 * @return the int
	 */
	public int indexOf(final Object o) {
		return phases.indexOf(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Phase> iterator() {
		return phases.iterator();
	}

	/**
	 * Adds the.
	 * 
	 * @param e
	 *            the phase
	 * @return true, if successful
	 */
	public boolean add(final Phase e) {
		return phases.add(e);
	}

	/**
	 * Gets the.
	 * 
	 * @param index
	 *            the index
	 * @return the phase
	 */
	public Phase get(final int index) {
		return phases.get(index);
	}

	/**
	 * Gets the last phase.
	 * 
	 * @return the last phase
	 */
	public Phase getLastPhase() {
		if (phases.size() > 0)
			return phases.get(phases.size() - 1);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Phases [phase=" + phases + "]";
	}

	/**
	 * Return phases in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		final StringBuilder sb = new StringBuilder("<state id=\"Phase\">"
				+ NEW_LINE);
		for (final Phase p : phases)
			sb.append("\t" + p.toXML() + NEW_LINE);
		sb.append("</state>");
		return sb.toString();
	}
}
