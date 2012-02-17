package sequence.model.activity;

/**
 * The Activitytime model.
 */
public class Activitytime {

	/** The start time. */
	private int startTime;

	/** The stop time. */
	private int stopTime;

	/** The duration. */
	private int duration;

	/**
	 * Gets the start time.
	 * 
	 * @return the start time
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 * 
	 * @param startTime
	 *            the new start time
	 */
	public void setStartTime(final int startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the stop time.
	 * 
	 * @return the stop time
	 */
	public int getStopTime() {
		return stopTime;
	}

	/**
	 * Sets the stop time.
	 * 
	 * @param stopTime
	 *            the new stop time
	 */
	public void setStopTime(final int stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * Gets the duration.
	 * 
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 * 
	 * @param duration
	 *            the new duration
	 */
	public void setDuration(final int duration) {
		this.duration = duration;
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
		if (!(o instanceof Activitytime))
			return false;
		return (duration == ((Activitytime) o).duration
				&& startTime == ((Activitytime) o).startTime && stopTime == ((Activitytime) o).stopTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Activitytime [startTime=" + startTime + ", stopTime="
				+ stopTime + ", duration=" + duration + "]";
	}

	/**
	 * Return activitytime in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		return "<activitytime>" + NEW_LINE + "\t" + "<starttime>" + startTime
				+ "</starttime>" + NEW_LINE + "\t" + "<stoptime>" + stopTime
				+ "</stoptime>" + NEW_LINE + "\t" + "<duration>" + duration
				+ "</duration>" + NEW_LINE + "</activitytime>";
	}
}
