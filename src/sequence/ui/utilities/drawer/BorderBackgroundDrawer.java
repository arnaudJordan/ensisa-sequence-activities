package sequence.ui.utilities.drawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BorderBackgroundDrawer extends BackgroundDrawer {
	private static final Color BORDER_COLOR = Color.WHITE;

	private BackgroundDrawer backgroundDrawer;

	public BorderBackgroundDrawer(final BackgroundDrawer backgroundDrawer) {
		this.backgroundDrawer = backgroundDrawer;
	}

	@Override
	public void draw(final Graphics2D g, final int width, final int height,
			final Color color) {
		backgroundDrawer.draw(g, width, height, color);
		g.setColor(BORDER_COLOR);
		g.setStroke(new BasicStroke(4));
		g.drawLine(0, 0, width, 0);
		g.drawLine(0, height, width, height);
	}

	public BackgroundDrawer getBackgroundDrawer() {
		return backgroundDrawer;
	}

	public void setBackgroundDrawer(final BackgroundDrawer backgroundDrawer) {
		this.backgroundDrawer = backgroundDrawer;
	}
}
