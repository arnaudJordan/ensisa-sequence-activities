package sequence.ui.utilities.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Class TimeIndicatorDrawer.
 */
public class TimeIndicatorDrawer {
	
	/** The TICKWIDTH. */
	private final int TICKWIDTH = 2;
	
	/** The color. */
	private final Color color;

	/**
	 * Instantiates a new time indicator drawer.
	 */
	public TimeIndicatorDrawer() {
		color = Color.BLACK;
	}

	/**
	 * Instantiates a new time indicator drawer.
	 *
	 * @param color the color
	 */
	public TimeIndicatorDrawer(final Color color) {
		this.color = color;
	}

	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param width the width
	 * @param height the height
	 */
	public void Draw(final Graphics2D g, final int width, final int height) {
		Draw(g, width, height, color);
	}

	/**
	 * Draw.
	 *
	 * @param g the g
	 * @param width the width
	 * @param height the height
	 * @param color the color
	 */
	public void Draw(final Graphics2D g, final int width, final int height,
			final Color color) {
		g.setColor(color);
		g.fillRect(0, 0, TICKWIDTH, height);
		g.fillRect(0, 0, width, TICKWIDTH);
		g.fillRect(width - TICKWIDTH, 0, TICKWIDTH, height);
	}
}
