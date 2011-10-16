package sequence.ui.component.sequence;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;

import sequence.model.Activity;
import sequence.model.Sequence;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityController;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.utilities.BetterSizeFlowLayout;
import sequence.utilities.ColorFactory;

public class SequenceRenderer extends DefaultRenderer implements Renderer {
	
	public SequenceRenderer(View view) {
		super(view);
		getView().setLayout(new BetterSizeFlowLayout());
		Sequence sequence = (Sequence)getView().getModel();
		ColorFactory colorFactory = new ColorFactory(sequence);
		for(Activity current : sequence) {
			if(((SequenceRenderingModel)getView().getRenderingModel()).getDurationThreshold() <= current.getActivitytime().getDuration()) {
				ActivityView activityView = new ActivityView(current);
				activityView.setRenderingModel(new ActivityRenderingModel(colorFactory.createColor(current)));
				ActivityController activityController = new ActivityController(current, activityView);
				getView().add(activityView);
			}
		}
	}

	public void renderView(Graphics2D g) {
		super.renderView(g);
        renderSequence(g);
	}
	
	private void renderSequence(Graphics2D g) {
	}
	
	public Dimension getPreferredSize() {
		return getView().getLayout().preferredLayoutSize(getView());
	}
}
