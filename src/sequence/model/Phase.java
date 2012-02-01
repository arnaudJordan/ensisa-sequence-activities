package sequence.model;

import sequence.mvc.Model;
import sequence.mvc.ModelListener;

/**
 * The Phase model.
 */
public class Phase implements Model {
	
	/** The date. */
	private final int date;
	
	/** The name. */
	private String name;

	private int stopTime = 0;
	
	/**
	 * Instantiates a new phase.
	 *
	 * @param date the date
	 */
	public Phase(int date) {
		super();
		this.date = date;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public int getDate() {
		return date;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name=name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Phase)) return false;
	    return this.date == (((Phase)o).getDate()) && this.name.equalsIgnoreCase(((Phase) o).getName());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Phase [date=" + date + ", name=" + name + "]";
	}
	
	/**
	 * Return phase in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		return "<value time=\""+date+"\">"+name+"</value>";
	}

	@Override
	public void addModelListener(ModelListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeModelListener(ModelListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void modelChange() {
		// TODO Auto-generated method stub
	}

	public void setStopTime(int stopTime) {
		this.stopTime=stopTime;	
	}
	public int getStopTime() {
		return this.stopTime;	
	}
}
