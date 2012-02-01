package sequence.ui.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class ListLayout implements LayoutManager {
	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	@Override
	public void removeLayoutComponent(Component comp) {
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return minimumLayoutSize(parent);
	}

	public Dimension minimumLayoutSize(Container parent) {
		return new Dimension(parent.getWidth(), parent.getComponentCount()*parent.getComponent(0).getHeight());
	}

	@Override
	public void layoutContainer(Container parent) {
		int currentHeight=0;
		for(int i=0; i < parent.getComponentCount(); i++)
		{
			Component c = parent.getComponent(i);
			if (c.isVisible())
			{
				c.setBounds(0,currentHeight, c.getPreferredSize().width, c.getPreferredSize().height);
				currentHeight+=c.getHeight();
			}
		}
	}
}
