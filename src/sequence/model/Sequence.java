package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sequence.mvc.Model;

public class Sequence implements Model {
	private List<Activity> activities;
	private Patient patient;
	
	public Sequence() {
		this.activities=new ArrayList<Activity>();
	}
	
	public Sequence(Sequence sequence) {
		this.activities=sequence.getActivities();
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
	
	public List<Activity> getActivities() {
		return activities;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	@Override
	public String toString() {
		return "Sequence [activities=" + activities + ", patient=" + patient
				+ "]";
	}

}
