package sequence.model.logs;

public class EditEvent {
	private final int time;
	private String event;
	
	public EditEvent(int time)
	{
		this.time=time;
	}
	
	public void setEvent(String event)
	{
		this.event = event;
	}
	public String getEvent()
	{
		return this.event;
	}
	public int getTime()
	{
		return time;
	}
	
	public String toXML()
	{
		return "<edit-event time=\""+time+"\">"+event+"</edit-event>";
	}
	
	@Override
	public String toString() {
		return "EditEvent [time=" + time + ", event=" + event + "]";
	}
	
	
}
