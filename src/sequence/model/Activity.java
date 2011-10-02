package sequence.model;

import sequence.mvc.Model;

public class Activity implements Model {
	private final int id;
	private final String state;
	private final int discipline;
	private final int type;
	
	private Activitytime activitytime;
	private Actuator actuator;
	private Actions actions;
	private UsedInstruments usedInstrument;
	private TreatedStructure treatedStructure;
	private Note note;

	
	public Activity(int id, String state, int discipline, int type) {
		this.id=id;
		this.state=state;
		this.discipline=discipline;
		this.type=type;
		
		this.activitytime=new Activitytime();
		this.actuator=new Actuator();
		this.actions=new Actions();
		this.usedInstrument=new UsedInstruments();
		this.treatedStructure=new TreatedStructure();
	}

	public Activity(Activity activity) {
		this.id=activity.getId();
		this.state=activity.getState();
		this.discipline=activity.getDiscipline();
		this.type=activity.getType();
		
		this.activitytime=activity.getActivitytime();
		this.actuator=activity.getActuator();
		this.actions=activity.getActions();
		this.usedInstrument=activity.getUsedInstrument();
		this.treatedStructure=activity.getTreatedStructure();
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

	public Actions getActions() {
		return actions;
	}

	public UsedInstruments getUsedInstrument() {
		return usedInstrument;
	}

	public TreatedStructure getTreatedStructure() {
		return treatedStructure;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", state=" + state + ", discipline="
				+ discipline + ", type=" + type + ", activitytime="
				+ activitytime + ", actuator=" + actuator + ", actions="
				+ actions + ", usedInstrument=" + usedInstrument
				+ ", treatedStructure=" + treatedStructure + ", note=" + note
				+ "]";
	}
	
}
