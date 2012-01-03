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
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Note)) return false;
		return this.note.equals(((Note)o).note);
	}
	
	@Override
	public String toString() {
		return "Note [note=" + note + "]";
	}
	
	public String toXML() {
		return "<note>"+note+"</note>";
	}
}
