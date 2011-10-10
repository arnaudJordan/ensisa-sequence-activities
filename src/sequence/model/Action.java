package sequence.model;

public class Action {
	private final String action;
	
	public Action(String action)
	{
		this.action=action;
	}
	
	public String getAction() {
		return action;
	}
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Action)) return false;
	    return this.action.equalsIgnoreCase(((Action)o).getAction());
	}
	
	@Override
	public String toString() {
		return "Action [action=" + action + "]";
	}
	
}
