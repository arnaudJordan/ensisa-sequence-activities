package sequence.model.location;

import sequence.model.Note;

public class Location {
	private City city;
	private Country country;
	private Institution institution;
	private Operatingtheatre operatingtheatre;
	private Note note;
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public Operatingtheatre getOperatingtheatre() {
		return operatingtheatre;
	}
	public void setOperatingtheatre(Operatingtheatre operatingtheatre) {
		this.operatingtheatre = operatingtheatre;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Location [city=" + city + ", country=" + country
				+ ", institution=" + institution + ", operatingtheatre="
				+ operatingtheatre + ", note=" + note + "]";
	}
	public String toXML() {
		StringBuilder sb = new StringBuilder("<rec_location>\n");
		if(city!=null)
			sb.append("\t"+city.toXML()+"\n");
		if(country!=null)
			sb.append("\t"+country.toXML()+"\n");
		if(institution!=null)
			sb.append("\t"+institution.toXML()+"\n");
		if(operatingtheatre!=null)
			sb.append("\t"+operatingtheatre.toXML()+"\n");
		if(note!=null)
			sb.append("\t"+note.toXML()+"\n");
		sb.append("</rec_location>\n");
		return sb.toString();
	}
}
