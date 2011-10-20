package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	public String getWorkflowID() {
		return workflowID;
	}

	public int size() {
		return activities.size();
	}

	public boolean isEmpty() {
		return activities.isEmpty();
	}

	public boolean contains(Object o) {
		return activities.contains(o);
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

	@Override
	public String toString() {
		return "Sequence [workflowID=" + workflowID + ", activities="
				+ activities + ", phases=" + phases + ", patient=" + patient
				+ ", location=" + location + ", date=" + date + ", discipline="
				+ discipline + "]";
	}
}
