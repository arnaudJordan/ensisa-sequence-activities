package sequence.model.location;

public class City {
	private final String city;

	public City(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "City [city=" + city + "]";
	}
}
