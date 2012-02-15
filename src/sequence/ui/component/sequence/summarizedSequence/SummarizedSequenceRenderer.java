package sequence.ui.component.sequence.summarizedSequence;

import java.awt.Dimension;
import java.awt.Graphics2D;

import sequence.model.Sequence;
import sequence.model.activity.Activity;
import sequence.mvc.DefaultRenderer;
import sequence.mvc.Renderer;
import sequence.mvc.View;
import sequence.ui.component.activity.ActivityRenderingModel;
import sequence.ui.component.activity.ActivitySummarizedController;
import sequence.ui.component.activity.ActivityView;
import sequence.ui.utilities.TimeLayout;
import sequence.utilities.ColorFactory;

public class SummarizedSequenceRenderer extends DefaultRenderer implements
		Renderer {
	private float scale;

	public SummarizedSequenceRenderer(final View view) {
		super(view);
		getView().setLayout(new TimeLayout());
		setScale();
		initialize();
	}

	public void initialize() {
		final Sequence sequence = (Sequence) getView().getModel();
		final ColorFactory colorFactory = new ColorFactory(sequence);
		for (final Activity current : sequence) {
			final ActivityView activityView = new ActivityView(current);
			final ActivityRenderingModel renderingModel = (ActivityRenderingModel) activityView
					.getRenderingModel();
			renderingModel.setColor(colorFactory.createColor(current));
			renderingModel.setHScale(scale);
			new ActivitySummarizedController(current, activityView);
			getView().add(activityView);
		}
	}

	public void setScale() {
		final Sequence sequence = (Sequence) getView().getModel();
		final int sequenceSize = sequence.getLastActivity().getActivitytime()
				.getStopTime()
				- sequence.get(0).getActivitytime().getStartTime();
		scale = (float) (getView().getSize().getWidth() / sequenceSize);
	}

	@Override
	public void renderView(final Graphics2D g) {
		super.renderView(g);
		setScale();
		renderSequence(g);
	}

	private void renderSequence(final Graphics2D g) {
		for (int i = 0; i < getView().getComponentCount(); i++) {
			final ActivityView activityView = (ActivityView) getView()
					.getComponent(i);
			((ActivityRenderingModel) activityView.getRenderingModel())
					.setHScale(scale);
		}
		getView().revalidate();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getView().getParent().getWidth(), (int) getView()
				.getLayout().minimumLayoutSize(getView()).getHeight());
	}
}
