package sequence.model;

public class AnatomicStructure {
	private final String anatomicStructure;

	public AnatomicStructure(String anatomicStructure) {
		this.anatomicStructure=anatomicStructure;
	}

	public String getAnatomicStructure() {
		return anatomicStructure;
	}
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof AnatomicStructure)) return false;
	    return this.anatomicStructure.equalsIgnoreCase(((AnatomicStructure)o).getAnatomicStructure());
	}
	
	@Override
	public String toString() {
		return anatomicStructure;
	}
}
