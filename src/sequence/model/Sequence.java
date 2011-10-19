package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sequence.mvc.DefaultModel;
import sequence.mvc.Model;

public class Sequence extends DefaultModel implements Iterable<Activity>, Model {
	private List<Activity> activities;
	private Phases phases;
	private Patient patient;
	
	public Sequence() {
		this.activities=new ArrayList<Activity>();
	}
	
	public Sequence(Sequence sequence) {
		this.activities=sequence.activities;
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

	public void setPhases(Phases phases) {
		this.phases = phases;
	}

	public Phases getPhases() {
		return phases;
	}

	@Override
	public String toString() {
		return "Sequence [activities=" + activities + ", phases=" + phases
				+ ", patient=" + patient + "]";
	}



}
