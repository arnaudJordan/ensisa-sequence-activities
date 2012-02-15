package sequence.ui.utilities.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class BackgroundDrawer {
	protected Color color;

	public BackgroundDrawer() {
		color = Color.BLACK;
	}

	public BackgroundDrawer(final Color color) {
		this.color = color;
	}

	public void draw(final Graphics2D g, final int width, final int height) {
		draw(g, width, height, color);
	}

	public abstract void draw(Graphics2D g, int width, int height, Color color);
}
