package sequence.model;

public class Actuator {
	private final String position;

	public Actuator(String position) {
		this.position=position;
	}

	public String getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "Position [position=" + position + "]";
	}
}
