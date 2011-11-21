package sequence.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

public class TimeLayout implements LayoutManager {
	private static final int VGAP = 10;
	private static final int MARGIN = 10;

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

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		int currentHeight = 0;
		int parentWidth = parent.getWidth();
		int initTime = 0;
		if (parent.getComponentCount() > 1)
			initTime = ((Timeable) parent.getComponent(0)).getStartTime();

		List<Integer> levelsLastPosition = new ArrayList<Integer>();
		levelsLastPosition.add(0);
		for (int i=0 ; i < parent.getComponentCount() ; i++) {
			Component c = parent.getComponent(i);
			if (c.isVisible()) {
				float scale = ((Scaleable) c).getHScale();
				Timeable currentElement = (Timeable) parent.getComponent(i);
				int currentStopTime = (int) ((currentElement.getStopTime() - initTime) * scale);
				if (currentStopTime > parentWidth) {
					initTime = currentElement.getStartTime();
					currentHeight += (c.getHeight() + VGAP)
							* levelsLastPosition.size() + 2 * VGAP;
					levelsLastPosition = new ArrayList<Integer>();
					levelsLastPosition.add(0);
				}

				boolean drawed = false;
				for (int j = 0; j < levelsLastPosition.size(); j++) {
					int lastPosition = (Integer) levelsLastPosition.get(j);
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
		currentHeight += 2 * parent.getComponent(0).getHeight() + VGAP + MARGIN;
		return new Dimension(parentWidth, currentHeight);
	}

	@Override
	public void layoutContainer(Container parent) {
		int currentHeight = 0;
		int parentWidth = parent.getWidth();

		int initTime = 0;
		if (parent.getComponentCount() > 1)
			initTime = ((Timeable) parent.getComponent(0)).getStartTime();

		List<Integer> levelsLastPosition = new ArrayList<Integer>();
		levelsLastPosition.add(0);
		for (int i=0; i < parent.getComponentCount(); i++) {
			Component c = parent.getComponent(i);
			if (c.isVisible()) {
				float scale = ((Scaleable) c).getHScale();
				Timeable currentElement = (Timeable) parent.getComponent(i);
				int currentTime = (int) ((currentElement.getStartTime() - initTime) * scale);
				int currentStopTime = (int) ((currentElement.getStopTime() - initTime) * scale);
				if (currentStopTime > parentWidth) {
					initTime = currentElement.getStartTime();
					currentTime = 0;
					currentHeight += (c.getHeight() + VGAP)
							* levelsLastPosition.size() + 2 * VGAP;
					levelsLastPosition = new ArrayList<Integer>();
					levelsLastPosition.add(0);
				}

				boolean drawed = false;
				for (int j = 0 ; j < levelsLastPosition.size() ; j++) {
					int lastPosition = (Integer) levelsLastPosition.get(j);
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
