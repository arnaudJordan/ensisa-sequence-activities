package sequence.model.location;

/**
 * The Country model.
 */
public class Country {

	/** The country. */
	private final String country;

	/**
	 * Instantiates a new country.
	 * 
	 * @param country
	 *            the country
	 */
	public Country(final String country) {
		this.country = country;
	}

	/**
	 * Gets the country.
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Country [country=" + country + "]";
	}

	/**
	 * Return country in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<country>" + country + "</country>";
	}
}
