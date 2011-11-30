package sequence.model.activity;

import sequence.model.Actuator;
import sequence.model.Note;
import sequence.mvc.DefaultModel;
import sequence.mvc.Model;

public class Activity extends DefaultModel {
	private final int id;
	private String state;
	private int discipline;
	private int type;
	
	private Activitytime activitytime;
	private Actuator actuator;
	private Action action;
	private UsedInstruments usedInstrument;
	private AnatomicStructure anatomicStructure;
	private Note note;

	
	public Activity(int id, String state, int discipline, int type) {
		this.id=id;
		this.state=state;
		this.discipline=discipline;
		this.type=type;
		
		this.activitytime=new Activitytime();
		this.usedInstrument=new UsedInstruments();
	}

	public Activity(Activity activity) {
		this.id=activity.getId();
		this.state=activity.getState();
		this.discipline=activity.getDiscipline();
		this.type=activity.getType();
		
		this.activitytime=activity.getActivitytime();
		this.actuator=activity.getActuator();
		this.usedInstrument=activity.getUsedInstrument();
	}
	public void setState(String state) {
		this.state=state;
		
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public int getDiscipline() {
		return discipline;
	}

	public int getType() {
		return type;
	}

	public Activitytime getActivitytime() {
		return activitytime;
	}

	public Actuator getActuator() {
		return actuator;
	}

	public Action getAction() {
		return action;
	}
        
        public void setUsedInstrument(UsedInstruments usedInstruments) {
		this.usedInstrument=usedInstruments;
	}

	public UsedInstruments getUsedInstrument() {
		return usedInstrument;
	}

	public AnatomicStructure getTreatedStructure() {
		return anatomicStructure;
	}

	public void setActuator(Actuator actuator) {
		this.actuator=actuator;
	}
	
	public void setTreatedStructure(AnatomicStructure anatomicStructure) {
		this.anatomicStructure=anatomicStructure;
		
	}
	
	public void setAction(Action action) {
		this.action=action;
	}
	
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", state=" + state + ", discipline="
				+ discipline + ", type=" + type + ", activitytime="
				+ activitytime + ", actuator=" + actuator + ", action="
				+ action + ", usedInstrument=" + usedInstrument
				+ ", anatomicStructure=" + anatomicStructure + ", note=" + note
				+ "]";
	}

	public String toToolTip() {
		final String NEW_LINE = "<br>";
		return "<html>" + "&lt;" + action + ", " + anatomicStructure + ", " + usedInstrument + "&gt;" + NEW_LINE
				+ "begin at : " + activitytime.getStartTime() + NEW_LINE
				+ "end at : " + activitytime.getStopTime() + NEW_LINE
				+ "duration : " + activitytime.getDuration() + "</html>";
	}
	
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<activity id=\""+id+"\" state=\""+state+"\" discipline=\""+discipline+"\" type=\""+type+"\">"+NEW_LINE);
		sb.append("\t"+activitytime.toXML()+NEW_LINE);
		sb.append("\t"+actuator.toXML()+NEW_LINE);
		sb.append("\t<action>"+action.toXML()+"</action>"+NEW_LINE);
		sb.append("\t"+usedInstrument.toXML()+NEW_LINE);
		sb.append("\t<treatedStructure>"+anatomicStructure.toXML()+"</treatedStructure>"+NEW_LINE);
		sb.append("\t"+note.toXML()+NEW_LINE);
		sb.append("</activity>"+NEW_LINE);
		return sb.toString();
	}

	public void setType(int type) {
		this.type=type;
	}

	public void setDiscipline(int discipline) {
		this.discipline=discipline;
	}
}
