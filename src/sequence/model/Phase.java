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
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Phase)) return false;
	    return this.date == (((Phase)o).getDate()) && this.name.equalsIgnoreCase(((Phase) o).getName());
	}
	
	@Override
	public String toString() {
		return "Phase [date=" + date + ", name=" + name + "]";
	}
	
	public String toXML() {
		return "<value time=\""+date+"\">"+name+"</value>";
	}
}
