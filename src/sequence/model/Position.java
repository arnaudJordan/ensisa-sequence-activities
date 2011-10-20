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
	public String toString() {
		return "Position [position=" + position + "]";
	}
}
