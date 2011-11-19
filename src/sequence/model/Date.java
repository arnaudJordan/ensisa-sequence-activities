package sequence.model;

import java.util.Calendar;

public class Date {
	private Calendar date;
	private int startTime;
	private int stopTime;
	private int duration;
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
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
		return "Date [date=" + date + ", startTime=" + startTime
				+ ", stopTime=" + stopTime + ", duration=" + duration + "]";
	}
	
	public String toXML() {
		return "<date>"+date.get(Calendar.YEAR)+"-"+date.get(Calendar.MONTH)+"-"+date.get(Calendar.DAY_OF_MONTH)+"</date>";
	}

}
