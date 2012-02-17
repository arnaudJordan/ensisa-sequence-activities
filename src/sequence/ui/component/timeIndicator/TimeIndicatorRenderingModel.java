package sequence.ui.component.timeIndicator;

import java.awt.Color;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;
import sequence.utilities.ColorFactory;

public class TimeIndicatorRenderingModel extends DefaultModel implements
		RenderingModel {

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
		this(DEFAULT_COLOR, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT,
				DEFAULT_HSCALE, DEFAULT_VSCALE);
	}

	public TimeIndicatorRenderingModel(final Color color) {
		this(color, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, DEFAULT_HSCALE,
				DEFAULT_VSCALE);
	}

	public TimeIndicatorRenderingModel(final Color color, final float Hscale) {
		this(color, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, Hscale,
				DEFAULT_VSCALE);
	}

	public TimeIndicatorRenderingModel(final Color color,
			final int transparencyAlpha, final int height, final float Hscale,
			final float Vscale) {
		this.color = color;
		this.transparencyAlpha = transparencyAlpha;
		this.height = height;
		this.Hscale = Hscale;
		this.Vscale = Vscale;
	}

	public void setColor(final Color color) {
		if (this.color == color)
			return;
		this.color = color;
		modelChange();
	}

	public Color getColor() {
		return color;
	}

	public void setTransparencyAlpha(final int transparencyAlpha) {
		if (this.transparencyAlpha == transparencyAlpha)
			return;
		this.transparencyAlpha = transparencyAlpha;
		modelChange();
	}

	public void setTransparent() {
		setColor(ColorFactory.setAlpha(color, transparencyAlpha));
	}

	public void setOpaque() {
		setColor(ColorFactory.setAlpha(color, 255));
	}

	public int getTransparencyAlpha() {
		return transparencyAlpha;
	}

	public void setHeight(final int height) {
		if (this.height == height)
			return;
		this.height = height;
		modelChange();
	}

	public int getHeight() {
		return height;
	}

	public void setHScale(final float Hscale) {
		if (this.Hscale == Hscale)
			return;
		this.Hscale = Hscale;
		modelChange();
	}

	public float getHScale() {
		return Hscale;
	}

	public void setVScale(final float Vscale) {
		if (this.Vscale == Vscale)
			return;
		this.Vscale = Vscale;
		modelChange();
	}

	public float getVScale() {
		return Vscale;
	}

	public void setScale(final float scale) {
		if (Hscale == scale && Vscale == scale)
			return;
		Hscale = scale;
		Vscale = scale;
		modelChange();
	}
}
