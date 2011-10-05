package sequence.ui.component.sequence;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import sequence.model.Activity;
import sequence.model.Sequence;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityView;

public class SequenceRenderer extends DefaultRenderer implements Renderer {
	
	public SequenceRenderer(View view) {
		super(view);
		getView().setLayout(new FlowLayout());
		for(Activity current : ((Sequence)getView().getModel()).getActivities()) 
			getView().add(new ActivityView(current));
	}

	public void renderView(Graphics2D g) {
		super.renderView(g);
        renderSequence(g);
	}
	
	private void renderSequence(Graphics2D g) {
		System.out.println(getView().getComponentCount());
	}
	
	public Dimension getPreferredSize() {
		return getView().getLayout().preferredLayoutSize(getView());
	}
}
