package sequence.model;

public class Phase {
	private final int date;
	private String name;
	public Phase(int date) {
		super();
		this.date = date;
	}
	public int getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	@Override
	public String toString() {
		return "Phase [date=" + date + ", name=" + name + "]";
	}
	
	
}
