package sequence.model;

/**
 * The Name model.
 */
public class Name {

	/** The name. */
	private final String name;

	/**
	 * Instantiates a new name.
	 * 
	 * @param name
	 *            the name
	 */
	public Name(final String name) {
		this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Name [name=" + name + "]";
	}

	/**
	 * Return name in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<name>" + name + "</name>";
	}
}
