package sequence.model.location;

/**
 * The Operatingtheatre model.
 */
public class Operatingtheatre {
	
	/** The operatingtheatre. */
	private final String operatingtheatre;

	/**
	 * Instantiates a new operatingtheatre.
	 * 
	 * @param operatingtheatre
	 *            the operatingtheatre
	 */
	public Operatingtheatre(String operatingtheatre) {
		this.operatingtheatre = operatingtheatre;
	}
	
	/**
	 * Gets the operatingtheatre.
	 * 
	 * @return the operatingtheatre
	 */
	public String getOperatingtheatre() {
		return operatingtheatre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Operatingtheatre [operatingtheatre=" + operatingtheatre + "]";
	}

	/**
	 * Return operating in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<operatingtheatre>"+operatingtheatre+"</operatingtheatre>";
	}
}
