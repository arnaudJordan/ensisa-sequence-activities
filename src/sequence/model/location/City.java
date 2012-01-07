package sequence.model.location;

/**
 * The City model.
 */
public class City {
	
	/** The city. */
	private final String city;

	/**
	 * Instantiates a new city.
	 * 
	 * @param city
	 *            the city
	 */
	public City(String city) {
		this.city = city;
	}

	/**
	 * Gets the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "City [city=" + city + "]";
	}

	/**
	 * Return city in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<city>"+city+"</city>";
	}
}
