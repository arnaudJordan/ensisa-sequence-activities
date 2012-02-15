package sequence.model.activity;

/**
 * The BodyPart model.
 */
public class BodyPart {

	/** The bodypart. */
	private final String bodypart;

	/**
	 * Instantiates a new body part.
	 * 
	 * @param bodypart
	 *            the bodypart
	 */
	public BodyPart(final String bodypart) {
		super();
		this.bodypart = bodypart;
	}

	/**
	 * Gets the bodypart.
	 * 
	 * @return the bodypart
	 */
	public String getBodypart() {
		return bodypart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BodyPart))
			return false;
		return bodypart.equalsIgnoreCase(((BodyPart) o).getBodypart());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return bodypart;
	}

	/**
	 * Return bodyPart in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<bodyPart>" + bodypart + "</bodyPart>";
	}
}
