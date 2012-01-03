package sequence.model.activity;

import sequence.model.Actuator;
import sequence.model.Note;
import sequence.mvc.DefaultModel;

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
		if(this.state == state) return;
		this.state=state;
		this.modelChange();
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		if(this.note == note) return;
		this.note = note;
		this.modelChange();
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
	public void setActivitytime(Activitytime activitytime) {
		if(this.activitytime == activitytime) return;
		this.activitytime=activitytime;
		this.modelChange();
	}
	public Actuator getActuator() {
		return actuator;
	}

	public Action getAction() {
		return action;
	}
        
    public void setUsedInstrument(UsedInstruments usedInstruments) {
        if(this.usedInstrument == usedInstruments) return;
		this.usedInstrument=usedInstruments;
		this.modelChange();
	}

	public UsedInstruments getUsedInstrument() {
		return usedInstrument;
	}

	public AnatomicStructure getTreatedStructure() {
		return anatomicStructure;
	}

	public void setActuator(Actuator actuator) {
		if(this.actuator == actuator) return;
		this.actuator=actuator;
		this.modelChange();
	}
	
	public void setTreatedStructure(AnatomicStructure anatomicStructure) {
		if(this.anatomicStructure == anatomicStructure) return;
		this.anatomicStructure=anatomicStructure;
		this.modelChange();
	}
	
	public void setAction(Action action) {
		if(this.action == action) return;
		this.action=action;
		this.modelChange();
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
		if(activitytime!=null)
			sb.append("\t"+activitytime.toXML()+NEW_LINE);
		if(actuator!=null)
			sb.append("\t"+actuator.toXML()+NEW_LINE);
		if(action!=null)
			sb.append("\t<action>"+action.toXML()+"</action>"+NEW_LINE);
		if(usedInstrument!=null)
			sb.append("\t"+usedInstrument.toXML()+NEW_LINE);
		if(anatomicStructure!=null)
			sb.append("\t<treatedStructure>"+anatomicStructure.toXML()+"</treatedStructure>"+NEW_LINE);
		if(note!=null)
			sb.append("\t"+note.toXML()+NEW_LINE);
		sb.append("</activity>"+NEW_LINE);
		return sb.toString();
	}

	public void setType(int type) {
		if(this.type == type) return;
		this.type=type;
		this.modelChange();
	}

	public void setDiscipline(int discipline) {
		if(this.discipline == discipline) return;
		this.discipline=discipline;
		this.modelChange();
	}
}
