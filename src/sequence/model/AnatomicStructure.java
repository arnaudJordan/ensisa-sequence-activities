package sequence.model;

public class AnatomicStructure {
	private final String anatomicStructure;

	public AnatomicStructure(String anatomicStructure) {
		this.anatomicStructure=anatomicStructure;
	}

	public String getAnatomicStructure() {
		return anatomicStructure;
	}
	
	@Override
	public String toString() {
		return "AnatomicStructure [anatomicStructure=" + anatomicStructure + "]";
	}
}
