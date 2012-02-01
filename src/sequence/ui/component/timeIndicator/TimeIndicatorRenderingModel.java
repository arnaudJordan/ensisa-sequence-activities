package sequence.ui.component.timeIndicator;

import java.awt.Color;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;
import sequence.utilities.ColorFactory;

public class TimeIndicatorRenderingModel extends DefaultModel implements RenderingModel {

	private static final Color DEFAULT_COLOR = Color.BLACK;
	private static final int DEFAULT_TRANSPARENCY_ALPHA = 50;
	private static final int DEFAULT_HEIGHT = 10;
	private static final float DEFAULT_HSCALE = 1;
	private static final float DEFAULT_VSCALE = 1;
	
	private Color color;
	private int transparencyAlpha;
	private int height;
	private float Hscale, Vscale;
	
	public TimeIndicatorRenderingModel() {
		this(DEFAULT_COLOR, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, DEFAULT_HSCALE, DEFAULT_VSCALE);
	}
	
	public TimeIndicatorRenderingModel(Color color) {
		this(color, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, DEFAULT_HSCALE, DEFAULT_VSCALE);
	}
	
	public TimeIndicatorRenderingModel(Color color, float Hscale) {
		this(color, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, Hscale, DEFAULT_VSCALE);
	}
	
	public TimeIndicatorRenderingModel(Color color, int transparencyAlpha, int height, float Hscale, float Vscale) {
		this.color = color;
		this.transparencyAlpha = transparencyAlpha;
		this.height = height;
		this.Hscale = Hscale;
		this.Vscale = Vscale;
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

	public void setHScale(float Hscale) {
		if(this.Hscale == Hscale) return;
		this.Hscale = Hscale;
		this.modelChange();
	}

	public float getHScale() {
		return Hscale;
	}
	
	public void setVScale(float Vscale) {
		if(this.Vscale == Vscale) return;
		this.Vscale = Vscale;
		this.modelChange();
	}

	public float getVScale() {
		return Vscale;
	}
	
	public void setScale(float scale) {
		if(this.Hscale == scale && this.Vscale == scale) return;
		this.Hscale = scale;
		this.Vscale = scale;
		this.modelChange();
	}
}
