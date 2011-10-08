package sequence.ui.component.activity;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import sequence.model.Activity;
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
		if(activity != null)
			return new Dimension(activity.getActivitytime().getDuration(), ((ActivityRenderingModel)getView().getRenderingModel()).getHeight());
		return super.getPreferredSize();
	}
}
