package sequence.ui.component.sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import sequence.model.Activity;
import sequence.model.Sequence;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityView;

public class SequenceRenderer extends DefaultRenderer implements Renderer {
	
	public SequenceRenderer(View view) {
		super(view);
	}

	public void renderView(Graphics2D g) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); 
        g.setRenderingHints(rh);
        renderSequence(g);
	}
	
	private void renderSequence(Graphics2D g) {
		getView().setLayout(new FlowLayout());
		for(Activity current : ((Sequence)getView().getModel()).getActivities()) {
			g.setColor(Color.blue);
			ActivityView f = new ActivityView(current);
			getView().add(f);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 50);
	}
}
