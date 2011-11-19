package sequence.model;

public class Note {
	private final String note;
	
	public Note(String note)
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
	
	public String toXML() {
		return "<note>"+note+"</note>";
	}
}
