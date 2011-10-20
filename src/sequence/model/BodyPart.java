package sequence.model;

public class BodyPart {
	private final String bodypart;

	public BodyPart(String bodypart) {
		super();
		this.bodypart = bodypart;
	}

	public String getBodypart() {
		return bodypart;
	}

	@Override
	public String toString() {
		return "BodyPart [bodypart=" + bodypart + "]";
	}
}
