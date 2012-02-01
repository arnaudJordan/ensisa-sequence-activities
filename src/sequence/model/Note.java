package sequence.model;

/**
 * The Note model.
 */
public class Note {
	
	/** The note. */
	private final String note;
	
	/**
	 * Instantiates a new note.
	 *
	 * @param note the note
	 */
	public Note(String note)
	{
		this.note=note;
	}
	
	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Note)) return false;
		return this.note.equals(((Note)o).note);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Note [note=" + note + "]";
	}
	
	/**
	 * Return note in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		return "<note>"+note+"</note>";
	}
}
