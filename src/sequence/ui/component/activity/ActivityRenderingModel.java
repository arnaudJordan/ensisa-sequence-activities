package sequence.ui.component.activity;

import java.awt.Color;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;

public class ActivityRenderingModel extends DefaultModel implements RenderingModel {

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
		if(this.color == color) return;
		this.color = color;
		this.modelChange();
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		if(this.height == height) return;
		this.height = height;
		this.modelChange();
	}
}
