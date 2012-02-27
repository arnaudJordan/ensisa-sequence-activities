package sequence.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import sequence.model.activity.Action;
import sequence.model.activity.Activity;
import sequence.model.activity.AnatomicStructure;
import sequence.model.activity.BodyPart;
import sequence.model.activity.Instrument;
import sequence.model.location.Location;
import sequence.mvc.DefaultModel;

/**
 * The Sequence model.
 */
public class Sequence extends DefaultModel implements Iterable<Activity> {

	/** The file. */
	private File file;

	/** The workflow id. */
	private final String workflowID;

	/** The activities. */
	private final List<Activity> activities;

	/** The phases. */
	private Phases phases;

	/** The patient. */
	private Patient patient;

	/** The location. */
	private Location location;

	/** The date. */
	private Date date;

	/** The discipline. */
	private Discipline discipline;

	/**
	 * Instantiates a new sequence.
	 * 
	 * @param workflowID
	 *            the workflow id
	 */
	public Sequence(final String workflowID) {
		activities = new ArrayList<Activity>();
		this.workflowID = workflowID;
	}

	/**
	 * Instantiates a new sequence.
	 * 
	 * @param workflowID
	 *            the workflow id
	 * @param file
	 *            the file
	 */
	public Sequence(final String workflowID, final File file) {
		activities = new ArrayList<Activity>();
		this.workflowID = workflowID;
		this.file = file;
	}

	/**
	 * Instantiates a new sequence.
	 * 
	 * @param sequence
	 *            the sequence
	 */
	public Sequence(final Sequence sequence) {
		activities = sequence.activities;
		workflowID = sequence.workflowID;
	}

	/**
	 * Instantiates a new sequence.
	 * 
	 * @param workflowID
	 *            the workflow id
	 * @param activities
	 *            the activities
	 */
	public Sequence(final String workflowID, final List<Activity> activities) {
		this.activities = activities;
		this.workflowID = workflowID;
	}

	/**
	 * Gets the workflow id.
	 * 
	 * @return the workflow id
	 */
	public String getWorkflowID() {
		return workflowID;
	}

	/**
	 * Gets the file.
	 * 
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Size.
	 * 
	 * @return the int
	 */
	public int size() {
		return activities.size();
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return activities.isEmpty();
	}

	/**
	 * Clear.
	 */
	public void clear() {
		activities.clear();
	}

	/**
	 * Contains.
	 * 
	 * @param o
	 *            the object
	 * @return true, if successful
	 */
	public boolean contains(final Object o) {
		return activities.contains(o);
	}

