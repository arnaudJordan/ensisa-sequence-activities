package sequence.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.Border;

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
		Insets parentInsets = ((JComponent) parent).getBorder().getBorderInsets(parent);
		int HMargin = parentInsets.left + parentInsets.right;
		int currentHeight = parentInsets.top;
		for(int i=0; i < parent.getComponentCount(); i++)
		{
			Component c = parent.getComponent(i);
			if (c.isVisible())
			{
				if(c instanceof JButton)
					c.setBounds(parent.getWidth()-c.getWidth(), parentInsets.top/2, c.getPreferredSize().width, c.getPreferredSize().height);
				else if(c instanceof JLabel)
					c.setBounds(HMargin, parentInsets.top, c.getPreferredSize().width, c.getPreferredSize().height);
				else
					c.setBounds(HMargin, currentHeight, c.getPreferredSize().width, c.getPreferredSize().height);
				try {
					currentHeight+=Math.max(c.getPreferredSize().height, parent.getComponent(i-1).getPreferredSize().height);
				}
				catch(Exception e) {}
			}
		}
	}
}
