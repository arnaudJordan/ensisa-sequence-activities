package sequence.ui.utilities.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Class BackgroundDrawer.
 */
public abstract class BackgroundDrawer {
	
	/** The color. */
	protected Color color;

	/**
	 * Instantiates a new background drawer.
	 */
	public BackgroundDrawer() {
		color = Color.BLACK;
	}

	/**
	 * Instantiates a new background drawer.
	 *
	 * @param color the color
	 */
	public BackgroundDrawer(final Color color) {
		this.color = color;
	}

	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param width the width
	 * @param height the height
	 */
	public void draw(final Graphics2D g, final int width, final int height) {
		draw(g, width, height, color);
	}

	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param width the width
	 * @param height the height
	 * @param color the color
	 */
	public abstract void draw(Graphics2D g, int width, int height, Color color);
}
