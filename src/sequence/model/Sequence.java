package sequence.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sequence {
	private List<Activity> activities;
	
	public Sequence() {
		this.activities=new ArrayList<Activity>();
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
	
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}

	@Override
	public String toString() {
		return "Sequence [activities=" + activities + "]";
	}
}
