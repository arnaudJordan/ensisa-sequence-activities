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
}
