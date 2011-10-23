package sequence.model;

public class ClinicalDegree {
	private final String clinicalDegree;

	public ClinicalDegree(String clinicalDegree) {
		this.clinicalDegree = clinicalDegree;
	}

	public String getClinicalDegree() {
		return clinicalDegree;
	}

	@Override
	public String toString() {
		return "ClinicalDegree [clinicalDegree=" + clinicalDegree + "]";
	}
	
	public String toXML() {
		return "<clinicalDegree>"+clinicalDegree+"</clinicalDegree>";
	}

}
