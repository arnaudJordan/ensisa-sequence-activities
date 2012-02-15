package sequence.ui.utilities.drawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BorderBackgroundDrawer extends BackgroundDrawer {
	private static final Color BORDER_COLOR = Color.black;
	
	private BackgroundDrawer backgroundDrawer;

	public BorderBackgroundDrawer(BackgroundDrawer backgroundDrawer) {
		this.backgroundDrawer = backgroundDrawer;
	}
	
	@Override
	public void draw(Graphics2D g, int width, int height, Color color) {
		backgroundDrawer.draw(g, width, height, color);
		g.setColor(BORDER_COLOR);
		g.setStroke(new BasicStroke(4));
		g.drawLine(0, 0, width, 0);
		g.drawLine(0, height, width, height);
	}

	public BackgroundDrawer getBackgroundDrawer() {
		return backgroundDrawer;
	}

	public void setBackgroundDrawer(BackgroundDrawer backgroundDrawer) {
		this.backgroundDrawer = backgroundDrawer;
	}
}
