package sequence.ui.component.activity;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.activity.Activity;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;

public class ActivityRenderer extends DefaultRenderer implements Renderer {
	
	public ActivityRenderer(View view) {
		super(view);
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
		g.setColor(((ActivityRenderingModel)getView().getRenderingModel()).getColor());
		if(activity != null)
			g.fillRect(0, 0, (int)(getView().getSize().getWidth()), (int)(getView().getSize().getHeight()));
	}
	
	public Dimension getPreferredSize() {
		Activity activity = (Activity) ((ActivityView)getView()).getModel();
		ActivityRenderingModel renderingModel = ((ActivityRenderingModel)getView().getRenderingModel());
		if(activity != null && renderingModel != null)
			return new Dimension((int)(activity.getActivitytime().getDuration() * renderingModel.getHScale()), (int)(((ActivityRenderingModel)getView().getRenderingModel()).getHeight() * renderingModel.getVScale()));
		return super.getPreferredSize();
	}
}
