package sequence.ui.utilities.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

public class FullBackgroundDrawer extends BackgroundDrawer {

	@Override
	public void draw(final Graphics2D g, final int width, final int height,
			final Color color) {
		g.setColor(color);
		g.fillRect(0, 0, width, height);
	}
}
