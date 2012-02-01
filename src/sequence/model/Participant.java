package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Participant model.
 */
public class Participant {
	
	/** The position. */
	private Position position;
	
	/** The name. */
	private Name name;
	
	/** The clinical degree. */
	private ClinicalDegree clinicalDegree;
	
	/** The academic degree. */
	private AcademicDegree academicDegree;
	
	/** The actors. */
	private List<Actor> actors;
	
	/** The note. */
	private Note note;
	
	/** The color. */
	private Color color;
	
	/**
	 * Instantiates a new participant.
	 */
	public Participant()
	{
		this.actors = new ArrayList<Actor>();
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public Name getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(Name name) {
		this.name = name;
	}
	
	/**
	 * Gets the clinical degree.
	 *
	 * @return the clinical degree
	 */
	public ClinicalDegree getClinicalDegree() {
		return clinicalDegree;
	}
	
	/**
	 * Sets the clinical degree.
	 *
	 * @param clinicalDegree the new clinical degree
	 */
	public void setClinicalDegree(ClinicalDegree clinicalDegree) {
		this.clinicalDegree = clinicalDegree;
	}
	
	/**
	 * Gets the academic degree.
	 *
	 * @return the academic degree
	 */
	public AcademicDegree getAcademicDegree() {
		return academicDegree;
	}
	
	/**
	 * Sets the academic degree.
	 *
	 * @param academicDegree the new academic degree
	 */
	public void setAcademicDegree(AcademicDegree academicDegree) {
		this.academicDegree = academicDegree;
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
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return actors.size();
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return actors.isEmpty();
	}

	/**
	 * Contains.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	public boolean contains(Object o) {
		return actors.contains(o);
	}

	/**
	 * Iterator.
	 *
	 * @return the iterator
	 */
	public Iterator<Actor> iterator() {
		return actors.iterator();
	}

	/**
	 * Adds the.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public boolean add(Actor e) {
		return actors.add(e);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Participant [position=" + position + ", name=" + name
				+ ", clinicalDegree=" + clinicalDegree + ", academicDegree="
				+ academicDegree + ", actors=" + actors + ", note=" + note
				+ ", color=" + color + "]";
	}

	/**
	 * Return participant in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<participant>"+NEW_LINE);
		if(position!=null)
			sb.append("\t"+position.toXML()+NEW_LINE);
		if(name!=null)
			sb.append("\t"+name.toXML()+NEW_LINE);
		if(clinicalDegree!=null)
			sb.append("\t"+clinicalDegree.toXML()+NEW_LINE);
		if(academicDegree!=null)
			sb.append("\t"+academicDegree.toXML()+NEW_LINE);
		for(Actor actor : actors)
			sb.append("\t"+actor.toXML()+NEW_LINE);
		if(note!=null)
			sb.append("\t"+note.toXML()+NEW_LINE);
		if(color!=null)
			sb.append("\t"+color.toXML()+NEW_LINE);
		sb.append("</participant>");
		return sb.toString();
	}
}
