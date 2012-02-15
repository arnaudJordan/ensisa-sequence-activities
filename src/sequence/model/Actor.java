package sequence.model;

/**
 * The Actor model.
 */
public class Actor {

	/** The actor. */
	private final String actor;

	/**
	 * Instantiates a new actor.
	 * 
	 * @param actor
	 *            the actor
	 */
	public Actor(final String actor) {
		this.actor = actor;
	}

	/**
	 * Gets the actor.
	 * 
	 * @return the actor
	 */
	public String getActor() {
		return actor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Actor [actor=" + actor + "]";
	}

	/**
	 * Return actor in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<actor>" + actor + "</actor>";
	}
}
