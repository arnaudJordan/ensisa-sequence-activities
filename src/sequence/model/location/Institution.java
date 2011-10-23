package sequence.model.location;

public class Institution {
	private final String institution;

	public Institution(String institution) {
		this.institution = institution;
	}

	public String getInstitution() {
		return institution;
	}

	@Override
	public String toString() {
		return "Institution [institution=" + institution + "]";
	}

	public String toXML() {
		return "<institution>"+institution+"</institution>";
	}
}
