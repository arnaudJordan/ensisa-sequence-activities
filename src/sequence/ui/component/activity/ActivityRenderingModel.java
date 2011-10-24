package sequence.ui.component.activity;

import java.awt.Color;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;

public class ActivityRenderingModel extends DefaultModel implements RenderingModel {

	private static final Color DEFAULT_COLOR = Color.BLACK;
	private static final int DEFAULT_HEIGHT = 10;
	private static final float DEFAULT_SCALE = 1;
	
	private Color color;
	private int height;
	private float scale;
	
	public ActivityRenderingModel() {
		this(DEFAULT_COLOR, DEFAULT_HEIGHT, DEFAULT_SCALE);
	}
	
	public ActivityRenderingModel(Color color) {
		this(color, DEFAULT_HEIGHT, DEFAULT_SCALE);
	}
	
	public ActivityRenderingModel(Color color, int height, float scale) {
		setColor(color);
		setHeight(height);
		setScale(scale);
	}
	
	public void setColor(Color color) {
		if(this.color == color) return;
		this.color = color;
		this.modelChange();
	}

	public Color getColor() {
		return color;
	}

	public void setHeight(int height) {
		if(this.height == height) return;
		this.height = height;
		this.modelChange();
	}
	
	public int getHeight() {
		return height;
	}

	public void setScale(float scale) {
		if(this.scale == scale) return;
		this.scale = scale;
		this.modelChange();
	}

	public float getScale() {
		return scale;
	}
}
