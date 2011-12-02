package sequence.ui.component.activity;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.activity.Activity;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.utilities.BackgroundDrawer;

public class ActivityRenderer extends DefaultRenderer implements Renderer {
	protected BackgroundDrawer bd;
	
	public ActivityRenderer(View view) {
		super(view);
		this.bd=new BackgroundDrawer();
	}

	public void renderView(Graphics2D g) {
		super.renderView(g);
        renderActivity(g);
	}
	
	private void renderActivity(Graphics2D g) {
		renderBackground(g);
	}

	private void renderBackground(Graphics2D g) {
		Activity activity = (Activity) ((ActivityView)getView()).getModel();
		if(activity != null)
			bd.Draw(g, (int)(getView().getSize().getWidth()), (int)(getView().getSize().getHeight()), ((ActivityRenderingModel)getView().getRenderingModel()).getColor());
	}
	
	public void setBackgroundDrawer(BackgroundDrawer bd)
	{
		this.bd=bd;
		getView().repaint();
	}
	public BackgroundDrawer getBackgroundDrawer() {
		return this.bd;
	}
	public Dimension getPreferredSize() {
		Activity activity = (Activity) ((ActivityView)getView()).getModel();
		ActivityRenderingModel renderingModel = ((ActivityRenderingModel)getView().getRenderingModel());
		if(activity != null && renderingModel != null)
			return new Dimension((int)(activity.getActivitytime().getDuration() * renderingModel.getHScale()), (int)(((ActivityRenderingModel)getView().getRenderingModel()).getHeight() * renderingModel.getVScale()));
		return super.getPreferredSize();
	}

	
}
