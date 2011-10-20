package sequence.model;

public class Color {
	private final int color;

	public Color(int color) {
		super();
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Color [color=" + color + "]";
	}
}
