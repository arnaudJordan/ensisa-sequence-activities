package sequence.model;

public class Note {
	private final String note;
	
	Note(String note)
	{
		this.note=note;
	}
	public String getNote() {
		return note;
	}
	
	@Override
	public String toString() {
		return "Note [note=" + note + "]";
	}
}
