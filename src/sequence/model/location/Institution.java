package sequence.model.location;

/**
 * The Institution model.
 */
public class Institution {
	
	/** The institution. */
	private final String institution;

	/**
	 * Instantiates a new institution.
	 * 
	 * @param institution
	 *            the institution
	 */
	public Institution(String institution) {
		this.institution = institution;
	}

	/**
	 * Gets the institution.
	 * 
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Institution [institution=" + institution + "]";
	}

	/**
	 * Return institution in XML format.
	 * 
	 * @return the XMLstring
	 */
	public String toXML() {
		return "<institution>"+institution+"</institution>";
	}
}
