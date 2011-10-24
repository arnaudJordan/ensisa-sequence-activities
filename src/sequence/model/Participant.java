package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Participant {
	private Position position;
	private Name name;
	private ClinicalDegree clinicalDegree;
	private AcademicDegree academicDegree;
	private List<Actor> actors;
	private Note note;
	private Color color;
	
	public Participant()
	{
		this.actors = new ArrayList<Actor>();
	}
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public ClinicalDegree getClinicalDegree() {
		return clinicalDegree;
	}
	public void setClinicalDegree(ClinicalDegree clinicalDegree) {
		this.clinicalDegree = clinicalDegree;
	}
	public AcademicDegree getAcademicDegree() {
		return academicDegree;
	}
	public void setAcademicDegree(AcademicDegree academicDegree) {
		this.academicDegree = academicDegree;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public int size() {
		return actors.size();
	}

	public boolean isEmpty() {
		return actors.isEmpty();
	}

	public boolean contains(Object o) {
		return actors.contains(o);
	}

	public Iterator<Actor> iterator() {
		return actors.iterator();
	}

	public boolean add(Actor e) {
		return actors.add(e);
	}

	@Override
	public String toString() {
		return "Participant [position=" + position + ", name=" + name
				+ ", clinicalDegree=" + clinicalDegree + ", academicDegree="
				+ academicDegree + ", actors=" + actors + ", note=" + note
				+ ", color=" + color + "]";
	}

	public String toXML() {
		StringBuilder sb = new StringBuilder("<participant>\n");
		if(clinicalDegree!=null)
			sb.append("\t"+position.toXML()+"\n");
		if(clinicalDegree!=null)
			sb.append("\t"+name.toXML()+"\n");
		if(clinicalDegree!=null)
			sb.append("\t"+clinicalDegree.toXML()+"\n");
		sb.append("\t"+academicDegree.toXML()+"\n");
		for(Actor actor : actors)
			sb.append("\t"+actor.toXML()+"\n");
		if(clinicalDegree!=null)
			sb.append("\t"+note.toXML()+"\n");
		if(clinicalDegree!=null)
			sb.append("\t"+color.toXML()+"\n");
		sb.append("</participant>");
		return sb.toString();
	}
}
