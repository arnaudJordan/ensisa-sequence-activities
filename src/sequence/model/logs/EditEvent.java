package sequence.model.logs;

/**
 * The EditEvent model.
 */
public class EditEvent {
	
	/** The time. */
	private final int time;
	
	/** The event. */
	private String event;
	
	/**
	 * Instantiates a new edits the event.
	 * 
	 * @param time
	 *            the time
	 */
	public EditEvent(int time)
	{
		this.time=time;
	}
	
	/**
	 * Sets the event.
	 * 
	 * @param event
	 *            the new event
	 */
	public void setEvent(String event)
	{
		this.event = event;
	}
	
	/**
	 * Gets the event.
	 * 
	 * @return the event
	 */
	public String getEvent()
	{
		return this.event;
	}
	
	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public int getTime()
	{
		return time;
	}
	
	/**
	 * Return EditEvent in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML()
	{
		return "<edit-event time=\""+time+"\">"+event+"</edit-event>";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EditEvent [time=" + time + ", event=" + event + "]";
	}
	
	
}
