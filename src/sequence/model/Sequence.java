package sequence.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sequence.model.activity.Action;
import sequence.model.activity.Activity;
import sequence.model.activity.AnatomicStructure;
import sequence.model.activity.UsedInstruments;
import sequence.model.location.Location;
import sequence.mvc.DefaultModel;
import sequence.mvc.Model;

public class Sequence extends DefaultModel implements Iterable<Activity>, Model {
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
		return date.getDuration();
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
	
	
	public int[] ActionStructureInstrumentNumber()
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
		return new int[]{actions.size(), anatomicStructures.size(),usedInstruments.size()};
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
