package sequence.model;

/**
 * The Position model.
 */
public class Position {

	/** The position. */
	private final String position;

	/**
	 * Instantiates a new position.
	 * 
	 * @param position
	 *            the position
	 */
	public Position(final String position) {
		super();
		this.position = position;
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position
	 */
	public String getPosition() {
		return position;
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
		if (!(o instanceof Position))
			return false;
		return position.equalsIgnoreCase(((Position) o).getPosition());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return position;
	}

	/**
	 * Return position in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<position>" + position + "</position>";
	}
}
