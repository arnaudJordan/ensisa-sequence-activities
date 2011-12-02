package sequence.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import sequence.model.activity.Action;
import sequence.model.activity.Activity;
import sequence.model.activity.AnatomicStructure;
import sequence.model.activity.BodyPart;
import sequence.model.activity.Instrument;
import sequence.model.activity.UsedInstruments;
import sequence.model.location.Location;
import sequence.mvc.DefaultModel;
import sequence.mvc.Model;

public class Sequence extends DefaultModel implements Iterable<Activity>, Model {
	private File file;
	private final String workflowID;
	private List<Activity> activities;
	private Phases phases;
	private Patient patient;
	private Location location;
	private Date date;
	private Discipline discipline;
	
	
	public Sequence(String workflowID) {
		this.activities=new ArrayList<Activity>();
		this.workflowID=workflowID;
	}
	public Sequence(String workflowID, File file) {
		this.activities=new ArrayList<Activity>();
		this.workflowID=workflowID;
		this.file=file;
	}
	
	public Sequence(Sequence sequence) {
		this.activities=sequence.activities;
		this.workflowID=sequence.workflowID;
	}
	
	public Sequence(String workflowID, List<Activity> activities) {
		this.activities=activities;
		this.workflowID=workflowID;
	}
	
	
	public String getWorkflowID() {
		return workflowID;
	}

	public File getFile() {
		return file;
	}
	public int size() {
		return activities.size();
	}

	public boolean isEmpty() {
		return activities.isEmpty();
	}

	public void clear() {
		activities.clear();
	}

	public boolean contains(Object o) {
		return activities.contains(o);
	}

	public int indexOf(Object o) {
		return activities.indexOf(o);
	}

	public Iterator<Activity> iterator() {
		return activities.iterator();
	}

	public Activity get(int index) {
		return activities.get(index);
	}
	public Activity getLastActivity() {
		return activities.get(this.size()-1);
	}
	
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPhases(Phases phases) {
		this.phases = phases;
	}

	public Phases getPhases() {
		return phases;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	public int completeDuration()
	{
		if(getDate()==null) return 0;
		return getDate().getDuration();
	}

	public int completeWorkDuration()
	{
		int duration =0;
		for(Activity activity : activities)
		{
			duration += activity.getActivitytime().getDuration();
		}
		return duration;
	}
	
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
	
	public Phase getPhaseOfActivity(Activity activity) {
		for(Phase current : phases) {
			if(current.getDate() <= activity.getActivitytime().getStartTime()
					&& current.getDate()+phaseDuration().get(phases.indexOf(current)) >= activity.getActivitytime().getStartTime())
				return current;
		}
		return null;
	}
	
	public int activityNumber()
	{
		return activities.size();
	}
	
	public int meanActivityDuration()
	{
		return completeDuration()/activityNumber();
	}
	public Object[] states()
	{
		Set<String> set = new HashSet<String>();
		for(Activity activity : activities)
		{
			set.add(activity.getState());
		}
		return set.toArray();
	}
	public Object[] disciplines()
	{
		Set<Integer> set = new HashSet<Integer>();
		for(Activity activity : activities)
		{
			set.add(activity.getDiscipline());
		}
		return set.toArray();
	}
	public Object[] types()
	{
		Set<Integer> set = new HashSet<Integer>();
		for(Activity activity : activities)
		{
			set.add(activity.getType());
		}
		return set.toArray();
	}
	
	public int[] ActionStructureInstrumentNumber()
	{
		Object[] tab = ActionsStructuresInstruments();
		return new int[]{((Object[]) tab[0]).length, ((Object[]) tab[1]).length,((Object[]) tab[2]).length};
	}
	public Object[] ActionsStructuresInstruments()
	{
		List<Action> actions = new ArrayList<Action>();
		List<AnatomicStructure> anatomicStructures = new ArrayList<AnatomicStructure>();
		List<UsedInstruments> usedInstruments = new ArrayList<UsedInstruments>();
		for(Activity current : activities) {
			if(!actions.contains(current.getAction()))
				actions.add(current.getAction());
			if(!anatomicStructures.contains(current.getTreatedStructure()))
				anatomicStructures.add(current.getTreatedStructure());
			if(!usedInstruments.contains(current.getUsedInstrument()))
				usedInstruments.add(current.getUsedInstrument());
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
		return new Object[]{actions.toArray(), anatomicStructures.toArray(),usedInstruments.toArray()};
	}
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
	
	public void toFile(File file) throws IOException
	{
		FileWriter fw = new FileWriter(file);
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><iccas xmlns=\"http://www.iccas.de/projects/workflow\">");
		fw.write(this.toXML());
		fw.write("</iccas>");
		fw.close();
	}
	
	@Override
	public String toString() {
		return "Sequence [workflowID=" + workflowID + ", activities="
				+ activities + ", phases=" + phases + ", patient=" + patient
				+ ", location=" + location + ", date=" + date + ", discipline="
				+ discipline + "]";
	}
	
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<rec_workflow workflowID=\""+workflowID+"\" ver=\"0.2\" rec_type=\"LIVE\">");
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
		sb.append("</rec_workflow>");
		return sb.toString();
	}
}
