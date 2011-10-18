package sequence.model;

public class Instrument {
	private final String instrument;

	public Instrument(String instrument) {
		this.instrument=instrument;
	}

	public String getInstrument() {
		return instrument;
	}

	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Instrument)) return false;
	    return this.instrument.equalsIgnoreCase(((Instrument)o).getInstrument());
	}
	
	@Override
	public String toString() {
		return instrument;
	}

}
