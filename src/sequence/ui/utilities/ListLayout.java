package sequence.ui.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * The layout that is used to display components in a vertical list
 */
public class ListLayout implements LayoutManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String,
	 * java.awt.Component)
	 */
	@Override
	public void addLayoutComponent(final String name, final Component comp) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
	 */
	@Override
	public void removeLayoutComponent(final Component comp) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension preferredLayoutSize(final Container parent) {
		return minimumLayoutSize(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension minimumLayoutSize(final Container parent) {
		return new Dimension(parent.getWidth(), parent.getComponentCount()
				* parent.getComponent(0).getHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
	 */
	@Override
	public void layoutContainer(final Container parent) {
		int currentHeight = 0;
		for (int i = 0; i < parent.getComponentCount(); i++) {
			final Component c = parent.getComponent(i);
			if (c.isVisible()) {
				c.setBounds(0, currentHeight, c.getPreferredSize().width,
						c.getPreferredSize().height);
				currentHeight += c.getHeight();
			}
		}
	}
}
