package sequence.ui.utilities.drawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Class BorderBackgroundDrawer.
 */
public class BorderBackgroundDrawer extends BackgroundDrawer {
	
	/** The Constant BORDER_COLOR. */
	private static final Color BORDER_COLOR = Color.WHITE;

	/** The background drawer. */
	private BackgroundDrawer backgroundDrawer;

	/**
	 * Instantiates a new border background drawer.
	 *
	 * @param backgroundDrawer the background drawer
	 */
	public BorderBackgroundDrawer(final BackgroundDrawer backgroundDrawer) {
		this.backgroundDrawer = backgroundDrawer;
	}

	/* (non-Javadoc)
	 * @see sequence.ui.utilities.drawer.BackgroundDrawer#draw(java.awt.Graphics2D, int, int, java.awt.Color)
	 */
	@Override
	public void draw(final Graphics2D g, final int width, final int height,
			final Color color) {
		backgroundDrawer.draw(g, width, height, color);
		g.setColor(BORDER_COLOR);
		g.setStroke(new BasicStroke(4));
		g.drawLine(0, 0, width, 0);
		g.drawLine(0, height, width, height);
	}

	/**
	 * Gets the background drawer.
	 *
	 * @return the background drawer
	 */
	public BackgroundDrawer getBackgroundDrawer() {
		return backgroundDrawer;
	}

	/**
	 * Sets the background drawer.
	 *
	 * @param backgroundDrawer the new background drawer
	 */
	public void setBackgroundDrawer(final BackgroundDrawer backgroundDrawer) {
		this.backgroundDrawer = backgroundDrawer;
	}
}
