package sequence.model.activity;

/**
 * The Instrument model.
 */
public class Instrument {
	
	/** The instrument. */
	private final String instrument;

	/**
	 * Instantiates a new instrument.
	 * 
	 * @param instrument
	 *            the instrument
	 */
	public Instrument(String instrument) {
		this.instrument=instrument;
	}

	/**
	 * Gets the instrument.
	 * 
	 * @return the instrument
	 */
	public String getInstrument() {
		return instrument;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Instrument)) return false;
	    return this.instrument.equalsIgnoreCase(((Instrument)o).getInstrument());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return instrument;
	}
	
	/**
	 * Return intrument in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<instrument>"+instrument+"</instrument>";
	}
}
