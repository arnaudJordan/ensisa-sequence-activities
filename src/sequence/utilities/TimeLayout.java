package sequence.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import sequence.model.Activity;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;

public class TimeLayout implements LayoutManager {
	private static final int vgap=10;
	private static final int hgap=5;

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
		int nextHeight = 0;
		int currentWidth = 0;
		int parentWidth = parent.getWidth();
		//System.out.println(parentWidth);
		int initTime = 0;
		
		if(parent.getComponentCount()>1)
			initTime = ((Activity) ((ActivityView) parent.getComponent(0)).getModel()).getActivitytime().getStartTime();
		for(int i=0; i<parent.getComponentCount();i++)
		{
			Component c = parent.getComponent(i);
			if(c.isVisible())
			{
				float scale = ((ActivityRenderingModel)((View) c).getRenderingModel()).getScale();
				Activity currentActivity = (Activity) ((ActivityView) parent.getComponent(i)).getModel();
				int currentTime = (int) ((currentActivity.getActivitytime().getStartTime()-initTime)*scale);
				if(currentTime>=parentWidth)
				{
					initTime=currentTime+initTime;
					currentTime=0;
					currentHeight+=c.getHeight() + vgap + nextHeight;
					nextHeight=0;
				}
				
				if(i>0)
				{
					Activity lastActivity = (Activity) ((ActivityView) parent.getComponent(i-1)).getModel();
					if(currentActivity.getActivitytime().getStartTime()<lastActivity.getActivitytime().getStopTime())
					{
						nextHeight=c.getHeight()+vgap/2;
						continue;
					}				
				}
				currentWidth+=c.getWidth() + hgap;
			}
		}
		return new Dimension(parentWidth, currentHeight+700);
	}

	@Override
	public void layoutContainer(Container parent) {
		int currentHeight = 0;
		int nextHeight = 0;
		int parentWidth = parent.getWidth();
		
		int initTime = 0;
		Graphics g = parent.getGraphics();
		if(parent.getComponentCount()>1)
			initTime = ((Activity) ((ActivityView) parent.getComponent(0)).getModel()).getActivitytime().getStartTime();
		for(int i=0; i<parent.getComponentCount();i++)
		{
			Component c = parent.getComponent(i);
			if(c.isVisible())
			{
				float scale = ((ActivityRenderingModel)((View) c).getRenderingModel()).getScale();
				Activity currentActivity = (Activity) ((ActivityView) parent.getComponent(i)).getModel();
				int currentTime = (int) ((currentActivity.getActivitytime().getStartTime()-initTime)*scale);
				if(currentTime+c.getWidth()>=parentWidth)
				{
					initTime+=currentTime;
					currentTime=0;
					g.drawLine(0, currentHeight-vgap/2, parentWidth, currentHeight-vgap/2);
					currentHeight+=c.getHeight() + vgap + nextHeight;
					nextHeight=0;
				}
				
				if(i>0)
				{
					Activity lastActivity = (Activity) ((ActivityView) parent.getComponent(i-1)).getModel();
					if(currentActivity.getActivitytime().getStartTime()<lastActivity.getActivitytime().getStopTime())
					{
						nextHeight=c.getHeight()+vgap/2;
						c.setBounds(currentTime, currentHeight+nextHeight, c.getPreferredSize().width, c.getPreferredSize().height);
						continue;
					}				
				}
				c.setBounds(currentTime, currentHeight, c.getPreferredSize().width, c.getPreferredSize().height);
				System.out.println("CurrentWidth : " + currentTime);
				System.out.println("CurrentHeight : " + currentHeight);
			}
		}
		parent.repaint();
	}
}
