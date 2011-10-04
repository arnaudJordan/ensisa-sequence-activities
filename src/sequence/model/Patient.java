package sequence.model;


public class Patient {
	private int age;
	private Sex sex;
	private Actuator actuator;
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
	public Actuator getActuator() {
		return actuator;
	}
	public void setActuator(Actuator actuator) {
		this.actuator = actuator;
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
				+ actuator + ", note=" + note + "]";
	}
	
	
}
