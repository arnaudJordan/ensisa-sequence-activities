package sequence.model;

public class Name {
	private final String name;

	public Name(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Name [name=" + name + "]";
	}

	public String toXML() {
		return "<name>"+name+"</name>";
	}
}
