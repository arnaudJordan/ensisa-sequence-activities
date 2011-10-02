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
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); 
        g.setRenderingHints(rh);
        renderActivity(g);
	}
	
	private void renderActivity(Graphics2D g) {
		renderBackground(g);
	}

	private void renderBackground(Graphics2D g) {
		Activity activity = (Activity) ((ActivityView)getView()).getModel();
		if(activity != null)
			g.fillRect(0, 0, activity.getActivitytime().getDuration()*3, 50);
	}
	
	public Dimension getPreferredSize() {
		Activity activity = (Activity) ((ActivityView)getView()).getModel();
		if(activity != null)
			return new Dimension(activity.getActivitytime().getDuration(), 50);
		return new Dimension(200, 50);
	}
}
