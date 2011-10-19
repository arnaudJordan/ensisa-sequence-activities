package sequence.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import sequence.model.Activity;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;

public class TimeLayout implements LayoutManager {
	private static final int vgap=5;

	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// TODO Auto-generated method stub
		
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
		if(parent.getComponentCount()>1)
			initTime = (int) (((Activity) ((ActivityView)
					parent.getComponent(0)).getModel()).getActivitytime().getStartTime());
		
		List<Integer> levelsLastPosition = new ArrayList<Integer>();
		levelsLastPosition.add(0);
		for(int i=0; i<201;i++)
		{
			Component c = parent.getComponent(i);
			if(c.isVisible())
			{
				float scale = ((ActivityRenderingModel)((View) c).getRenderingModel()).getScale();
				Activity currentActivity = (Activity) ((ActivityView) parent.getComponent(i)).getModel();
				int currentStopTime = (int) ((currentActivity.getActivitytime().getStopTime()-initTime)* scale);
				if(currentStopTime>parentWidth)
				{
					initTime = currentActivity.getActivitytime().getStartTime();
					currentHeight+=(c.getHeight()+vgap)*levelsLastPosition.size() + 2 * vgap;
					levelsLastPosition = new ArrayList<Integer>();
					levelsLastPosition.add(0);
				}
				
				boolean drawed = false;
				for(int j=0; j<levelsLastPosition.size(); j++)
				{
					int lastPosition = (Integer) levelsLastPosition.get(j);
					if(lastPosition<=currentActivity.getActivitytime().getStartTime())
					{
						levelsLastPosition.set(j, currentActivity.getActivitytime().getStopTime());
						drawed=true;
						break;
					}
				}
				if(!drawed)
				{
					levelsLastPosition.add(currentActivity.getActivitytime().getStopTime());
				}
			}
		}
		currentHeight+=parent.getComponent(0).getHeight()+vgap;
		return new Dimension(parentWidth, currentHeight);
	}

	@Override
	public void layoutContainer(Container parent) {
		int currentHeight = 0;
		int parentWidth = parent.getWidth();
		
		int initTime = 0;
		if(parent.getComponentCount()>1)
			initTime = (int) (((Activity) ((ActivityView)
					parent.getComponent(0)).getModel()).getActivitytime().getStartTime());
		
		List<Integer> levelsLastPosition = new ArrayList<Integer>();
		levelsLastPosition.add(0);
		for(int i=0; i<201;i++)
		{
			Component c = parent.getComponent(i);
			if(c.isVisible())
			{
				float scale = ((ActivityRenderingModel)((View) c).getRenderingModel()).getScale();
				Activity currentActivity = (Activity) ((ActivityView) parent.getComponent(i)).getModel();
				int currentTime = (int) ((currentActivity.getActivitytime().getStartTime()-initTime)*scale);
				int currentStopTime = (int) ((currentActivity.getActivitytime().getStopTime()-initTime)* scale);
				if(currentStopTime>parentWidth)
				{
					initTime = currentActivity.getActivitytime().getStartTime();
					currentTime=0;
					currentHeight+=(c.getHeight()+vgap)*levelsLastPosition.size() + 2 * vgap;
					levelsLastPosition = new ArrayList<Integer>();
					levelsLastPosition.add(0);
				}
				
				boolean drawed = false;
				for(int j=0; j<levelsLastPosition.size(); j++)
				{
					int lastPosition = (Integer) levelsLastPosition.get(j);
					if(lastPosition<=currentActivity.getActivitytime().getStartTime())
					{
						c.setBounds(currentTime, currentHeight+(c.getHeight()+vgap)*j, c.getPreferredSize().width, c.getPreferredSize().height);
						levelsLastPosition.set(j, currentActivity.getActivitytime().getStopTime());
						drawed=true;
						break;
					}
				}
				if(!drawed)
				{
					c.setBounds(currentTime, currentHeight+(c.getHeight()+vgap)*(levelsLastPosition.size()), c.getPreferredSize().width, c.getPreferredSize().height);
					levelsLastPosition.add(currentActivity.getActivitytime().getStopTime());
				}
			}
		}
		parent.repaint();
	}
}
