package sequence.model.activity;

public class Activitytime {
	private int startTime;
	private int stopTime;
	private int duration;
	
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public int getStopTime() {
		return stopTime;
	}
	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Activitytime)) return false;
	    return (this.duration == ((Activitytime)o).duration && this.startTime == ((Activitytime)o).startTime && this.stopTime == ((Activitytime)o).stopTime);
	}
	
	@Override
	public String toString() {
		return "Activitytime [startTime=" + startTime + ", stopTime="
				+ stopTime + ", duration=" + duration + "]";
	}
	
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		return "<activitytime>"+NEW_LINE+"\t" +
				"<starttime>"+startTime+"</starttime>"+NEW_LINE+"\t" +
				"<stoptime>"+stopTime+"</stoptime>"+NEW_LINE+"\t" +
				"<duration>"+duration+"</duration>"+NEW_LINE +
				"</activitytime>";
	}
}
