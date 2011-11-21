package sequence.model.location;

public class Country {
	private final String country;

	public Country(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Country [country=" + country + "]";
	}

	public String toXML() {
		return "<country>"+country+"</country>";
	}
}
