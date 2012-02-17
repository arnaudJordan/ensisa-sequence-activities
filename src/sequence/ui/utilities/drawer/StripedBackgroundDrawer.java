package sequence.ui.utilities.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

public class StripedBackgroundDrawer extends BackgroundDrawer {

	@Override
	public void draw(final Graphics2D g, final int width, final int height,
			final Color color) {
		final Color rc = new Color(color.getRed(), 0, 0, color.getAlpha());
		g.setColor(rc);
		g.fillRect(0, 0, width, height / 3);
		final Color gc = new Color(0, color.getGreen(), 0, color.getAlpha());
		g.setColor(gc);
		g.fillRect(0, height / 3, width, height / 3);
		final Color bc = new Color(0, 0, color.getBlue(), color.getAlpha());
		g.setColor(bc);
		g.fillRect(0, 2 * height / 3, width, height / 3);
	}
}
