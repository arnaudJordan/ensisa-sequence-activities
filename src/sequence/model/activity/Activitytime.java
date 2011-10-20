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
	
	@Override
	public String toString() {
		return "Activitytime [startTime=" + startTime + ", stopTime="
				+ stopTime + ", duration=" + duration + "]";
	}
}
