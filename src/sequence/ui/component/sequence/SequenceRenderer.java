package sequence.ui.component.sequence;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityController;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.utilities.ColorFactory;
import sequence.utilities.TimeLayout;

public class SequenceRenderer extends DefaultRenderer implements Renderer {
	
	public SequenceRenderer(View view) {
		super(view);
		getView().setLayout(new TimeLayout());
		initialize();
	}

	public void initialize() {
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
    	return new Dimension(getView().getParent().getParent().getWidth(), (int) getView().getLayout().minimumLayoutSize(this.getView()).getHeight());
    }
}
