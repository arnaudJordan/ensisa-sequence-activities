package sequence.model;

import java.util.Calendar;

/**
 * The Date model.
 */
public class Date {
	
	/** The date. */
	private Calendar date;
	
	/** The start time. */
	private int startTime;
	
	/** The stop time. */
	private int stopTime;
	
	/** The duration. */
	private int duration;
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}
	
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
	 * @param startTime the new start time
	 */
	public void setStartTime(int startTime) {
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
	 * @param stopTime the new stop time
	 */
	public void setStopTime(int stopTime) {
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
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Date [date=" + date + ", startTime=" + startTime
				+ ", stopTime=" + stopTime + ", duration=" + duration + "]";
	}
	
	/**
	 * Return date in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<rec_date>"+NEW_LINE);
		if(date!=null)
			sb.append("\t<date>"+date.get(Calendar.YEAR)+"-"+date.get(Calendar.MONTH)+"-"+date.get(Calendar.DAY_OF_MONTH)+"</date>"+NEW_LINE);
		if(startTime!=0)
			sb.append("\t<starttime>"+startTime+"</starttime>"+NEW_LINE);
		if(stopTime!=0)
			sb.append("\t<stoptime>"+stopTime+"</stoptime>"+NEW_LINE);
		if(duration!=0)
			sb.append("\t<duration>"+duration+"</duration>"+NEW_LINE);
		sb.append("</rec_date>"+NEW_LINE);
		return sb.toString();
	}

}
