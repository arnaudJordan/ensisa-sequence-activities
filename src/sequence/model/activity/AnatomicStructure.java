package sequence.model.activity;

/**
 * The AnatomicStructure model.
 */
public class AnatomicStructure {
	
	/** The anatomic structure. */
	private final String anatomicStructure;

	/**
	 * Instantiates a new anatomic structure.
	 * 
	 * @param anatomicStructure
	 *            the anatomic structure
	 */
	public AnatomicStructure(String anatomicStructure) {
		this.anatomicStructure=anatomicStructure;
	}

	/**
	 * Gets the anatomic structure.
	 * 
	 * @return the anatomic structure
	 */
	public String getAnatomicStructure() {
		return anatomicStructure;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof AnatomicStructure)) return false;
	    return this.anatomicStructure.equalsIgnoreCase(((AnatomicStructure)o).getAnatomicStructure());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return anatomicStructure;
	}
	
	/**
	 * Return anatomicStructure in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<anatomicStructure>"+anatomicStructure+"</anatomicStructure>";
	}
}
