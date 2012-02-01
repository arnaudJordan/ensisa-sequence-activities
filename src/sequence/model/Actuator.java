package sequence.model;

import sequence.model.activity.BodyPart;

/**
 * The Actuator model.
 */
public class Actuator {
	
	/** The position. */
	private Position position;
	
	/** The usedbodypart. */
	private BodyPart usedbodypart;
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Gets the usedbodypart.
	 *
	 * @return the usedbodypart
	 */
	public BodyPart getUsedbodypart() {
		return usedbodypart;
	}

	/**
	 * Sets the usedbodypart.
	 *
	 * @param usedbodypart the new usedbodypart
	 */
	public void setUsedbodypart(BodyPart usedbodypart) {
		this.usedbodypart = usedbodypart;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Actuator)) return false;
		return (this.position.equals(((Actuator)o).position) && this.usedbodypart.equals(((Actuator)o).usedbodypart));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Actuator [position=" + position + ", usedbodypart="
				+ usedbodypart + "]";
	}

	/**
	 * Return actuator in XML format.
	 *
	 * @return the XML string
	 */
	public String toXML() {
		final String NEW_LINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder("<actuator>"+NEW_LINE);
		if(position!=null)
			sb.append("\t"+position.toXML()+NEW_LINE);
		if(usedbodypart!=null)
			sb.append("\t"+usedbodypart.toXML()+NEW_LINE);
		sb.append("</actuator>");
		return sb.toString();
	}
}
