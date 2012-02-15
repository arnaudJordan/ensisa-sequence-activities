package sequence.ui.utilities;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ComponentLocation {

	public static void setLocation(final Component parent, final Component child) {
		int x = parent.getLocation().x + parent.getWidth() / 2
				- child.getPreferredSize().width / 2;
		int y = parent.getLocation().y + parent.getHeight() / 2
				- child.getPreferredSize().height / 2;
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		if (x < 0)
			x = 0;
		else if (x + child.getPreferredSize().width > screenSize.width)
			x = screenSize.width - child.getPreferredSize().width;
		if (y < 0)
			y = 0;
		else if (y + child.getPreferredSize().height > screenSize.height)
			y = screenSize.height - child.getPreferredSize().height;
		child.setLocation(x, y);
	}

	public static void setLocationOnCenterScreen(final Component c) {
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		System.out.println(c.getPreferredSize().width);
		c.setLocation(screenSize.width / 2 - c.getPreferredSize().width / 2,
				screenSize.height / 2 - c.getPreferredSize().height / 2);
	}
}
