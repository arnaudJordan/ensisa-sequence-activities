package sequence.ui.component.activity;

import java.awt.Color;

import sequence.mvc.RenderingModel;

public class ActivityRenderingModel implements RenderingModel {

	private static final Color DEFAULT_COLOR = Color.BLACK;
	private static final int DEFAULT_HEIGHT = 10;
	
	private Color color;
	private int height;
	
	public ActivityRenderingModel() {
		this(DEFAULT_COLOR, DEFAULT_HEIGHT);
	}
	
	public ActivityRenderingModel(Color color) {
		this(color, DEFAULT_HEIGHT);
	}
	
	public ActivityRenderingModel(Color color, int height) {
		setColor(color);
		setHeight(height);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}
}
