package sequence.ui.component.activity;

import java.awt.Color;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;
import sequence.utilities.ColorFactory;

public class ActivityRenderingModel extends DefaultModel implements RenderingModel {

	private static final Color DEFAULT_COLOR = Color.BLACK;
	private static final int DEFAULT_TRANSPARENCY_ALPHA = 50;
	private static final int DEFAULT_HEIGHT = 10;
	private static final float DEFAULT_SCALE = 1;
	
	private Color color;
	private int transparencyAlpha;
	private int height;
	private float scale;
	
	public ActivityRenderingModel() {
		this(DEFAULT_COLOR, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, DEFAULT_SCALE);
	}
	
	public ActivityRenderingModel(Color color) {
		this(color, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, DEFAULT_SCALE);
	}
	
	public ActivityRenderingModel(Color color, int transparencyAlpha, int height, float scale) {
		setColor(color);
		setTransparencyAlpha(transparencyAlpha);
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

	public void setTransparencyAlpha(int transparencyAlpha) {
		if(this.transparencyAlpha == transparencyAlpha) return;
		this.transparencyAlpha = transparencyAlpha;
		this.modelChange();
	}
	
	public void setTransparent() {
		setColor(ColorFactory.setAlpha(this.color, this.transparencyAlpha));
	}
	
	public void setOpaque() {
		setColor(ColorFactory.setAlpha(this.color, 255));
	}

	public int getTransparencyAlpha() {
		return transparencyAlpha;
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
