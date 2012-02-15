package sequence.model.activity;

import sequence.model.Actuator;
import sequence.model.Note;
import sequence.mvc.DefaultModel;

/**
 * The Activity model.
 */
public class Activity extends DefaultModel implements Comparable<Activity> {

	/** The id. */
	private final int id;

	/** The state. */
	private String state;

	/** The discipline. */
	private int discipline;

	/** The type. */
	private int type;

	/** The activitytime. */
	private Activitytime activitytime;

	/** The actuator. */
	private Actuator actuator;

	/** The action. */
	private Action action;

	/** The used instrument. */
	private UsedInstruments usedInstrument;

	/** The anatomic structure. */
	private AnatomicStructure anatomicStructure;

	/** The note. */
	private Note note;

	/**
	 * Instantiates a new activity.
	 * 
	 * @param id
	 *            the id
	 * @param state
	 *            the state name
	 * @param discipline
	 *            the discipline number
	 * @param type
	 *            the type number
	 */
	public Activity(final int id, final String state, final int discipline,
			final int type) {
		this.id = id;
		this.state = state;
		this.discipline = discipline;
		this.type = type;

		activitytime = new Activitytime();
		usedInstrument = new UsedInstruments();
	}

	/**
	 * Instantiates a new activity by copy an other activity.
	 * 
	 * @param activity
	 *            the copied activity
	 */
	public Activity(final Activity activity) {
		id = activity.getId();
		state = activity.getState();
		discipline = activity.getDiscipline();
		type = activity.getType();

		activitytime = activity.getActivitytime();
		actuator = activity.getActuator();
		usedInstrument = activity.getUsedInstrument();
	}

	/**
	 * Sets the state.
	 * 
	 * @param state
	 *            the new state
	 */
	public void setState(final String state) {
		if (this.state == state)
			return;
		this.state = state;
		modelChange();
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
	 * @param note
	 *            the new note
	 */
	public void setNote(final Note note) {
		if (this.note == note)
			return;
		this.note = note;
		modelChange();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the state.
	 * 
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Gets the discipline.
	 * 
	 * @return the discipline
	 */
	public int getDiscipline() {
		return discipline;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Gets the activitytime.
	 * 
	 * @return the activitytime
	 */
	public Activitytime getActivitytime() {
		return activitytime;
	}

	/**
	 * Sets the activitytime.
	 * 
	 * @param activitytime
	 *            the new activitytime
	 */
	public void setActivitytime(final Activitytime activitytime) {
		if (this.activitytime == activitytime)
			return;
		this.activitytime = activitytime;
		modelChange();
	}

	/**
	 * Gets the actuator.
	 * 
	 * @return the actuator
	 */
	public Actuator getActuator() {
		return actuator;
	}

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Sets the used instrument.
	 * 
	 * @param usedInstruments
	 *            the new used instrument
	 */
	public void setUsedInstrument(final UsedInstruments usedInstruments) {
		if (usedInstrument == usedInstruments)
			return;
		usedInstrument = usedInstruments;
		modelChange();
	}

	/**
	 * Gets the used instrument.
	 * 
	 * @return the used instrument
	 */
	public UsedInstruments getUsedInstrument() {
		return usedInstrument;
	}

	/**
	 * Gets the treated structure.
	 * 
	 * @return the treated structure
	 */
	public AnatomicStructure getTreatedStructure() {
		return anatomicStructure;
	}

	/**
	 * Sets the actuator.
	 * 
	 * @param actuator
	 *            the new actuator
	 */
	public void setActuator(final Actuator actuator) {
		if (this.actuator == actuator)
			return;
		this.actuator = actuator;
		modelChange();
	}

	/**
	 * Sets the treated structure.
	 * 
	 * @param anatomicStructure
	 *            the new treated structure
	 */
	public void setTreatedStructure(final AnatomicStructure anatomicStructure) {
		if (this.anatomicStructure == anatomicStructure)
			return;
		this.anatomicStructure = anatomicStructure;
		modelChange();
	}

	/**
	 * Sets the action.
	 * 
	 * @param action
	 *            the new action
	 */
	public void setAction(final Action action) {
		if (this.action == action)
			return;
		this.action = action;
		modelChange();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Activity [id=" + id + ", state=" + state + ", discipline="
				+ discipline + ", type=" + type + ", activitytime="
				+ activitytime + ", actuator=" + actuator + ", action="
				+ action + ", usedInstrument=" + usedInstrument
				+ ", anatomicStructure=" + anatomicStructure + ", note=" + note
				+ "]";
	}

	/**
	 * Return ToolTip string.
	 * 
	 * @return the string
	 */
	public String toToolTip() {
		final String NEW_LINE = "<br>";
		return "<html>" + "&lt;" + action + ", " + anatomicStructure + ", "
				+ usedInstrument + "&gt;" + NEW_LINE + "begin at : "
				+ activitytime.getStartTime() + NEW_LINE + "end at : "
				+ activitytime.getStopTime() + NEW_LINE + "duration : "
				+ activitytime.getDuration() + "</html>";
	}

	/**
	 * Return activity in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		final StringBuilder sb = new StringBuilder("<activity id=\"" + id
				+ "\" state=\"" + state + "\" discipline=\"" + discipline
				+ "\" type=\"" + type + "\">" + NEW_LINE);
		if (activitytime != null)
			sb.append("\t" + activitytime.toXML() + NEW_LINE);
		if (actuator != null)
			sb.append("\t" + actuator.toXML() + NEW_LINE);
		if (action != null)
			sb.append("\t<action>" + action.toXML() + "</action>" + NEW_LINE);
		if (usedInstrument != null)
			sb.append("\t" + usedInstrument.toXML() + NEW_LINE);
		if (anatomicStructure != null)
			sb.append("\t<treatedStructure>" + anatomicStructure.toXML()
					+ "</treatedStructure>" + NEW_LINE);
		if (note != null)
			sb.append("\t" + note.toXML() + NEW_LINE);
		sb.append("</activity>" + NEW_LINE);
		return sb.toString();
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(final int type) {
		if (this.type == type)
			return;
		this.type = type;
		modelChange();
	}

	/**
	 * Sets the discipline.
	 * 
	 * @param discipline
	 *            the new discipline
	 */
	public void setDiscipline(final int discipline) {
		if (this.discipline == discipline)
			return;
		this.discipline = discipline;
		modelChange();
	}

	@Override
	public int compareTo(final Activity o) {
		return new Integer(id).compareTo(o.getId());
	}
}
