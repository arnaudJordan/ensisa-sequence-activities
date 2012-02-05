package sequence.model;

import sequence.utilities.Sex;

/**
 * The Patient model.
 */
public class Patient {
	
	/** The age. */
	private int age;
	
	/** The sex. */
	private Sex sex;
	
	/** The position. */
	private Position position;
	
	/** The note. */
	private Note note;
	
	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public Sex getSex() {
		return sex;
	}
	
	/**
	 * Sets the sex.
	 *
	 * @param sex the new sex
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	/**
	 * Gets the posotion.
	 *
	 * @return the posotion
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public Note getNote() {
		return note;
	}
	
	/**
	 * Sets the note.
	 *
	 * @param note the new note
	 */
	public void setNote(Note note) {
		this.note = note;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Patient [age=" + age + ", sex=" + sex + ", actuator="
				+ position + ", note=" + note + "]";
	}
	
	/**
	 * Return patient in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<patient>");
		sb.append("\t<age>"+age+"</age>"+NEW_LINE);
		if(sex==Sex.male)
			sb.append("\t<sex>M</sex>"+NEW_LINE);
		else
			sb.append("\t<sex>F</sex>"+NEW_LINE);
		if(position!=null)
			sb.append("\t"+position.toXML()+NEW_LINE);
		if(note!=null)
			sb.append("\t"+note.toXML()+NEW_LINE);
		sb.append("</patient>");
		return sb.toString();
	}
	
	
}
