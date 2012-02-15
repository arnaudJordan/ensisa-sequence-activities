package sequence.model;

/**
 * The Color model.
 */
public class Color {

	/** The color. */
	private final int color;

	/**
	 * Instantiates a new color.
	 * 
	 * @param color
	 *            the color
	 */
	public Color(final int color) {
		super();
		this.color = color;
	}

	/**
	 * Gets the color.
	 * 
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Color [color=" + color + "]";
	}

	/**
	 * Return color in XML format.
	 * 
	 * @return the XML string
	 */
	public String toXML() {
		return "<color>" + color + "</color>";
	}
}
