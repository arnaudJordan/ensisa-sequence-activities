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
		StringBuilder sb = new StringBuilder("<actuator>\n");
		if(position!=null)
			sb.append("\t"+position.toXML()+"\n");
		if(usedbodypart!=null)
			sb.append("\t"+usedbodypart.toXML()+"\n");
		sb.append("</actuator>");
		return sb.toString();
	}
}
