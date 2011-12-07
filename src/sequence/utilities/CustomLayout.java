package sequence.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;

public class CustomLayout implements LayoutManager {
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
				if(c instanceof JButton)
					c.setBounds(parent.getWidth()-c.getWidth(), parent.getHeight()-c.getHeight(), c.getWidth(), c.getHeight());
				else if(c instanceof JLabel)
					c.setBounds(0, 0, c.getWidth(), c.getHeight());
				else
					c.setBounds(0,currentHeight, c.getPreferredSize().width, c.getPreferredSize().height);
				currentHeight+=c.getPreferredSize().height;
			}
		}
	}
}
