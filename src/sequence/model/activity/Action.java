package sequence.model.activity;

/**
 * The Action model.
 */
public class Action {
	
	/** The action name. */
	private final String action;
	
	/**
	 * Instantiates a new action.
	 *
	 * @param action the action name
	 */
	public Action(String action)
	{
		this.action=action;
	}
	
	/**
	 * Gets the action name.
	 *
	 * @return the action name
	 */
	public String getAction() {
		return action;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Action)) return false;
	    return this.action.equalsIgnoreCase(((Action)o).getAction());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return action;
	}
	
	/**
	 * Return action in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		return "<action>"+action+"</action>";
	}
}
