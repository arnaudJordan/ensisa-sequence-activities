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
	
	@Override
	public String toString() {
		return "Action [action=" + action + "]";
	}
	
}
