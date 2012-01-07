package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Phases model.
 */
public class Phases implements Iterable<Phase> {
	
	/** The phases. */
	private List<Phase> phases;
	
	/**
	 * Instantiates a new phases.
	 */
	public Phases()
	{
		this.phases = new ArrayList<Phase>();
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
	 * @param o the o
	 * @return the int
	 */
	public int indexOf(Object o) {
		return phases.indexOf(o);
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Phase> iterator() {
		return phases.iterator();
	}

	/**
	 * Adds the.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public boolean add(Phase e) {
		return phases.add(e);
	}

	/**
	 * Gets the.
	 *
	 * @param index the index
	 * @return the phase
	 */
	public Phase get(int index) {
		return phases.get(index);
	}
	
	/**
	 * Gets the last phase.
	 *
	 * @return the last phase
	 */
	public Phase getLastPhase() {
		return phases.get(phases.size()-1);
	}

	/* (non-Javadoc)
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
		StringBuilder sb = new StringBuilder("<state id=\"Phase\">"+NEW_LINE);
		for(Phase p : phases)
			sb.append("\t"+p.toXML()+NEW_LINE);
		sb.append("</state>");
		return sb.toString();
	}
	
}
