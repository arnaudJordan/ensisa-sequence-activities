package sequence.ui.component.sequence.subSequence;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityController;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.utilities.TimeLayout;

public class SubSequenceRenderer extends DefaultRenderer implements Renderer {

	public SubSequenceRenderer(final View view) {
		super(view);
		getView().setLayout(new TimeLayout());
		initialize();
	}

	public void initialize() {
		final Sequence sequence = (Sequence) getView().getModel();
		final View summarizedSelectedActivities = ((SubSequenceView) getView())
				.getSummarizedView();
		for (final Activity current : sequence) {
			if (((SubSequenceRenderingModel) getView().getRenderingModel())
					.getDurationThreshold() <= current.getActivitytime()
					.getDuration()) {
				final int index = ((Sequence) summarizedSelectedActivities
						.getModel()).indexOf(current);
				final ActivityView activityView = new ActivityView(
						(ActivityView) summarizedSelectedActivities
								.getComponent(index));
				new ActivityController(current, activityView);
				getView().add(activityView);
			}
		}
	}

	@Override
	public void renderView(final Graphics2D g) {
		super.renderView(g);
		renderSequence(g);
	}

	private void renderSequence(final Graphics2D g) {
	}

	@Override
	public Dimension getPreferredSize() {
		final int Hinsets = 2 * (getView().getParent().getInsets().left + getView()
				.getParent().getInsets().right);
		return new Dimension(getView().getParent().getWidth() - Hinsets,
				(int) getView().getLayout().minimumLayoutSize(getView())
						.getHeight());
	}
}
