package sequence.model;

/**
 * The AcademicDegree model.
 */
public class AcademicDegree {
	
	/** The academic degree. */
	private final String academicDegree;

	/**
	 * Instantiates a new academic degree.
	 *
	 * @param academicDegree the academic degree
	 */
	public AcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}

	/**
	 * Gets the academic degree.
	 *
	 * @return the academic degree
	 */
	public String getAcademicDegree() {
		return academicDegree;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AcademicDegree [academicDegree=" + academicDegree + "]";
	}
	
	/**
	 * Return academicdegree in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		return "<academicDegree>"+academicDegree+"</academicDegree>";
	}
}
