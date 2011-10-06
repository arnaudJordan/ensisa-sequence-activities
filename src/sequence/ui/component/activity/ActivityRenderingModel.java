package sequence.ui.component.activity;

import java.awt.Color;

import sequence.mvc.RenderingModel;

public class ActivityRenderingModel implements RenderingModel {

	private static final Color DEFAULT_COLOR = Color.BLACK;
	
	private Color color;
	
	public ActivityRenderingModel() {
		this(DEFAULT_COLOR);
	}
	
	public ActivityRenderingModel(Color color) {
		setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
