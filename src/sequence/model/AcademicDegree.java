package sequence.model;

public class AcademicDegree {
	private final String academicDegree;

	public AcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}

	public String getAcademicDegree() {
		return academicDegree;
	}

	@Override
	public String toString() {
		return "AcademicDegree [academicDegree=" + academicDegree + "]";
	}
}
