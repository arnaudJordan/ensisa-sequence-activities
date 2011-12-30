package sequence.model.activity;

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
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof BodyPart)) return false;
	    return this.bodypart.equalsIgnoreCase(((BodyPart)o).getBodypart());
	}
	
	@Override
	public String toString() {
		return bodypart;
	}
	
	public String toXML() {
		return "<bodyPart>"+bodypart+"</bodyPart>";
	}
}
