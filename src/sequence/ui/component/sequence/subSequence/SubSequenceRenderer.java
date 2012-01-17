package sequence.ui.component.sequence.subSequence;

import java.awt.Dimension;
import java.awt.Graphics2D;
import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityController;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.utilities.TimeLayout;

public class SubSequenceRenderer extends DefaultRenderer implements Renderer {
	
	public SubSequenceRenderer(View view) {
		super(view);
		getView().setLayout(new TimeLayout());
		initialize();
	}

	public void initialize() {
		Sequence sequence = (Sequence)getView().getModel();
		View summarizedSelectedActivities = ((SubSequenceView) getView()).getSummarizedView();
		for(Activity current : sequence) {
			if(((SubSequenceRenderingModel)getView().getRenderingModel()).getDurationThreshold() <= current.getActivitytime().getDuration()) {
				int index = ((Sequence) summarizedSelectedActivities.getModel()).indexOf(current);
				ActivityView activityView = new ActivityView(current);
				((ActivityRenderingModel) activityView.getRenderingModel()).setColor(((ActivityRenderingModel) ((View) summarizedSelectedActivities.getComponent(index)).getRenderingModel()).getColor());
				new ActivityController(current, activityView);
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
		int Hinsets = 2*(getView().getParent().getInsets().left + getView().getParent().getInsets().right);
    	return new Dimension(getView().getParent().getWidth() - Hinsets, (int) getView().getLayout().minimumLayoutSize(getView()).getHeight());
    }
}
