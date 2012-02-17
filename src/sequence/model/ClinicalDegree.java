package sequence.model;

/**
 * The ClinicalDegree model.
 */
public class ClinicalDegree {

	/** The clinical degree. */
	private final String clinicalDegree;

	/**
	 * Instantiates a new clinical degree.
	 * 
	 * @param clinicalDegree
	 *            the clinical degree
	 */
	public ClinicalDegree(final String clinicalDegree) {
		this.clinicalDegree = clinicalDegree;
	}

	/**
	 * Gets the clinical degree.
	 * 
	 * @return the clinical degree
	 */
	public String getClinicalDegree() {
		return clinicalDegree;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClinicalDegree [clinicalDegree=" + clinicalDegree + "]";
	}

	/**
	 * Return clinicDegree in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<clinicalDegree>" + clinicalDegree + "</clinicalDegree>";
	}

}