	/**
	 * Index of.
	 * 
	 * @param o
	 *            the object
	 * @return the int
	 */
	public int indexOf(final Object o) {
		return activities.indexOf(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Activity> iterator() {
		return activities.iterator();
	}

	/**
	 * Gets the.
	 * 
	 * @param index
	 *            the index
	 * @return the activity
	 */
	public Activity get(final int index) {
		return activities.get(index);
	}

	/**
	 * Gets the last activity.
	 * 
	 * @return the last activity
	 */
	public Activity getLastActivity() {
		return activities.get(size() - 1);
	}

	/**
	 * Adds the activity.
	 * 
	 * @param activity
	 *            the activity
	 */
	public void addActivity(final Activity activity) {
		activities.add(activity);
	}

	/**
	 * Adds the activity at the right index, sorted by activities' id.
	 * 
	 * @param activity
	 *            the activity
	 */
	public void addActivitySorted(final Activity activity) {
		int index = 0;
		while (index < size()
				&& activity.getId() > activities.get(index).getId()) {
			index++;
		}
		activities.add(index, activity);
		modelChange();
	}

	/**
	 * Removes the activity.
	 * 
	 * @param activity
	 *            the activity
	 */
	public void removeActivity(final Activity activity) {
		activities.remove(activity);
		modelChange();
	}

	/**
	 * Sets the patient.
	 * 
	 * @param patient
	 *            the new patient
	 */
	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

	/**
	 * Gets the patient.
	 * 
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the new location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate() {
		if (date == null)
			generateDate();
		return date;
	}

	/**
	 * Generate date.
	 */
	private void generateDate() {
		date = new Date();
		date.setStartTime(get(0).getActivitytime().getStartTime());
		date.setStopTime(get(size() - 1).getActivitytime().getStopTime());
		date.setDuration(date.getStopTime() - date.getStartTime());
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            the new date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Sets the phases.
	 * 
	 * @param phases
	 *            the new phases
	 */
	public void setPhases(final Phases phases) {
		this.phases = phases;
	}

	/**
	 * Gets the phases.
	 * 
	 * @return the phases
	 */
	public Phases getPhases() {
		return phases;
	}

	/**
	 * Gets the discipline.
	 * 
	 * @return the discipline
	 */
	public Discipline getDiscipline() {
		return discipline;
	}

	/**
	 * Sets the discipline.
	 * 
	 * @param discipline
	 *            the new discipline
	 */
	public void setDiscipline(final Discipline discipline) {
		this.discipline = discipline;
	}

	/**
	 * Remove last activity.
	 * 
	 */
	public void removeLastActivity() {
		activities.remove(activities.size() - 1);
	}

	/**
	 * Returns the complete duration of the sequence.
	 * 
	 * @return the complete duration of the sequence
	 */
	public int completeDuration() {
		return getDate().getDuration();
	}

	/**
	 * Returns the sum of all activities' duration.
	 * 
	 * @return the sum of all activities' duration
	 */
	public int completeWorkDuration() {
		int duration = 0;
		for (final Activity activity : activities) {
			duration += activity.getActivitytime().getDuration();
		}
		return duration;
	}

	/**
	 * Returns the list of all phases' duration.
	 * 
	 * @return the list of all phases' duration
	 */
	public List<Integer> phaseDuration() {
		final List<Integer> phasesDuration = new ArrayList<Integer>();
		final int nbPhase = phases.size();
		for (int i = 0; i < nbPhase; i++) {
			if (i < nbPhase - 1)
				phasesDuration.add(phases.get(i + 1).getDate()
						- phases.get(i).getDate());
			else
				phasesDuration.add(getDate().getStopTime()
						- phases.get(i).getDate());
		}
		return phasesDuration;
	}

	/**
	 * Gets the activities contained in the phase.
	 * 
	 * @param phase
	 *            the phase
	 * @return the activities contained in the phase
	 */
	public List<Activity> getActivitiesInPhase(final Phase phase) {
		final List<Activity> activities = new ArrayList<Activity>();
		final Iterator<Activity> it = this.activities.iterator();
		boolean stop = false;
		while (!stop && it.hasNext()) {
			final Activity current = it.next();
			if (current.getActivitytime().getStartTime() >= phase.getDate()
					&& current.getActivitytime().getStartTime() <= phase
							.getDate()
							+ phaseDuration().get(phases.indexOf(phase)))
				activities.add(current);
			else if (!activities.isEmpty())
				stop = true;
		}
		return activities;
	}

	/**
	 * Gets the phase that contains the activity.
	 * 
	 * @param activity
	 *            the activity
	 * @return the phase that contains the activity
	 */
	public Phase getPhaseOfActivity(final Activity activity) {
		for (final Phase current : phases) {
			if (current.getDate() <= activity.getActivitytime().getStartTime()
					&& current.getDate()
							+ phaseDuration().get(phases.indexOf(current)) >= activity
							.getActivitytime().getStartTime())
				return current;
		}
		return null;
	}

	/**
	 * Activity number.
	 * 
	 * @return the int
	 */
	public int activityNumber() {
		return activities.size();
	}

	/**
	 * Mean activity duration.
	 * 
	 * @return the int
	 */
	public int meanActivityDuration() {
		return completeDuration() / activityNumber();
	}

	/**
	 * Returns an array of all the different activities' states contained in
	 * this sequence.
	 * 
	 * @return the array of all the different activities' states contained in
	 *         this sequence
	 */
	public Object[] states() {
		final Set<String> set = new HashSet<String>();
		for (final Activity activity : activities) {
			set.add(activity.getState());
		}
		return set.toArray();
	}

	/**
	 * Returns an array of all the different activities' disciplines contained
	 * in this sequence.
	 * 
	 * @return the array of all the different activities' disciplines contained
	 *         in this sequence
	 */
	public Object[] disciplines() {
		final Set<Integer> set = new HashSet<Integer>();
		for (final Activity activity : activities) {
			set.add(activity.getDiscipline());
		}
		return set.toArray();
	}

	/**
	 * Returns an array of all the different activities' types contained in this
	 * sequence.
	 * 
	 * @return the array of all the different activities' types contained in
	 *         this sequence
	 */
	public Object[] types() {
		final Set<Integer> set = new HashSet<Integer>();
		for (final Activity activity : activities) {
			set.add(activity.getType());
		}
		return set.toArray();
	}

	/**
	 * Returns the number of all the different activities' action, structure and
	 * instruments contained in this sequence.
	 * 
	 * @return the number array of all the different activities' action,
	 *         structure and instruments contained in this sequence
	 */
	public int[] ActionStructureInstrumentNumber() {
		final Object[] tab = ActionsStructuresInstruments();
		return new int[] { ((Object[]) tab[0]).length,
				((Object[]) tab[1]).length, ((Object[]) tab[2]).length };
	}

	/**
	 * Returns an array of all the different activities' action, structure and
	 * instruments contained in this sequence.
	 * 
	 * @return the array of all the different activities' action, structure and
	 *         instruments contained in this sequence
	 */
	public Object[] ActionsStructuresInstruments() {
		final List<Action> actions = new ArrayList<Action>();
		final List<AnatomicStructure> anatomicStructures = new ArrayList<AnatomicStructure>();
		final List<Instrument> instruments = new ArrayList<Instrument>();
		for (final Activity current : activities) {
			if (!actions.contains(current.getAction()))
				actions.add(current.getAction());
			if (!anatomicStructures.contains(current.getTreatedStructure()))
				anatomicStructures.add(current.getTreatedStructure());
			for (final Instrument ins : current.getUsedInstrument())
				if (!instruments.contains(ins))
					instruments.add(ins);
		}
		/*
		 * Collections.sort(actions, new Comparator<Action>() { public int
		 * compare(Action o1, Action o2) { return
		 * o1.toString().compareTo(o2.toString()); } });
		 */
		/*
		 * Collections.sort(anatomicStructures, new
		 * Comparator<AnatomicStructure>() { public int
		 * compare(AnatomicStructure o1, AnatomicStructure o2) { return
		 * o1.toString().compareTo(o2.toString()); } });
		 * Collections.sort(usedInstruments, new Comparator<UsedInstruments>() {
		 * public int compare(UsedInstruments o1, UsedInstruments o2) { return
		 * o1.toString().compareTo(o2.toString()); } });
		 */
		return new Object[] { actions.toArray(), anatomicStructures.toArray(),
				instruments.toArray() };
	}

	/**
	 * Returns an array of all the different actuators' positions of all the
	 * activities contained in this sequence.
	 * 
	 * @return Returns the array of all the different actuators' positions of
	 *         all the activities contained in this sequence
	 */
	public Object[] Positions() {
		final List<Position> positions = new ArrayList<Position>();
		for (final Activity current : activities) {
			if (!positions.contains(current.getActuator().getPosition()))
				positions.add(current.getActuator().getPosition());
		}
		/*
		 * Collections.sort(positions, new Comparator<Position>() { public int
		 * compare(Position o1, Position o2) { return
		 * o1.toString().compareTo(o2.toString()); } });
		 */return positions.toArray();
	}

	/**
	 * Returns an array of all the different actuators' used body parts of all
	 * the activities contained in this sequence.
	 * 
	 * @return the array of all the different actuators' used body parts of all
	 *         the activities contained in this sequence
	 */
	public Object[] BodyParts() {
		final List<BodyPart> bodyParts = new ArrayList<BodyPart>();
		for (final Activity current : activities) {
			if (!bodyParts.contains(current.getActuator().getUsedbodypart()))
				bodyParts.add(current.getActuator().getUsedbodypart());
		}
		/*
		 * Collections.sort(bodyParts, new Comparator<BodyPart>() { public int
		 * compare(BodyPart o1, BodyPart o2) { return
		 * o1.getBodypart().compareTo(o2.getBodypart()); } });
		 */
		return bodyParts.toArray();
	}

	/**
	 * To file.
	 * 
	 * @param file
	 *            the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void toFile(final File file) throws IOException {
		final String NEW_LINE = System.getProperty("line.separator");
		final FileWriter fw = new FileWriter(file);
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NEW_LINE);
		fw.write("<iccas xmlns=\"http://www.iccas.de/projects/workflow\">"
				+ NEW_LINE);
		fw.write(toXML());
		fw.write("</iccas>");
		fw.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sequence [file=" + file + ", workflowID=" + workflowID
				+ ", activities=" + activities + ", phases=" + phases
				+ ", patient=" + patient + ", location=" + location + ", date="
				+ date + ", discipline=" + discipline + "]";
	}

	/**
	 * Return sequence in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		final StringBuilder sb = new StringBuilder(
				"<rec_workflow workflowID=\"" + workflowID
						+ "\" ver=\"0.2\" rec_type=\"LIVE\">" + NEW_LINE);
		if (discipline != null)
			sb.append("\t" + discipline.toXML() + NEW_LINE);
		if (patient != null)
			sb.append("\t" + patient.toXML() + NEW_LINE);
		if (location != null)
			sb.append("\t" + location.toXML() + NEW_LINE);
		if (date != null)
			sb.append("\t" + date.toXML() + NEW_LINE);
		for (final Activity a : activities)
			sb.append("\t" + a.toXML() + NEW_LINE);
		if (phases != null)
			sb.append("\t" + phases.toXML() + NEW_LINE);
		sb.append("</rec_workflow>" + NEW_LINE);
		return sb.toString();
	}
}
