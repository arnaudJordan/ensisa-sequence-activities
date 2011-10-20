package sequence.model;

public class Actor {
	private final String actor;

	public Actor(String actor) {
		this.actor = actor;
	}

	public String getActor() {
		return actor;
	}

	@Override
	public String toString() {
		return "Actor [actor=" + actor + "]";
	}
	

}
