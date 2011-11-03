package sequence.ui.component.activity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.activity.Activity;
import sequence.mvc.View;

public class ActivityContractedRenderer extends ActivityRenderer {

	public ActivityContractedRenderer(View view) {
		super(view);
	}
	
	public void renderView(Graphics2D g) {
        renderActivity(g);
	}
	
	private void renderActivity(Graphics2D g) {
		renderBackground(g);
	}
	
	private void renderBackground(Graphics2D g) {
		Activity activity = (Activity) ((ActivityView)getView()).getModel();
		g.setColor(((ActivityRenderingModel)getView().getRenderingModel()).getColor());
		if(activity == null)
			return;
		
		ActivityRenderingModel renderingModel = ((ActivityRenderingModel)getView().getRenderingModel());
		int parentWidth = getView().getParent().getWidth();
		int activityWidth = (int) (activity.getActivitytime().getDuration() * renderingModel.getScale());
		g.fillRect(0, 0, activityWidth, (int) (renderingModel.getHeight() * renderingModel.getScale()));
		if(parentWidth<activityWidth)
		{
			g.drawLine(parentWidth/2 -5 , -5, parentWidth/2 +5, (int) getView().getSize().getHeight()+5);
			drawCutMark(g, parentWidth/2);
		}
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(activity.getId()), (int) getView().getSize().getWidth()/2, (int) getView().getSize().getHeight()/2);
	}
	private void drawCutMark(Graphics2D g, int middle)
	{
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.WHITE);
		final int gap = 5;
		g2.drawLine(middle - 2*gap , 0, middle, (int) getView().getSize().getHeight()+5);
		g2.drawLine(middle , 0, middle + 2*gap, (int) getView().getSize().getHeight()+5);
	}
	
	public Dimension getPreferredSize() {
		Activity activity = (Activity) ((ActivityView)getView()).getModel();
		ActivityRenderingModel renderingModel = ((ActivityRenderingModel)getView().getRenderingModel());
		if(activity != null && renderingModel != null)
		{
			int parentWidth = getView().getParent().getWidth();
			int activityWidth = (int) (activity.getActivitytime().getDuration() * renderingModel.getScale());
			if(parentWidth<activityWidth)
				return new Dimension(parentWidth, (int) (renderingModel.getHeight() * renderingModel.getScale()));
			else
				return new Dimension(activityWidth, (int) (renderingModel.getHeight() * renderingModel.getScale()));
		}
			
		return super.getPreferredSize();
	}

}
