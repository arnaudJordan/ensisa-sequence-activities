package sequence.model;

import sequence.model.activity.BodyPart;


public class Actuator {
	private Position position;
	private BodyPart usedbodypart;
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public BodyPart getUsedbodypart() {
		return usedbodypart;
	}

	public void setUsedbodypart(BodyPart usedbodypart) {
		this.usedbodypart = usedbodypart;
	}
	
	@Override
	public String toString() {
		return "Actuator [position=" + position + ", usedbodypart="
				+ usedbodypart + "]";
	}

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
