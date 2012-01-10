package sequence.utilities;

import java.awt.Color;
import java.awt.Graphics2D;

public class TimeIndicatorDrawer {
	private final int TICKWIDTH = 5;
	private Color color;
	public TimeIndicatorDrawer() {
		this.color=Color.BLACK;
	}
	public TimeIndicatorDrawer(Color color) {
		this.color=color;
	}
	public void Draw(Graphics2D g, int width, int height)
	{
		Draw(g, width, height, this.color);
	}
	public void Draw(Graphics2D g, int width, int height, Color color)
	{
		g.setColor(color);
		g.fillRect(0, 0, TICKWIDTH, height);
		g.fillRect(0, 0, width, height/2);
		g.fillRect(width-TICKWIDTH, 0, TICKWIDTH, height);
	}
}
