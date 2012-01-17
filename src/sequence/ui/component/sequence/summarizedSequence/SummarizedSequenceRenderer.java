package sequence.ui.component.sequence.summarizedSequence;

import java.awt.Dimension;
import java.awt.Graphics2D;
import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.component.activity.ActivitySummarizedController;
import sequence.ui.component.sequence.SequenceContainer;
import sequence.ui.utilities.TimeLayout;
import sequence.utilities.ColorFactory;

public class SummarizedSequenceRenderer extends DefaultRenderer implements Renderer {
	private float scale;
	
	public SummarizedSequenceRenderer(View view) {
		super(view);
		getView().setLayout(new TimeLayout());
		setScale();
		initialize();
	}

	public void initialize() {
		Sequence sequence = (Sequence)getView().getModel();
		ColorFactory colorFactory = new ColorFactory(sequence);
		for(Activity current : sequence) {
			ActivityView activityView = new ActivityView(current);
			ActivityRenderingModel renderingModel = (ActivityRenderingModel) activityView.getRenderingModel();
			renderingModel.setColor(colorFactory.createColor(current));
			renderingModel.setHScale(scale);
			new ActivitySummarizedController(current, activityView);
			getView().add(activityView);
		}
	}
	
	public void setScale() {
		Sequence sequence = (Sequence)getView().getModel();
		int sequenceSize = sequence.getLastActivity().getActivitytime().getStopTime() - sequence.get(0).getActivitytime().getStartTime();
		scale = (float) (getView().getSize().getWidth()/sequenceSize);
	}

	public void renderView(Graphics2D g) {
		super.renderView(g);
		setScale();
        renderSequence(g);
	}
	
	private void renderSequence(Graphics2D g) {
		for(int i=0 ; i<getView().getComponentCount() ; i++) {
			ActivityView activityView = (ActivityView) getView().getComponent(i);
			((ActivityRenderingModel) activityView.getRenderingModel()).setHScale(scale);
		}
		getView().revalidate();
	}
	
	public Dimension getPreferredSize() {
		int Hinsets = 2*(getView().getParent().getInsets().left + getView().getParent().getInsets().right);
    	return new Dimension(getView().getParent().getWidth() - Hinsets, (int) getView().getLayout().minimumLayoutSize(getView()).getHeight());
    }
}
