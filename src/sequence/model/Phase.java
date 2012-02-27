package sequence.model;

import sequence.mvc.DefaultModel;

/**
 * The Phase model.
 */
public class Phase extends DefaultModel {

	/** The date. */
	private final int date;

	/** The name. */
	private String name;

	/** The stop time. */
	private int stopTime = 0;

	/**
	 * Instantiates a new phase.
	 * 
	 * @param date
	 *            the date
	 */
	public Phase(final int date) {
		super();
		this.date = date;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(final String name) {
		this.name = name;
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
		if (!(o instanceof Phase))
			return false;
		return date == (((Phase) o).getDate())
				&& name.equalsIgnoreCase(((Phase) o).getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Phase [date=" + date + ", name=" + name + "]";
	}

	/**
	 * Return phase in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<value time=\"" + date + "\">" + name + "</value>";
	}

	/**
	 * Sets the stop time.
	 *
	 * @param stopTime the new stop time
	 */
	public void setStopTime(final int stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * Gets the stop time.
	 *
	 * @return the stop time
	 */
	public int getStopTime() {
		return stopTime;
	}
}
