package sequence.model;

public class Position {
	private final String position;

	public Position(String position) {
		super();
		this.position = position;
	}

	public String getPosition() {
		return position;
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Position)) return false;
	    return this.position.equalsIgnoreCase(((Position)o).getPosition());
	}
	
	@Override
	public String toString() {
		return position;
	}
	
	public String toXML() {
		return "<position>"+position+"</position>";
	}
}
