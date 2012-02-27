package sequence.ui.component.timeIndicator;

import java.awt.Color;

import sequence.mvc.DefaultModel;
import sequence.mvc.RenderingModel;
import sequence.utilities.ColorFactory;

/**
 * The Class TimeIndicatorRenderingModel.
 */
public class TimeIndicatorRenderingModel extends DefaultModel implements
		RenderingModel {

	/** The Constant DEFAULT_COLOR. */
	private static final Color DEFAULT_COLOR = Color.BLACK;
	
	/** The Constant DEFAULT_TRANSPARENCY_ALPHA. */
	private static final int DEFAULT_TRANSPARENCY_ALPHA = 50;
	
	/** The Constant DEFAULT_HEIGHT. */
	private static final int DEFAULT_HEIGHT = 10;
	
	/** The Constant DEFAULT_HSCALE. */
	private static final float DEFAULT_HSCALE = 1;
	
	/** The Constant DEFAULT_VSCALE. */
	private static final float DEFAULT_VSCALE = 1;

	/** The color. */
	private Color color;
	
	/** The transparency alpha. */
	private int transparencyAlpha;
	
	/** The height. */
	private int height;
	
	/** The Vscale. */
	private float Hscale, Vscale;

	/**
	 * Instantiates a new time indicator rendering model.
	 */
	public TimeIndicatorRenderingModel() {
		this(DEFAULT_COLOR, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT,
				DEFAULT_HSCALE, DEFAULT_VSCALE);
	}

	/**
	 * Instantiates a new time indicator rendering model.
	 *
	 * @param color the color
	 */
	public TimeIndicatorRenderingModel(final Color color) {
		this(color, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, DEFAULT_HSCALE,
				DEFAULT_VSCALE);
	}

	/**
	 * Instantiates a new time indicator rendering model.
	 *
	 * @param color the color
	 * @param Hscale the hscale
	 */
	public TimeIndicatorRenderingModel(final Color color, final float Hscale) {
		this(color, DEFAULT_TRANSPARENCY_ALPHA, DEFAULT_HEIGHT, Hscale,
				DEFAULT_VSCALE);
	}

	/**
	 * Instantiates a new time indicator rendering model.
	 *
	 * @param color the color
	 * @param transparencyAlpha the transparency alpha
	 * @param height the height
	 * @param Hscale the hscale
	 * @param Vscale the vscale
	 */
	public TimeIndicatorRenderingModel(final Color color,
			final int transparencyAlpha, final int height, final float Hscale,
			final float Vscale) {
		this.color = color;
		this.transparencyAlpha = transparencyAlpha;
		this.height = height;
		this.Hscale = Hscale;
		this.Vscale = Vscale;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(final Color color) {
		if (this.color == color)
			return;
		this.color = color;
		modelChange();
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the transparency alpha.
	 *
	 * @param transparencyAlpha the new transparency alpha
	 */
	public void setTransparencyAlpha(final int transparencyAlpha) {
		if (this.transparencyAlpha == transparencyAlpha)
			return;
		this.transparencyAlpha = transparencyAlpha;
		modelChange();
	}

	/**
	 * Sets the transparent.
	 */
	public void setTransparent() {
		setColor(ColorFactory.setAlpha(color, transparencyAlpha));
	}

	/**
	 * Sets the opaque.
	 */
	public void setOpaque() {
		setColor(ColorFactory.setAlpha(color, 255));
	}

	/**
	 * Gets the transparency alpha.
	 *
	 * @return the transparency alpha
	 */
	public int getTransparencyAlpha() {
		return transparencyAlpha;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(final int height) {
		if (this.height == height)
			return;
		this.height = height;
		modelChange();
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the h scale.
	 *
	 * @param Hscale the new h scale
	 */
	public void setHScale(final float Hscale) {
		if (this.Hscale == Hscale)
			return;
		this.Hscale = Hscale;
		modelChange();
	}

	/**
	 * Gets the h scale.
	 *
	 * @return the h scale
	 */
	public float getHScale() {
		return Hscale;
	}

	/**
	 * Sets the v scale.
	 *
	 * @param Vscale the new v scale
	 */
	public void setVScale(final float Vscale) {
		if (this.Vscale == Vscale)
			return;
		this.Vscale = Vscale;
		modelChange();
	}

	/**
	 * Gets the v scale.
	 *
	 * @return the v scale
	 */
	public float getVScale() {
		return Vscale;
	}

	/**
	 * Sets the scale.
	 *
	 * @param scale the new scale
	 */
	public void setScale(final float scale) {
		if (Hscale == scale && Vscale == scale)
			return;
		Hscale = scale;
		Vscale = scale;
		modelChange();
	}
}
