package sequence.model.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The UsedInstruments model.
 */
public class UsedInstruments implements Iterable<Instrument> {

	/** The used instruments. */
	private final List<Instrument> usedInstruments;

	/**
	 * Instantiates a new used instruments.
	 */
	public UsedInstruments() {
		usedInstruments = new ArrayList<Instrument>();
	}

	/**
	 * Adds the instrument.
	 * 
	 * @param instrument
	 *            the instrument
	 */
	public void addInstrument(final Instrument instrument) {
		usedInstruments.add(instrument);
	}

	/**
	 * Size.
	 * 
	 * @return the int
	 */
	public int size() {
		return usedInstruments.size();
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return usedInstruments.isEmpty();
	}

	/**
	 * Contains.
	 * 
	 * @param o
	 *            the o
	 * @return true, if successful
	 */
	public boolean contains(final Object o) {
		return usedInstruments.contains(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Instrument> iterator() {
		return usedInstruments.iterator();
	}

	/**
	 * Gets the.
	 * 
	 * @param index
	 *            the index
	 * @return the instrument
	 */
	public Instrument get(final int index) {
		return usedInstruments.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UsedInstruments))
			return false;
		return usedInstruments.equals(((UsedInstruments) o).usedInstruments);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return usedInstruments.toString();
	}

	/**
	 * Return usedInstruments in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		final StringBuilder sb = new StringBuilder("<usedInstruments>"
				+ NEW_LINE);
		for (final Instrument instrument : usedInstruments)
			sb.append("\t" + instrument.toXML() + NEW_LINE);
		sb.append("</usedInstruments>");
		return sb.toString();
	}
}
