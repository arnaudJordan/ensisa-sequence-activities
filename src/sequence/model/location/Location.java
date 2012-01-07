package sequence.model.location;

import sequence.model.Note;

// TODO: Auto-generated Javadoc
/**
 * The Class Location.
 */
public class Location {
	
	/** The city. */
	private City city;
	
	/** The country. */
	private Country country;
	
	/** The institution. */
	private Institution institution;
	
	/** The operatingtheatre. */
	private Operatingtheatre operatingtheatre;
	
	/** The note. */
	private Note note;
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	/**
	 * Gets the institution.
	 *
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}
	
	/**
	 * Sets the institution.
	 *
	 * @param institution the new institution
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	/**
	 * Gets the operatingtheatre.
	 *
	 * @return the operatingtheatre
	 */
	public Operatingtheatre getOperatingtheatre() {
		return operatingtheatre;
	}
	
	/**
	 * Sets the operatingtheatre.
	 *
	 * @param operatingtheatre the new operatingtheatre
	 */
	public void setOperatingtheatre(Operatingtheatre operatingtheatre) {
		this.operatingtheatre = operatingtheatre;
	}
	
	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public Note getNote() {
		return note;
	}
	
	/**
	 * Sets the note.
	 *
	 * @param note the new note
	 */
	public void setNote(Note note) {
		this.note = note;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [city=" + city + ", country=" + country
				+ ", institution=" + institution + ", operatingtheatre="
				+ operatingtheatre + ", note=" + note + "]";
	}
	
	/**
	 * Return location in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<rec_location>"+NEW_LINE);
		if(city!=null)
			sb.append("\t"+city.toXML()+NEW_LINE);
		if(country!=null)
			sb.append("\t"+country.toXML()+NEW_LINE);
		if(institution!=null)
			sb.append("\t"+institution.toXML()+NEW_LINE);
		if(operatingtheatre!=null)
			sb.append("\t"+operatingtheatre.toXML()+NEW_LINE);
		if(note!=null)
			sb.append("\t"+note.toXML()+NEW_LINE);
		sb.append("</rec_location>"+NEW_LINE);
		return sb.toString();
	}
}
