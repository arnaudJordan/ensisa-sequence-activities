package sequence.ui.utilities.drawer;

import java.awt.Color;
import java.awt.Graphics2D;

public class FullBackgroundDrawer extends BackgroundDrawer {
	
	@Override
	public void draw(Graphics2D g, int width, int height, Color color) {
		g.setColor(color);
		g.fillRect(0, 0, width, height);
	}
}
