package sequence.ui.utilities.drawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BorderBackgroundDrawer extends BackgroundDrawer {
	
	private static final Color BORDER_COLOR = Color.black;

	@Override
	public void Draw(Graphics2D g, int width, int height, Color color)
	{
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.setColor(BORDER_COLOR);
		g.setStroke(new BasicStroke(4));
		g.drawLine(0, 0, width, 0);
		g.drawLine(0, height, width, height);
	}

}
