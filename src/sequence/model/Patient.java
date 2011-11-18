package sequence.model;

import sequence.utilities.Sex;


public class Patient {
	private int age;
	private Sex sex;
	private Position position;
	private Note note;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Position getPosotion() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "Patient [age=" + age + ", sex=" + sex + ", actuator="
				+ position + ", note=" + note + "]";
	}
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<patient>");
		sb.append("\t<age>"+age+"</age>"+NEW_LINE);
		if(sex==Sex.male)
			sb.append("\t<sex>M</sex>"+NEW_LINE);
		else
			sb.append("\t<sex>F</sex>"+NEW_LINE);
		sb.append("\t"+position.toXML()+NEW_LINE);
		sb.append("\t"+note.toXML()+NEW_LINE);
		sb.append("</patient>");
		return sb.toString();
	}
	
	
}
