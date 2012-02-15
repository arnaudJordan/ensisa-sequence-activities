package sequence.ui.utilities.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

public class TimeIndicatorDrawer {
	private final int TICKWIDTH = 2;
	private final Color color;

	public TimeIndicatorDrawer() {
		color = Color.BLACK;
	}

	public TimeIndicatorDrawer(final Color color) {
		this.color = color;
	}

	public void Draw(final Graphics2D g, final int width, final int height) {
		Draw(g, width, height, color);
	}

	public void Draw(final Graphics2D g, final int width, final int height,
			final Color color) {
		g.setColor(color);
		g.fillRect(0, 0, TICKWIDTH, height);
		g.fillRect(0, 0, width, TICKWIDTH);
		g.fillRect(width - TICKWIDTH, 0, TICKWIDTH, height);
	}
}
