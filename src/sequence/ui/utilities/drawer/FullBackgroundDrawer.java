package sequence.ui.utilities.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The Class FullBackgroundDrawer.
 */
public class FullBackgroundDrawer extends BackgroundDrawer {

	/* (non-Javadoc)
	 * @see sequence.ui.utilities.drawer.BackgroundDrawer#draw(java.awt.Graphics2D, int, int, java.awt.Color)
	 */
	@Override
	public void draw(final Graphics2D g, final int width, final int height,
			final Color color) {
		g.setColor(color);
		g.fillRect(0, 0, width, height);
	}
}
