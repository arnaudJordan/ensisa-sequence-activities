package sequence.model;

public class Instrument {
	private final String instrument;

	public Instrument(String instrument) {
		this.instrument=instrument;
	}

	public String getInstrument() {
		return instrument;
	}

	@Override
	public String toString() {
		return "Instrument [instrument=" + instrument + "]";
	}

}
