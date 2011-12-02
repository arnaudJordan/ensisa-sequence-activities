package sequence.utilities;

import java.awt.Color;
import java.awt.Graphics2D;

public class BackgroundDrawer {
	private Color color;
	public BackgroundDrawer() {
		this.color=Color.BLACK;
	}
	public BackgroundDrawer(Color color) {
		this.color=color;
	}
	public void Draw(Graphics2D g, int width, int height)
	{
		Draw(g, width, height, this.color);
	}
	public void Draw(Graphics2D g, int width, int height, Color color)
	{
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		
	}

}
