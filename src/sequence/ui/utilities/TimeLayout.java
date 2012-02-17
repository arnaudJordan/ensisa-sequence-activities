package sequence.ui.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import sequence.utilities.Scaleable;
import sequence.utilities.Timeable;

/**
 * Place elements of a <code>Container</code> in fonction of their time and
 * their scale. Elements must implement <code>Timeable</code> and
 * <code>Scaleable</code>.
 * 
 * @see java.awt.LayoutManager
 * @see Timeable
 * @see Scaleable
 * 
 * @author Arnaud Jordan
 */
public class TimeLayout implements LayoutManager {
	private static final int VGAP = 10;

	@Override
	public void addLayoutComponent(final String name, final Component comp) {
	}

	@Override
	public void removeLayoutComponent(final Component comp) {
	}

	@Override
	public Dimension preferredLayoutSize(final Container parent) {
		return minimumLayoutSize(parent);
	}

	@Override
	public Dimension minimumLayoutSize(final Container parent) {
		// If there are no component size is 0.
		if (parent.getComponentCount() == 0)
			return new Dimension(0, 0);

		int currentHeight = 0;
		final int parentWidth = parent.getWidth();
		int initTime = ((Timeable) parent.getComponent(0)).getStartTime();

		List<Integer> levelsLastPosition = new ArrayList<Integer>();
		levelsLastPosition.add(0);
		for (int i = 0; i < parent.getComponentCount(); i++) {
			final Component c = parent.getComponent(i);
			if (c.isVisible()) {
				final float scale = ((Scaleable) c).getHScale();
				final Timeable currentElement = (Timeable) parent
						.getComponent(i);
				final int currentStopTime = (int) ((currentElement
						.getStopTime() - initTime) * scale);
				if (currentStopTime > parentWidth) {
					initTime = currentElement.getStartTime();
					currentHeight += (c.getHeight() + VGAP)
							* levelsLastPosition.size() + 2 * VGAP;
					levelsLastPosition = new ArrayList<Integer>();
					levelsLastPosition.add(0);
				}

				boolean drawed = false;
				for (int j = 0; j < levelsLastPosition.size(); j++) {
					final int lastPosition = levelsLastPosition.get(j);
					if (lastPosition <= currentElement.getStartTime()) {
						levelsLastPosition.set(j, currentElement.getStopTime());
						drawed = true;
						break;
					}
				}
				if (!drawed) {
					levelsLastPosition.add(currentElement.getStopTime());
				}
			}
		}
		// currentHeight += 2 * parent.getComponent(0).getHeight() + VGAP +
		// MARGIN;
		currentHeight += (parent.getComponent(0).getHeight() + VGAP)
				* levelsLastPosition.size();
		return new Dimension(parentWidth, currentHeight);
	}

	@Override
	public void layoutContainer(final Container parent) {
		if (parent.getComponentCount() == 0)
			return;

		int currentHeight = 0;
		final int parentWidth = parent.getWidth();

		int initTime = ((Timeable) parent.getComponent(0)).getStartTime();

		List<Integer> levelsLastPosition = new ArrayList<Integer>();
		levelsLastPosition.add(0);
		for (int i = 0; i < parent.getComponentCount(); i++) {
			final Component c = parent.getComponent(i);
			if (c.isVisible()) {
				final float scale = ((Scaleable) c).getHScale();
				final Timeable currentElement = (Timeable) parent
						.getComponent(i);
				int currentTime = (int) ((currentElement.getStartTime() - initTime) * scale);
				final int currentStopTime = (int) ((currentElement
						.getStopTime() - initTime) * scale);
				if (currentStopTime > parentWidth) {
					initTime = currentElement.getStartTime();
					currentTime = 0;
					currentHeight += (c.getHeight() + VGAP)
							* levelsLastPosition.size() + 2 * VGAP;
					levelsLastPosition = new ArrayList<Integer>();
					levelsLastPosition.add(0);
				}

				boolean drawed = false;
				for (int j = 0; j < levelsLastPosition.size(); j++) {
					final int lastPosition = levelsLastPosition.get(j);
					if (lastPosition <= currentElement.getStartTime()) {
						c.setBounds(currentTime, currentHeight
								+ (c.getHeight() + VGAP) * j,
								c.getPreferredSize().width,
								c.getPreferredSize().height);
						levelsLastPosition.set(j, currentElement.getStopTime());
						drawed = true;
						break;
					}
				}
				if (!drawed) {
					c.setBounds(currentTime,
							currentHeight + (c.getHeight() + VGAP)
									* (levelsLastPosition.size()),
							c.getPreferredSize().width,
							c.getPreferredSize().height);
					levelsLastPosition.add(currentElement.getStopTime());
				}
			}
		}
	}
}
