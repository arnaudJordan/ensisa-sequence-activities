package sequence.utilities;

import java.awt.Color;
import java.awt.Graphics2D;

public class StripedBackgroundDrawer extends BackgroundDrawer {
	
	@Override
	public void Draw(Graphics2D g, int width, int height, Color color)
	{
		Color rc=new Color(color.getRed(), 0, 0);
		g.setColor(rc);
		g.fillRect(0, 0, width, height/3);
		Color gc=new Color(0, color.getGreen(), 0);
		g.setColor(gc);
		g.fillRect(0, height/3, width, height/3);
		Color bc=new Color(0, 0, color.getBlue());
		g.setColor(bc);
		g.fillRect(0, 2*height/3, width, height/3);
	}

}
