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
import sequence.mvc.Model;
/**
 * The Sequence model.
 */
public class Sequence extends DefaultModel implements Iterable<Activity>, Model {

	/** The file. */
	private File file;
	
	/** The workflow id. */
	private final String workflowID;
	
	/** The activities. */
	private List<Activity> activities;
	
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
	 * @param workflowID the workflow id
	 */
	public Sequence(String workflowID) {
		this.activities=new ArrayList<Activity>();
		this.workflowID=workflowID;
	}
	
	/**
	 * Instantiates a new sequence.
	 *
	 * @param workflowID the workflow id
	 * @param file the file
	 */
	public Sequence(String workflowID, File file) {
		this.activities=new ArrayList<Activity>();
		this.workflowID=workflowID;
		this.file=file;
	}
	
	/**
	 * Instantiates a new sequence.
	 *
	 * @param sequence the sequence
	 */
	public Sequence(Sequence sequence) {
		this.activities=sequence.activities;
		this.workflowID=sequence.workflowID;
	}
	
	/**
	 * Instantiates a new sequence.
	 *
	 * @param workflowID the workflow id
	 * @param activities the activities
	 */
	public Sequence(String workflowID, List<Activity> activities) {
		this.activities=activities;
		this.workflowID=workflowID;
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
	 * @param o the o
	 * @return true, if successful
	 */
	public boolean contains(Object o) {
		return activities.contains(o);
	}

	/**
	 * Index of.
	 *
	 * @param o the o
	 * @return the int
	 */
	public int indexOf(Object o) {
		return activities.indexOf(o);
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Activity> iterator() {
		return activities.iterator();
	}

	/**
	 * Gets the.
	 *
	 * @param index the index
	 * @return the activity
	 */
	public Activity get(int index) {
		return activities.get(index);
	}
	
	/**
	 * Gets the last activity.
	 *
	 * @return the last activity
	 */
	public Activity getLastActivity() {
		return activities.get(this.size()-1);
	}
	
	/**
	 * Adds the activity.
	 *
	 * @param activity the activity
	 */
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public void addActivitySorted(Activity activity) {
		int index = 0;
		while (index < size()
				&& activity.getId() > activities.get(index).getId()) {
			index++;
		}
		activities.add(index, activity);
		modelChange();
	}
	
	public void removeActivity(Activity activity) {
		activities.remove(activity);
		modelChange();
	}

	/**
	 * Sets the patient.
	 *
	 * @param patient the new patient
	 */
	public void setPatient(Patient patient) {
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
	 * @param location the new location
	 */
	public void setLocation(Location location) {
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
		if(date==null) generateDate();
		return date;
	}

	private void generateDate() {
		this.date=new Date();
		this.date.setStartTime(get(0).getActivitytime().getStartTime());
		this.date.setStopTime(get(size()-1).getActivitytime().getStopTime());
		this.date.setDuration(this.date.getStopTime()-this.date.getStartTime());
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Sets the phases.
	 *
	 * @param phases the new phases
	 */
	public void setPhases(Phases phases) {
		this.phases = phases;
	}

	/**
	 * Gets the phases.
	 *
	 * @return the phases
	 */
	public Phases getPhases() {
		return this.phases;
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
	 * @param discipline the new discipline
	 */
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	/**
	 * Complete duration.
	 *
	 * @return the int
	 */
	public int completeDuration()
	{
		return getDate().getDuration();
	}

	/**
	 * Complete work duration.
	 *
	 * @return the int
	 */
	public int completeWorkDuration()
	{
		int duration =0;
		for(Activity activity : activities)
		{
			duration += activity.getActivitytime().getDuration();
		}
		return duration;
	}
	
	/**
	 * Phase duration.
	 *
	 * @return the list
	 */
	public List<Integer> phaseDuration()
	{
		List<Integer> phasesDuration = new ArrayList<Integer>();
		int nbPhase = phases.size();
		for(int i=0 ; i < nbPhase ; i++)
		{
			if(i<nbPhase-1)
				phasesDuration.add(phases.get(i+1).getDate() - phases.get(i).getDate());
			else
				phasesDuration.add(getDate().getStopTime() - phases.get(i).getDate());
		}
		return phasesDuration;
	}
	
	/**
	 * Gets the activities in phase.
	 *
	 * @param phase the phase
	 * @return the activities in phase
	 */
	public List<Activity> getActivitiesInPhase(Phase phase) {
		List<Activity> activities = new ArrayList<Activity>();
		Iterator<Activity> it = this.activities.iterator();
		boolean stop = false;
		while(!stop && it.hasNext()) {
			Activity current = it.next();
			if(current.getActivitytime().getStartTime() >= phase.getDate()
					&& current.getActivitytime().getStartTime() <= phase.getDate()+phaseDuration().get(phases.indexOf(phase)))
				activities.add(current);
			else if(!activities.isEmpty())
				stop = true;
		}
		return activities;
	}
	
	/**
	 * Gets the phase of activity.
	 *
	 * @param activity the activity
	 * @return the phase of activity
	 */
	public Phase getPhaseOfActivity(Activity activity) {
		for(Phase current : phases) {
			if(current.getDate() <= activity.getActivitytime().getStartTime()
					&& current.getDate()+phaseDuration().get(phases.indexOf(current)) >= activity.getActivitytime().getStartTime())
				return current;
		}
		return null;
	}
	
	/**
	 * Activity number.
	 *
	 * @return the int
	 */
	public int activityNumber()
	{
		return activities.size();
	}
	
	/**
	 * Mean activity duration.
	 *
	 * @return the int
	 */
	public int meanActivityDuration()
	{
		return completeDuration()/activityNumber();
	}
	
	/**
	 * States.
	 *
	 * @return the object[]
	 */
	public Object[] states()
	{
		Set<String> set = new HashSet<String>();
		for(Activity activity : activities)
		{
			set.add(activity.getState());
		}
		return set.toArray();
	}
	
	/**
	 * Disciplines.
	 *
	 * @return the object[]
	 */
	public Object[] disciplines()
	{
		Set<Integer> set = new HashSet<Integer>();
		for(Activity activity : activities)
		{
			set.add(activity.getDiscipline());
		}
		return set.toArray();
	}
	
	/**
	 * Types.
	 *
	 * @return the object[]
	 */
	public Object[] types()
	{
		Set<Integer> set = new HashSet<Integer>();
		for(Activity activity : activities)
		{
			set.add(activity.getType());
		}
		return set.toArray();
	}
	
	/**
	 * Action structure instrument number.
	 *
	 * @return the int[]
	 */
	public int[] ActionStructureInstrumentNumber()
	{
		Object[] tab = ActionsStructuresInstruments();
		return new int[]{((Object[]) tab[0]).length, ((Object[]) tab[1]).length,((Object[]) tab[2]).length};
	}
	
	/**
	 * Actions structures instruments.
	 *
	 * @return the object[]
	 */
	public Object[] ActionsStructuresInstruments()
	{
		List<Action> actions = new ArrayList<Action>();
		List<AnatomicStructure> anatomicStructures = new ArrayList<AnatomicStructure>();
		List<Instrument> instruments = new ArrayList<Instrument>();
		for(Activity current : activities) {
			if(!actions.contains(current.getAction()))
				actions.add(current.getAction());
			if(!anatomicStructures.contains(current.getTreatedStructure()))
				anatomicStructures.add(current.getTreatedStructure());
			for(Instrument ins : current.getUsedInstrument())
				if(!instruments.contains(ins))
					instruments.add(ins);
		}
		/*Collections.sort(actions, new Comparator<Action>() {
			public int compare(Action o1, Action o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});*/
		/*Collections.sort(anatomicStructures, new Comparator<AnatomicStructure>() {
			public int compare(AnatomicStructure o1, AnatomicStructure o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		Collections.sort(usedInstruments, new Comparator<UsedInstruments>() {
			public int compare(UsedInstruments o1, UsedInstruments o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});*/
		return new Object[]{actions.toArray(), anatomicStructures.toArray(),instruments.toArray()};
	}
	
	/**
	 * Positions.
	 *
	 * @return the object[]
	 */
	public Object[] Positions()
	{
		List<Position> positions = new ArrayList<Position>();
		for(Activity current : activities) {
			if(!positions.contains(current.getActuator().getPosition()))
				positions.add(current.getActuator().getPosition());
		}
/*		Collections.sort(positions, new Comparator<Position>() {
			public int compare(Position o1, Position o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
*/		return positions.toArray();
	}
	
	/**
	 * Body parts.
	 *
	 * @return the object[]
	 */
	public Object[] BodyParts()
	{
		List<BodyPart> bodyParts = new ArrayList<BodyPart>();
		for(Activity current : activities) {
			if(!bodyParts.contains(current.getActuator().getUsedbodypart()))
				bodyParts.add(current.getActuator().getUsedbodypart());
		}
/*		Collections.sort(bodyParts, new Comparator<BodyPart>() {
			public int compare(BodyPart o1, BodyPart o2) {
				return o1.getBodypart().compareTo(o2.getBodypart());
			}
		});*/
		return bodyParts.toArray();
	}
	
	/**
	 * To file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void toFile(File file) throws IOException
	{
		final String NEW_LINE = System.getProperty("line.separator");
		FileWriter fw = new FileWriter(file);
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+NEW_LINE);
		fw.write("<iccas xmlns=\"http://www.iccas.de/projects/workflow\">"+NEW_LINE);
		fw.write(this.toXML());
		fw.write("</iccas>");
		fw.close();
	}
	
	/* (non-Javadoc)
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
		StringBuilder sb = new StringBuilder("<rec_workflow workflowID=\""+workflowID+"\" ver=\"0.2\" rec_type=\"LIVE\">"+NEW_LINE);
		if(discipline!=null)
			sb.append("\t"+discipline.toXML()+NEW_LINE);
		if(patient!=null)
			sb.append("\t"+patient.toXML()+NEW_LINE);
		if(location!=null)
			sb.append("\t"+location.toXML()+NEW_LINE);
		if(date!=null)
			sb.append("\t"+date.toXML()+NEW_LINE);
		for(Activity a : activities)
			sb.append("\t"+a.toXML()+NEW_LINE);
		if(phases!=null)
			sb.append("\t"+phases.toXML()+NEW_LINE);
		sb.append("</rec_workflow>"+NEW_LINE);
		return sb.toString();
	}
}
